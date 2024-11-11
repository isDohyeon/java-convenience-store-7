package store.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.constants.ErrorMessages;

class ProductTest {

    private Product productWithPromotion;
    private Product productWithoutPromotion;

    @BeforeEach
    void setUp() {
        Promotion promotion = new Promotion("탄산2+1", 2, 1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(10));
        productWithPromotion = new Product("콜라", 1000, 10, promotion);
        productWithoutPromotion = new Product("사이다", 1000, 5, null);
    }

    @Test
    void 프로모션_제품_초기화_확인() {
        assertThat(productWithPromotion.getName()).isEqualTo("콜라");
        assertThat(productWithPromotion.getPrice()).isEqualTo(1000);
        assertThat(productWithPromotion.getPromotionStock()).isEqualTo(10);
        assertThat(productWithPromotion.getDefaultStock()).isEqualTo(0);
        assertThat(productWithPromotion.getPromotion().getName()).isEqualTo("탄산2+1");
    }

    @Test
    void 기본_제품_초기화_확인() {
        assertThat(productWithoutPromotion.getName()).isEqualTo("사이다");
        assertThat(productWithoutPromotion.getPrice()).isEqualTo(1000);
        assertThat(productWithoutPromotion.getDefaultStock()).isEqualTo(5);
        assertThat(productWithoutPromotion.getPromotionStock()).isEqualTo(0);
        assertThat(productWithoutPromotion.getPromotion()).isNull();
    }

    @Test
    void 기본_재고_추가() {
        productWithoutPromotion.addStock(3, false);

        assertThat(productWithoutPromotion.getDefaultStock()).isEqualTo(8);
    }

    @Test
    void 프로모션_재고_추가() {
        productWithPromotion.addStock(5, true);

        assertThat(productWithPromotion.getPromotionStock()).isEqualTo(15);
    }

    @Test
    void 기본_재고_감소() {
        productWithoutPromotion.reduceStock(3, false);

        assertThat(productWithoutPromotion.getDefaultStock()).isEqualTo(2);
    }

    @Test
    void 프로모션_재고_감소() {
        productWithPromotion.reduceStock(8, true);

        assertThat(productWithPromotion.getPromotionStock()).isEqualTo(2);
    }

    @Test
    void 기본_재고가_부족할_때_예외_발생() {
        assertThatThrownBy(() -> productWithoutPromotion.reduceStock(6, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.QUANTITY.getMessage());
    }

    @Test
    void 프로모션_재고가_부족할_때_예외_발생() {
        assertThatThrownBy(() -> productWithPromotion.reduceStock(15, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.QUANTITY.getMessage());
    }
}
