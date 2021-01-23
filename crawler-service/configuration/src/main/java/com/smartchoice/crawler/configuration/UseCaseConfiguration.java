package com.smartchoice.crawler.configuration;

import com.smartchoice.crawler.usecase.CrawlProductUseCase;
import com.smartchoice.crawler.usecase.port.LookupProductPort;
import com.smartchoice.crawler.usecase.port.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

  @Bean
  public CrawlProductUseCase createCrawlProductUseCase(
      LookupProductPort lookupProductAdapter, ProductRepositoryPort productRepositoryAdapter) {
    return new CrawlProductUseCase(lookupProductAdapter, productRepositoryAdapter);
  }
}
