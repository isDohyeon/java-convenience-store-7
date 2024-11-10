package store.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.domain.Product;
import store.domain.Promotion;

public class FileUtils {

    private static final String DELIMITER = ",";

    public static List<Product> loadProducts(String filePath, Map<String, Promotion> promotions) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            return br.lines()
                    .map(line -> parseProduct(line, promotions))
                    .toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    public static Map<String, Promotion> loadPromotions(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            return br.lines()
                    .map(FileUtils::parsePromotion)
                    .collect(HashMap::new, (map, promo) -> map.put(promo.getName(), promo), HashMap::putAll);
        } catch (IOException e) {
            return Map.of();
        }
    }

    private static Product parseProduct(String line, Map<String, Promotion> promotions) {
        List<String> values = List.of(line.split(DELIMITER));
        String name = values.get(0);
        int price = Integer.parseInt(values.get(1));
        int quantity = Integer.parseInt(values.get(2));
        Promotion promotion = promotions.getOrDefault(values.get(3), null);
        return new Product(name, price, quantity, promotion);
    }

    private static Promotion parsePromotion(String line) {
        List<String> values = List.of(line.split(DELIMITER));
        String name = values.get(0);
        int buyCount = Integer.parseInt(values.get(1));
        int freeCount = Integer.parseInt(values.get(2));
        LocalDate startDate = LocalDate.parse(values.get(3));
        LocalDate endDate = LocalDate.parse(values.get(4));
        return new Promotion(name, buyCount, freeCount, startDate, endDate);
    }
}
