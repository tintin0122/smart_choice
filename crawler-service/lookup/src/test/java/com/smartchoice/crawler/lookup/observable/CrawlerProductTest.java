package com.smartchoice.crawler.lookup.observable;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.lookup.service.LazadaSearchProductService;
import com.smartchoice.crawler.lookup.service.Observer;
import com.smartchoice.crawler.lookup.service.ShopeeSearchProductService;
import com.smartchoice.crawler.lookup.service.TikiSearchProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CrawlerProductTest {

  @InjectMocks private CrawlerProduct crawlerProduct;

  private List<Observer> observers = new ArrayList<>();

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

    ReflectionTestUtils.setField(crawlerProduct, "apiResources", observers);
  }

  @Test
  public void testSearchProductByName() {
    final LazadaSearchProductService mockLazada = Mockito.mock(LazadaSearchProductService.class);
    Mockito.when(mockLazada.searchProduct(Mockito.anyString()))
        .thenReturn(Arrays.asList(productTiki));

    final TikiSearchProductService mockTiki = Mockito.mock(TikiSearchProductService.class);
    Mockito.when(mockTiki.searchProduct(Mockito.anyString()))
        .thenReturn(Arrays.asList(productLazada));

    final ShopeeSearchProductService mockShopee = Mockito.mock(ShopeeSearchProductService.class);
    Mockito.when(mockShopee.searchProduct(Mockito.anyString())).thenReturn(Collections.emptyList());

    observers.add(mockShopee);
    observers.add(mockLazada);
    observers.add(mockTiki);

    final List<Product> products = crawlerProduct.searchProductByName("name");
    Assertions.assertTrue(products.size() == 2);
  }
}
