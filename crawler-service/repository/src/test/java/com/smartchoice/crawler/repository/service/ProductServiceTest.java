package com.smartchoice.crawler.repository.service;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.repository.config.DbUnitConfigTest;
import com.smartchoice.crawler.repository.mapper.ProductMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ProductServiceTest extends DbUnitConfigTest {

  private ProductService productService;

  @Override
  public void setUpDataTests() {
    super.setUpDataTests();
    MockitoAnnotations.initMocks(this);
    productService = new ProductService(productRepository, new ProductMapper());
  }

  @Test
  public void testGetProductByName() {
    final List<Product> products = productService.getProductByName("name");
    Assert.assertTrue(!CollectionUtils.isEmpty(products));
    Assert.assertTrue(products.size() == 2);
  }
}
