package store.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import store.domain.Product;
import store.domain.Products;

public class OutputParser {

    public static List<String> parseProducts(Products products) {
        return products.getProducts().stream()
                .flatMap(product -> formatProduct(product).stream())
                .collect(Collectors.toList());
    }

    private static List<String> formatProduct(Product product) {
        List<String> formattedProducts = new ArrayList<>();
        if (product.getPromotion() != null) {
            formattedProducts.add(addDefaultInfo(product)
                    + formatQuantity(product.getPromotionStock())
                    + product.getPromotion().getName());
        }
        formattedProducts.add(addDefaultInfo(product)
                + formatQuantity(product.getDefaultStock()));
        return formattedProducts;
    }

    private static String addDefaultInfo(Product product) {
        return "- " + product.getName() + " " + formatPrice(product.getPrice());
    }

    private static String formatPrice(int price) {
        return String.format("%,d", price) + "원 ";
    }

    private static String formatQuantity(int quantity) {
        if (quantity == 0) {
            return "재고 없음 ";
        }
        return quantity + "개 ";
    }
}
