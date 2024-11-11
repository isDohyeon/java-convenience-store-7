package store.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.constants.ErrorMessages;
import store.domain.Product;
import store.domain.Products;

class ProductLoaderTest {

    private ProductLoader productLoader;

    @BeforeEach
    void setUp() {
        this.productLoader = new ProductLoader();
    }

    @Test
    void 총_제품_수가_16개인지_확인() {
        Products products = productLoader.getProducts();
        assertThat(products.getProducts()).hasSize(18);
    }

    @Test
    void 첫_번째_제품의_정보_확인() {
        Products products = productLoader.getProducts();
        Product firstProduct = products.getProducts().get(0);
        assertThat(firstProduct.getName()).isEqualTo("콜라");
        assertThat(firstProduct.getPrice()).isEqualTo(1000);
        assertThat(firstProduct.getPromotionStock()).isEqualTo(10);
        assertThat(firstProduct.getPromotion().getName()).isEqualTo("탄산2+1");
    }

    @Test
    void 두_번째_제품의_프로모션이_null인지_확인() {
        Products products = productLoader.getProducts();
        Product secondProduct = products.getProducts().get(1);
        assertThat(secondProduct.getPromotion()).isNull();
    }

    @Test
    void 마지막_제품의_정보를_확인() {
        Products products = productLoader.getProducts();
        Product lastProduct = products.getProducts().get(17);
        assertThat(lastProduct.getName()).isEqualTo("컵라면");
        assertThat(lastProduct.getPrice()).isEqualTo(1700);
        assertThat(lastProduct.getPromotionStock()).isEqualTo(10);
        assertThat(lastProduct.getPromotion()).isNull();
    }

    @Test
    void 상품_정보_검증() {
        HashMap product1 = new HashMap(Map.of("콜라", 99));
        HashMap product2 = new HashMap(Map.of("듣도보도못한상품", 1));

        assertThatThrownBy(() -> productLoader.validateProducts(List.of(product1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.QUANTITY.getMessage());

        assertThatThrownBy(() -> productLoader.validateProducts(List.of(product2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessages.PRODUCT_NAME.getMessage());
    }
}
