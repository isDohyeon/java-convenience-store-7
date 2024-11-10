package store.utils;

import java.util.List;
import java.util.stream.Collectors;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotion;

public class OutputParser {

    public static List<String> parseProducts(Products products) {
        return products.getProducts().stream()
                .map(OutputParser::formatProduct)
                .collect(Collectors.toList());
    }

    private static String formatProduct(Product product) {
        return "- "
                + product.getName() + " "
                + formatPrice(product.getPrice())
                + formatQuantity(product.getQuantity())
                + formatPromotion(product.getPromotion());
    }

    private static String formatPrice(int price) {
        return String.format("%,d", price) + "원 ";
    }

    private static String formatQuantity(int quantity) {
        if (quantity == 0) {
            return "재고 없음";
        }
        return quantity + "개 ";
    }

    private static String formatPromotion(Promotion promotion) {
        if (promotion == null) {
            return "";
        }
        return promotion.getName();
    }
}
