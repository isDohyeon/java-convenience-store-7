package store.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import store.validate.Validator;

public class InputParser {

    public static List<Map<String, Integer>> parsePurchaseInput(String input) {
        List<Map<String, Integer>> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(Validator.PURCHASE_PATTERN);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(extractPurchaseInfo(matcher));
        }
        return result;
    }

    private static Map<String, Integer> extractPurchaseInfo(Matcher matcher) {
        return new HashMap<>(Map.of(matcher.group(1), Integer.parseInt(matcher.group(2))));
    }
}
