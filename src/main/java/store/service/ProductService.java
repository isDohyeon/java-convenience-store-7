package store.service;

import java.util.List;
import java.util.Map;
import store.domain.Products;
import store.domain.Promotion;
import store.utils.FileUtils;

public class ProductService {

    private final Products products;

    public ProductService() {
        Map<String, Promotion> promotions = FileUtils.loadPromotions("src/main/resources/promotions.md");
        this.products = new Products("src/main/resources/products.md", promotions);
    }

    public void purchase(List<Map<String, Integer>> request) {

    }

    public Products getProducts() {
        return products;
    }
}
