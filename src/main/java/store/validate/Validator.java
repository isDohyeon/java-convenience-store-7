package store.validate;

import store.messages.ErrorMessages;

public class Validator {

    public static final String PURCHASE_PATTERN = "\\[([가-힣]+)-(\\d+)]";

    public static void validateItemsInput(String input) {
        if (!input.matches("^" + PURCHASE_PATTERN + "(," + PURCHASE_PATTERN + ")*$")) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PATTERN.getMessage());
        }
    }
}
