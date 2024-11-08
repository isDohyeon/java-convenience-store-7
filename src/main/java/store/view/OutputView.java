package store.view;

import store.domain.Product;
import store.domain.Products;

public class OutputView {

    public void printProducts(Products products) {
        System.out.println("안녕하세요. W편의점 입니다.\n현재 보유하고 있는 상품입니다.\n");
        for (Product product : products.getProducts()) {
            System.out.println(formatMessage(product));
        }
    }

    private static String formatMessage(Product product) {
        return String.format(Message.FORMAT_TEXT.getText(),
                product.getName(),
                String.format("%,d", product.getPrice()),
                product.getQuantity(),
                product.getPromotion());
    }

    private enum Message {
        FORMAT_TEXT("- %s %s원 %d개 %s");

        private final String text;

        Message(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
