package store.domain;

import java.util.List;
import store.utils.FileUtils;

public class Products {

    private final List<Product> products;

    public Products(String filePath) {
        this.products = FileUtils.loadProducts(filePath);
    }

    public List<Product> getProducts() {
        return products;
    }
}
