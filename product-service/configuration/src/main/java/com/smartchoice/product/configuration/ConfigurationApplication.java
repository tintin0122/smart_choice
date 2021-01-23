package com.smartchoice.product.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(value = {"com.smartchoice"})
@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class ConfigurationApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigurationApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }
}
