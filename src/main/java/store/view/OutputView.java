package store.view;

import java.util.List;

public class OutputView {

    private static final String INTRODUCTION_PROMPT = "\n안녕하세요. W편의점 입니다.\n현재 보유하고 있는 상품입니다.\n";

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printProducts(List<String> products) {
        System.out.println(INTRODUCTION_PROMPT);
        products.forEach(System.out::println);
    }

    public void printReceipt(String receipt) {
        System.out.println(receipt);
    }
}
