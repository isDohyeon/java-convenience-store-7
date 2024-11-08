package store.service;

import store.domain.Products;
import store.utils.FileUtils;

public class ProductService {

    private final Products products;

    public ProductService() {
        this.products = new Products(FileUtils.loadProducts("src/main/resources/products.md"));
    }

    public Products getProducts() {
        return products;
    }
}
