package store.controller;

import static store.utils.OutputParser.parseToString;

import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;

public class Controller {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ProductService productService = new ProductService();

    public void run() {
        outputView.printProducts(parseToString(productService.getProducts()));
        inputView.readItem();
    }
}
