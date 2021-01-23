package com.smartchoice.crawler.usecase;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.usecase.port.LookupProductPort;
import com.smartchoice.crawler.usecase.port.ProductRepositoryPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CrawlProductUseCaseTest {
  @Mock private LookupProductPort lookupProductPort;

  @Mock private ProductRepositoryPort productRepositoryPort;

  @InjectMocks private CrawlProductUseCase crawlProductUseCase;

  private Product product;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    product = new Product();
    product.setId(Long.valueOf("1"));
    product.setProductName("test name");
    product.setDiscount(new BigDecimal(10));
    product.setPrice(new BigDecimal(10));
    product.setInformation("test information");
    product.setShop("TIKI");
    product.setTitle("test title");
    product.setUrl("url");
  }

  @Test
  public void testGetAllProductInformation() {

    Mockito.when(productRepositoryPort.searchProductByName(Mockito.anyString()))
        .thenReturn(Arrays.asList(product));

    final List<Product> products = crawlProductUseCase.getAllProductInformation("name");
    Assertions.assertTrue(!CollectionUtils.isEmpty(products));
    Assertions.assertTrue(Long.valueOf("1").equals(products.get(0).getId()));
  }

  @Test
  public void testGetAllProductInformationCallExternal() {
    Mockito.when(productRepositoryPort.searchProductByName(Mockito.anyString()))
        .thenReturn(Collections.emptyList());
    Mockito.when(lookupProductPort.crawlProductFromAllExternalApi(Mockito.anyString()))
        .thenReturn(Arrays.asList(product));

    final List<Product> products = crawlProductUseCase.getAllProductInformation("name");
    Assertions.assertTrue(!CollectionUtils.isEmpty(products));
    Assertions.assertTrue(Long.valueOf("1").equals(products.get(0).getId()));
  }

  @Test
  public void testGetAllProductInformationNoData() {
    Mockito.when(productRepositoryPort.searchProductByName(Mockito.anyString()))
        .thenReturn(Collections.emptyList());
    Mockito.when(lookupProductPort.crawlProductFromAllExternalApi(Mockito.anyString()))
        .thenReturn(Collections.emptyList());

    final List<Product> products = crawlProductUseCase.getAllProductInformation("name");
    Assertions.assertTrue(CollectionUtils.isEmpty(products));
  }
}
