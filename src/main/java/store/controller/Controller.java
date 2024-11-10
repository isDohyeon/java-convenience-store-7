package store.controller;

import static store.utils.OutputParser.parseProducts;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;

public class Controller {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ProductService productService = new ProductService();

    public void run() {
        outputView.printProducts(parseProducts(productService.getProducts()));
        List<Map<String, Integer>> purchaseItems = readItems();
    }

    private List<Map<String, Integer>> readItems() {
        retryUntilValid(() -> {
            List<Map<String, Integer>> items = inputView.readItems();
            productService.validateProducts(items);
            return items;
        });
        return List.of();
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return retryUntilValid(supplier);
    }
}
