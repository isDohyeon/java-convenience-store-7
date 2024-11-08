package store.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.domain.Product;

public class FileUtils {

    public static List<Product> loadProducts(String filePath) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = List.of(line.split(","));
                String name = values.get(0);
                int price = Integer.parseInt(values.get(1));
                int quantity = Integer.parseInt(values.get(2));
                String promotion = getPromotion(values.get(3));

                products.add(new Product(name, price, quantity, promotion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static String getPromotion(String promotion) {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion;
    }
}
