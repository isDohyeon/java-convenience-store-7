package store.view;

import static store.utils.InputParser.parsePurchaseInput;
import static store.validate.Validator.validateItemsInput;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;

public class InputView {

    public List<Map<String, Integer>> readItems() throws IllegalArgumentException {
        System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1]");
        String input = Console.readLine();
        validateItemsInput(input);
        return parsePurchaseInput(input);
    }
}
