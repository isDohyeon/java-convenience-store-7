package store.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.domain.Product;

public class FileUtils {

    private static final String DELIMITER = ",";

    public static List<Product> loadProducts(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            return br.lines()
                    .map(FileUtils::parseProduct)
                    .toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    private static Product parseProduct(String line) {
        List<String> values = List.of(line.split(DELIMITER));
        String name = values.get(0);
        int price = Integer.parseInt(values.get(1));
        int quantity = Integer.parseInt(values.get(2));
        String promotion = parsePromotion(values.get(3));
        return new Product(name, price, quantity, promotion);
    }

    private static String parsePromotion(String promotion) {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion;
    }
}
