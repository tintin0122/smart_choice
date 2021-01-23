package com.smartchoice.product.configuration;

import com.smartchoice.product.lookup.adapter.SearchProductAdapter;
import com.smartchoice.product.repository.adapter.ProductRepositoryAdapter;
import com.smartchoice.product.usecase.port.ProductRepositoryPort;
import com.smartchoice.product.usecase.port.SearchProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {

  @Bean
  public ProductRepositoryPort createProductRepositoryPort() {
    return new ProductRepositoryAdapter();
  }

  @Bean
  public SearchProductPort createSearchProductPort() {
    return new SearchProductAdapter();
  }
}
