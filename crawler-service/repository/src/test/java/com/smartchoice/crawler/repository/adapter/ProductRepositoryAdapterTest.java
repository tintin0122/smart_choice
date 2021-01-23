package com.smartchoice.crawler.repository.adapter;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.repository.config.DbUnitConfigTest;
import com.smartchoice.crawler.repository.mapper.ProductMapper;
import com.smartchoice.crawler.repository.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ProductRepositoryAdapterTest extends DbUnitConfigTest {
  @InjectMocks private ProductRepositoryAdapter adapter;

  @Override
  public void setUpDataTests() {
    super.setUpDataTests();
    MockitoAnnotations.initMocks(this);
    final ProductService productService =
        new ProductService(productRepository, new ProductMapper());
    ReflectionTestUtils.setField(adapter, "productService", productService);
  }

  @Test
  public void testSearchProductByName() {
      final List<Product> products = adapter.searchProductByName("name");
    Assert.assertTrue(!CollectionUtils.isEmpty(products));
    Assert.assertTrue(products.size() == 2);
  }
}
