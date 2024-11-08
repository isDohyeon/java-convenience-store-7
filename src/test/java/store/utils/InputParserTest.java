package store.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    void 입력값_추출_테스트() {
        String input = "[콜라-10],[사이다-3]";

        List<Map<String, Integer>> result = InputParser.parsePurchaseInput(input);

        assertThat(result).hasSize(2);

        assertThat(result.get(0)).isEqualTo(new HashMap(Map.of("콜라", 10)));
        assertThat(result.get(1)).isEqualTo(new HashMap(Map.of("사이다", 3)));
    }
}
