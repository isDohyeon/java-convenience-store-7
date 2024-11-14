package store.view;

import static store.messages.InputMessages.CONTINUE_SHOPPING_PROMPT;
import static store.messages.InputMessages.FREE_ITEM_PROMPT;
import static store.messages.InputMessages.MEMBERSHIP_DISCOUNT_PROMPT;
import static store.messages.InputMessages.PURCHASE_PROMPT;
import static store.messages.InputMessages.WITHOUT_DISCOUNT_PROMPT;
import static store.utils.InputParser.parsePurchaseInput;
import static store.validate.Validator.validateItemsInput;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import store.messages.ErrorMessages;

public class InputView {

    public List<Map<String, Integer>> readItems() throws IllegalArgumentException {
        System.out.println(PURCHASE_PROMPT.getMessage());
        String input = Console.readLine();
        validateItemsInput(input);
        return parsePurchaseInput(input);
    }

    public boolean confirmFreeItemAdd(String productName) {
        return confirmAction(String.format(FREE_ITEM_PROMPT.getMessage(), productName));
    }

    public boolean confirmPurchase(String productName, int quantity) {
        return confirmAction(String.format(WITHOUT_DISCOUNT_PROMPT.getMessage(), productName, quantity));
    }

    public boolean confirmMembershipDiscount() {
        return confirmAction(MEMBERSHIP_DISCOUNT_PROMPT.getMessage());
    }

    public boolean confirmContinueShopping() {
        return confirmAction(CONTINUE_SHOPPING_PROMPT.getMessage());
    }

    private boolean confirmAction(String message) {
        System.out.println(message);
        try {
            return readUserConfirm();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return confirmAction(message);
        }
    }

    private boolean readUserConfirm() {
        String input = Console.readLine();
        if (input.equals("Y")) {
            return true;
        }
        if (input.equals("N")) {
            return false;
        }
        throw new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage());
    }
}
