package com.smartchoice.product.usecase;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.usecase.port.ProductRepositoryPort;

public class ProductDetailUseCase {

  private ProductRepositoryPort productRepositoryPort;

  public ProductDetailUseCase(ProductRepositoryPort productRepositoryPort) {
    this.productRepositoryPort = productRepositoryPort;
  }

  public Product getProductDetail(String customerId, String productId) {
    return productRepositoryPort.getProductByCustomer(customerId).stream()
        .filter(product -> productId.equals(String.valueOf(product.getId())))
        .findFirst()
        .orElse(null);
  }
}
