package com.smartchoice.product.configuration;

import com.smartchoice.product.usecase.CompareProductUseCase;
import com.smartchoice.product.usecase.ProductDetailUseCase;
import com.smartchoice.product.usecase.port.ProductRepositoryPort;
import com.smartchoice.product.usecase.port.SearchProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

  @Bean
  public ProductDetailUseCase createProductDetailUseCase(
      ProductRepositoryPort searchProductAdapter) {
    return new ProductDetailUseCase(searchProductAdapter);
  }

  @Bean
  public CompareProductUseCase createCompareProductUseCase(
      ProductRepositoryPort productRepositoryAdapter, SearchProductPort searchProductAdapter) {
    return new CompareProductUseCase(productRepositoryAdapter, searchProductAdapter);
  }
}
