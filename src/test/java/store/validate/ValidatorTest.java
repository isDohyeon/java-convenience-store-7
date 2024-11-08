package store.validate;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "[콜라-10],[사이다-3]",
            "[콜라-3]",
            "[사이다-1],[물-2],[탄산수-3]"
    })
    void 올바른_입력값_검증(String input) {
        assertDoesNotThrow(() -> Validator.validatePurchaseInput(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "[콜라-10],",
            "[abc-10]",
            "[콜라-a]",
            "!콜라-10]",
            "[콜라,10]"
    })
    void 잘못된_입력값_검증(String input) {
        Assertions.assertThatThrownBy(() -> Validator.validatePurchaseInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
