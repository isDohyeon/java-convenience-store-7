package store.domain;

import store.constants.ErrorMessages;

public class Product {

    private final String name;
    private final int price;
    private int quantity;
    private final Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        this.quantity = quantity;
    }

    public void reduceStock(int quantity){
        this.quantity -= quantity;
    }

    public void validateStock(int quantity) {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException(ErrorMessages.QUANTITY.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
