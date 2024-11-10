package store.domain;

import java.util.List;
import java.util.Map;
import store.utils.FileUtils;

public class Products {

    private final List<Product> products;

    public Products(String filePath, Map<String, Promotion> promotions) {
        this.products = FileUtils.loadProducts(filePath, promotions);
    }

    public List<Product> getProducts() {
        return products;
    }
}
