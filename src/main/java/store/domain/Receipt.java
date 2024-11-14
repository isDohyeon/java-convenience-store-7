package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private final List<String> purchasedItems = new ArrayList<>();
    private final List<String> freeItems = new ArrayList<>();
    private int totalAmount;
    private int promotionDiscount;
    private int membershipDiscount;

    public void addPurchasedItem(String productName, int quantity, int amount) {
        purchasedItems.add(String.format("%-19s%-10s%-6s%n", productName, quantity, formatPrice(amount)));
    }

    public void addFreeItem(String productName, int quantity) {
        freeItems.add(String.format("%-15s %5d", productName, quantity));
    }

    public void setTotalAmount(int amount) {
        this.totalAmount = amount;
    }

    public void addPromotionDiscount(int amount) {
        this.promotionDiscount += amount;
    }

    public void addMembershipDiscount(int amount) {
        this.membershipDiscount += amount;
    }

    public int getFinalAmount() {
        return totalAmount - promotionDiscount - membershipDiscount;
    }

    private String formatPrice(int amount) {
        return String.format("%,d", amount);
    }

    private String formatDiscountPrice(int amount) {
        return String.format("-%,d", amount);
    }

    public String generateReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();

        receiptBuilder.append("\n==============W 편의점================\n");
        receiptBuilder.append(String.format("%-19s%-10s%-6s%n", "상품명", "수량", "금액"));
        purchasedItems.forEach(item -> receiptBuilder.append(item).append("\n"));

        receiptBuilder.append("=============증\t\t정===============\n");
        freeItems.forEach(item -> receiptBuilder.append(item).append("\n"));
        receiptBuilder.append("====================================\n");

        receiptBuilder.append(String.format("%-18s %-9s %-6s\n", "총구매액", getTotalQuantity(), formatPrice(totalAmount)));
        receiptBuilder.append(String.format("%-27s %-5s\n", "행사할인", formatDiscountPrice(promotionDiscount)));
        receiptBuilder.append(String.format("%-27s %-5s\n", "멤버십할인", formatDiscountPrice(membershipDiscount)));
        receiptBuilder.append(String.format("%-28s %-5s\n", "내실돈", formatPrice(getFinalAmount())));

        return receiptBuilder.toString();
    }

    private int getTotalQuantity() {
        return purchasedItems.stream()
                .mapToInt(item -> Integer.parseInt(item.split("\\s+")[1]))
                .sum();
    }
}
