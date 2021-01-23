package com.smartchoice.product.usecase;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.usecase.port.ProductRepositoryPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ProductDetailUseCaseTest {

  @Mock private ProductRepositoryPort productRepositoryPort;

  @InjectMocks private ProductDetailUseCase productDetailUseCase;

  private Product productTiki;
  private Product productLazada;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    productTiki = new Product();
    productTiki.setId(Long.valueOf("1"));
    productTiki.setProductName("test name");
    productTiki.setDiscount(new BigDecimal(10));
    productTiki.setPrice(new BigDecimal(10));
    productTiki.setInformation("test information");
    productTiki.setShop("TIKI");
    productTiki.setTitle("test title");
    productTiki.setUrl("url");

    productLazada = new Product();
    productLazada.setId(Long.valueOf("2"));
    productLazada.setProductName("test name");
    productLazada.setDiscount(new BigDecimal(10));
    productLazada.setPrice(new BigDecimal(10));
    productLazada.setInformation("test information");
    productLazada.setShop("LAZADA");
    productLazada.setTitle("test title");
    productLazada.setUrl("url");
  }

  @Test
  public void testGetProductDetail() {
    Mockito.when(productRepositoryPort.getProductByCustomer(Mockito.anyString()))
        .thenReturn(Arrays.asList(productLazada, productTiki));

    final Product result = productDetailUseCase.getProductDetail("customerId", "1");
    Assertions.assertTrue(result != null);
    Assertions.assertEquals("TIKI", result.getShop());
  }
}
