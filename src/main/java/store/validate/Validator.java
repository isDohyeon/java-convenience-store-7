package store.validate;

import store.constants.ErrorMessages;

public class Validator {

    public static final String PURCHASE_PATTERN = "\\[([가-힣]+)-(\\d+)]";

    public static void validatePurchaseInput(String input) {
        if (!input.matches("^" + PURCHASE_PATTERN + "(," + PURCHASE_PATTERN + ")*$")) {
            throw new IllegalArgumentException(ErrorMessages.PATTERN.getMessage());
        }
    }
}
