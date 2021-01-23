package com.smartchoice.product.usecase;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.usecase.port.ProductRepositoryPort;
import com.smartchoice.product.usecase.port.SearchProductPort;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class CompareProductUseCase {

  private ProductRepositoryPort productRepositoryPort;

  private SearchProductPort searchProductPort;

  public CompareProductUseCase(
      ProductRepositoryPort productRepositoryPort, SearchProductPort searchProductPort) {
    this.productRepositoryPort = productRepositoryPort;
    this.searchProductPort = searchProductPort;
  }

  public List<Product> searchProductByName(String customerId, String productName) {

    final List<Product> products = searchProductPort.searchProductByName(productName);

    if (!CollectionUtils.isEmpty(products)) {
      productRepositoryPort.updateProductToCustomerAsync(customerId, products);
    }

    return products;
  }
}
