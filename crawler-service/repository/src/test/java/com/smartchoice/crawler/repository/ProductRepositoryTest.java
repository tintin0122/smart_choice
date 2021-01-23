package com.smartchoice.crawler.repository;

import com.smartchoice.crawler.repository.config.DbUnitConfigTest;
import com.smartchoice.crawler.repository.model.ProductModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ProductRepositoryTest extends DbUnitConfigTest {
  @Override
  public void setUpDataTests() {
    super.setUpDataTests();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSearchProductByName() {
    final List<ProductModel> productModels = productRepository.searchProductByName("name");
    Assert.assertTrue(!CollectionUtils.isEmpty(productModels));
    Assert.assertTrue(productModels.size() == 2);
  }
}
