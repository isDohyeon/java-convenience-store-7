package store.view;

import static store.utils.InputParser.parsePurchaseInput;
import static store.validate.Validator.validateItemsInput;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import store.constants.ErrorMessages;

public class InputView {

    private static final String PURCHASE_INPUT = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1]";
    private static final String FREE_ITEM = "\n현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n";
    private static final String MEMBERSHIP_DISCOUNT = "\n멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String WITHOUT_DISCOUNT = "\n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n";
    private static final String CONTINUE_SHOPPING = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";

    public List<Map<String, Integer>> readItems() throws IllegalArgumentException {
        System.out.println(PURCHASE_INPUT);
        String input = Console.readLine();
        validateItemsInput(input);
        return parsePurchaseInput(input);
    }

    public boolean confirmFreeItemAdd(String productName) {
        System.out.printf(FREE_ITEM, productName);
        try {
            return readUserConfirm();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return confirmFreeItemAdd(productName);
        }
    }

    public boolean confirmPurchase(String productName, int quantity) {
        System.out.printf(WITHOUT_DISCOUNT, productName, quantity);
        try {
            return readUserConfirm();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return confirmPurchase(productName, quantity);
        }
    }

    public boolean confirmMembershipDiscount() {
        System.out.println(MEMBERSHIP_DISCOUNT);
        try {
            return readUserConfirm();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return confirmMembershipDiscount();
        }
    }

    public boolean confirmContinueShopping() {
        System.out.println(CONTINUE_SHOPPING);
        try {
            return readUserConfirm();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return confirmContinueShopping();
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
