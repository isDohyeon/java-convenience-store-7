package store.service;

import java.util.List;
import java.util.Map;
import store.domain.Products;

public class ProductService {

    private final Products products;

    public ProductService() {
        this.products = new Products("src/main/resources/products.md");
    }

    public void purchase(List<Map<String, Integer>> request) {

    }

    public Products getProducts() {
        return products;
    }
}
