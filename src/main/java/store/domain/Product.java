package store.domain;

import store.constants.ErrorMessages;

public class Product {

    private final String name;
    private final int price;
    private int promotionStock;
    private int defaultStock;
    private final Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        confirmStock(quantity, promotion);
    }

    private void confirmStock(int quantity, Promotion promotion) {
        if (promotion == null) {
            this.defaultStock = quantity;
            return;
        }
        this.promotionStock = quantity;
    }

    public boolean isInsufficientPromotionStock(int quantity) {
        return promotionStock < quantity;
    }

    public int getNoneDiscountAmount(int requestedQuantity) {
        return requestedQuantity - calculateFreeCount(requestedQuantity) * promotion.getSingleSet();
    }

    public int calculateFreeCount(int quantity) {
        return Math.min(promotionStock, quantity) / promotion.getSingleSet();
    }

    public void addStock(int quantity, boolean isPromotionStock) {
        if (isPromotionStock) {
            this.promotionStock += quantity;
            return;
        }
        this.defaultStock += quantity;
    }

    public void reduceStock(int quantity, boolean isPromotionStock) {
        if (isPromotionStock) {
            if (promotionStock < quantity) {
                defaultStock -= (quantity - promotionStock);
                promotionStock = 0;
                return;
            }
            promotionStock -= quantity;
            return;
        }
        this.defaultStock -= quantity;
    }

    public void validateStock(int quantity) {
        if (promotionStock + defaultStock < quantity) {
            throw new IllegalArgumentException(ErrorMessages.QUANTITY.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDefaultStock() {
        return defaultStock;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public int getPromotionStock() {
        return promotionStock;
    }
}
