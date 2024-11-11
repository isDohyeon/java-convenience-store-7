package store.controller;

import static store.utils.OutputParser.parseProducts;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import store.service.ProductLoader;
import store.service.PurchaseProcessor;
import store.view.InputView;
import store.view.OutputView;

public class Controller {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ProductLoader productLoader = new ProductLoader();
    private final PurchaseProcessor processor = new PurchaseProcessor(productLoader.getProducts());

    public void run() {
        do {
            outputView.printProducts(parseProducts(productLoader.getProducts()));
            List<Map<String, Integer>> purchaseItems = readItems();
            processor.purchase(purchaseItems);
            outputView.printReceipt(processor.getReceipt());
        } while (inputView.confirmContinueShopping());
    }

    private List<Map<String, Integer>> readItems() {
        try {
            List<Map<String, Integer>> items = inputView.readItems();
            productLoader.validateProducts(items);
            return items;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readItems();
        }
    }
}
