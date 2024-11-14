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
        for (Map<String, Integer> item : items) {
            handlePurchase(item);
        }
    }

    private void handlePurchase(Map<String, Integer> item) {
        for (Entry<String, Integer> entry : item.entrySet()) {
            Product product = products.findProductByName(entry.getKey());
            String name = product.getName();
            int quantity = entry.getValue();
            Promotion promotion = product.getPromotion();
            extracted(promotion, product, quantity, name);
        }
    }

    private void extracted(Promotion promotion, Product product, int quantity, String name) {
        int promotionDiscount = 0;
        if (promotion != null && promotion.isValidPromotion(LocalDate.now())) {
            int freeCount = product.calculateFreeCount(quantity);
            if (promotion.isInsufficientPurchase(quantity, product.getPromotionStock())) {
                if (inputView.confirmFreeItemAdd(name)) {
                    int addedFreeCount = promotion.getFreeCount();
                    quantity += addedFreeCount;
                    freeCount += addedFreeCount;
                }
            }
            if (product.isInsufficientPromotionStock(quantity)) {
                int noneDiscountAmount = product.getNoneDiscountAmount(quantity);
                if (!inputView.confirmPurchase(name, noneDiscountAmount)) {
                    quantity -= noneDiscountAmount;
                }
            }
            promotionDiscount = product.getPrice() * freeCount;
            receipt.addFreeItem(name, freeCount);
        }

        int purchasePrice = product.getPrice() * quantity;
        product.reduceStock(quantity, promotion != null);
        confirmReceipt(quantity, name, purchasePrice, promotionDiscount);
    }

    private void confirmReceipt(int quantity, String name, int purchaseAmount, int promotionDiscount) {
        receipt.addPurchasedItem(name, quantity, purchaseAmount);
        receipt.setTotalAmount(purchaseAmount);
        receipt.addPromotionDiscount(promotionDiscount);
        confirmMembershipDiscount(purchaseAmount, promotionDiscount);
    }

    private void confirmMembershipDiscount(int purchaseAmount, int promotionDiscount) {
        int membershipDiscount = 0;
        if (inputView.confirmMembershipDiscount()) {
            membershipDiscount = calculateMembershipDiscount(purchaseAmount - promotionDiscount);
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
