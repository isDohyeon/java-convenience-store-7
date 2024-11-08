package store.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.Product;
import store.domain.Products;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        this.productService = new ProductService();
    }

    @Test
    void 총_제품_수가_16개인지_확인() {
        Products products = productService.getProducts();
        assertThat(products.getProducts()).hasSize(18);
    }

    @Test
    void 첫_번째_제품의_정보_확인() {
        Products products = productService.getProducts();
        Product firstProduct = products.getProducts().get(0);
        assertThat(firstProduct.getName()).isEqualTo("콜라");
        assertThat(firstProduct.getPrice()).isEqualTo(1000);
        assertThat(firstProduct.getQuantity()).isEqualTo(10);
        assertThat(firstProduct.getPromotion()).isEqualTo("탄산2+1");
    }

    @Test
    void 두_번째_제품의_프로모션이_null인지_확인() {
        Products products = productService.getProducts();
        Product secondProduct = products.getProducts().get(1);
        assertThat(secondProduct.getPromotion()).isEqualTo("");
    }

    @Test
    void 마지막_제품의_정보를_확인() {
        Products products = productService.getProducts();
        Product lastProduct = products.getProducts().get(17);
        assertThat(lastProduct.getName()).isEqualTo("컵라면");
        assertThat(lastProduct.getPrice()).isEqualTo(1700);
        assertThat(lastProduct.getQuantity()).isEqualTo(10);
        assertThat(lastProduct.getPromotion()).isEqualTo("");
    }
}
