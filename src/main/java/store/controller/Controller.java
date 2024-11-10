package store.controller;

import static store.utils.OutputParser.parseProducts;

import java.util.List;
import java.util.Map;
import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;

public class Controller {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ProductService productService = new ProductService();

    public void run() {
        outputView.printProducts(parseProducts(productService.getProducts()));
        purchase();
    }

    private void purchase() {
        retryUntilValid(() -> {
            List<Map<String, Integer>> purchaseRequests = inputView.readItem();
        });
    }

    private void retryUntilValid(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            retryUntilValid(runnable);
        }
    }
}
