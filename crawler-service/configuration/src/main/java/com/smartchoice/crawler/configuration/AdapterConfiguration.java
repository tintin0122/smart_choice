package com.smartchoice.crawler.configuration;

import com.smartchoice.crawler.lookup.adapter.LookupProductAdapter;
import com.smartchoice.crawler.repository.adapter.ProductRepositoryAdapter;
import com.smartchoice.crawler.usecase.port.LookupProductPort;
import com.smartchoice.crawler.usecase.port.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {

    @Bean
    public LookupProductPort createLookupProductAdapter() {
        return new LookupProductAdapter();
    }

    @Bean
    public ProductRepositoryPort createProductRepositoryPort() {
        return new ProductRepositoryAdapter();
    }
}
