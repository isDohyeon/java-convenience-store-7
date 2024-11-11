package store.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotion;
import store.domain.Receipt;
import store.view.InputView;

public class PurchaseProcessor {

    private static final int MEMBERSHIP_DISCOUNT_RATE = 30;
    private static final int MEMBERSHIP_DISCOUNT_LIMIT = 8000;

    private final InputView inputView;
    private final Products products;
    private Receipt receipt;

    public PurchaseProcessor(Products products) {
        this.inputView = new InputView();
        this.products = products;
    }

    public void purchase(List<Map<String, Integer>> items) {
        this.receipt = new Receipt();
        int totalAmount = 0;
        int promotionDiscount = 0;
        int membershipDiscount = 0;
        int additionalFree = 0;

        for (Map<String, Integer> item : items) {
            for (Entry<String, Integer> entry : item.entrySet()) {
                Product product = products.findProductByName(entry.getKey());
                String name = product.getName();
                int quantity = entry.getValue();
                Promotion promotion = product.getPromotion();

                if (promotion != null && promotion.isValidPromotion(LocalDate.now())) {
                    int freeCount = 0;
                    if (product.isInsufficientPromotionStock(quantity)) {
                        int nonDiscountQuantity = product.getNoneDiscountAmount(quantity);
                        if (inputView.confirmPurchase(name, nonDiscountQuantity)) {
                            freeCount += product.calculateFreeCount(quantity);
                        }
                    } else if (promotion.isInsufficientPurchase(quantity)) {
                        if (inputView.confirmFreeItemAdd(name)) {
                            freeCount += product.calculateFreeCount(quantity);
                            freeCount += 1;
                            additionalFree++;
                            product.reduceStock(quantity, true);
                        }
                    }
                    promotionDiscount += product.getPrice() * freeCount;
                    receipt.addFreeItem(name, freeCount);
                }
                totalAmount = configTotalAmount(product, quantity, additionalFree, promotion, name, totalAmount);
            }
        }
        finalizePurchase(totalAmount, promotionDiscount, membershipDiscount);
    }

    private int configTotalAmount(Product product, int quantity, int additionalFree, Promotion promotion, String name,
                               int totalAmount) {
        int productAmount = product.getPrice() * (quantity + additionalFree);
        product.reduceStock(quantity, promotion != null);
        receipt.addPurchasedItem(name, quantity + additionalFree, productAmount);
        totalAmount += productAmount;
        return totalAmount;
    }

    private void finalizePurchase(int totalAmount, int promotionDiscount, int membershipDiscount) {
        receipt.setTotalAmount(totalAmount);
        receipt.addPromotionDiscount(promotionDiscount);
        if (inputView.confirmMembershipDiscount()) {
            membershipDiscount = calculateMembershipDiscount(totalAmount - promotionDiscount);
        }
        receipt.addMembershipDiscount(membershipDiscount);
    }

    private int calculateMembershipDiscount(int amountAfterPromotion) {
        int discount = (amountAfterPromotion * MEMBERSHIP_DISCOUNT_RATE) / 100;
        return Math.min(discount, MEMBERSHIP_DISCOUNT_LIMIT);
    }

    public String getReceipt() {
        return receipt.generateReceipt();
    }
}
