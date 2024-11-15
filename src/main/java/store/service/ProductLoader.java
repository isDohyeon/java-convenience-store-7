package store.service;

import java.util.List;
import java.util.Map;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotion;
import store.messages.ErrorMessages;
import store.utils.FileUtils;

public class ProductLoader {

    private final Products products;

    public ProductLoader() {
        Map<String, Promotion> promotions = FileUtils.loadPromotions("src/main/resources/promotions.md");
        this.products = new Products("src/main/resources/products.md", promotions);
    }

    public void validateProducts(List<Map<String, Integer>> items) throws IllegalArgumentException {
        items.stream()
                .flatMap(request -> request.entrySet().stream())
                .forEach(entry -> {
                    if (isTotalStockShortage(products.findProductByName(entry.getKey()), entry.getValue())) {
                        throw new IllegalArgumentException(ErrorMessages.STOCK_OVERFLOW.getMessage());
                    }
                });
    }

    private static boolean isTotalStockShortage(Product product, Integer purchaseQuantity) {
        return product.getPromotionStock() + product.getDefaultStock() < purchaseQuantity;
    }

    public Products getProducts() {
        return products;
    }
}
