package store.view;


import java.util.List;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printProducts(List<String> products) {
        System.out.println("안녕하세요. W편의점 입니다.\n현재 보유하고 있는 상품입니다.\n");
        products.forEach(System.out::println);
    }
}
