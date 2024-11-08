package store.controller;

import store.service.ProductService;
import store.view.OutputView;

public class Controller {

    private final OutputView outputView = new OutputView();
    private final ProductService productService = new ProductService();

    public void run() {
        outputView.printProducts(productService.getProducts());
    }
}
