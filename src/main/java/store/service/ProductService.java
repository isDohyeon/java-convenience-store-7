package store.service;

import store.domain.Products;

public class ProductService {

    private final Products products;

    public ProductService() {
        this.products = new Products("src/main/resources/products.md");
    }

    public Products getProducts() {
        return products;
    }
}
