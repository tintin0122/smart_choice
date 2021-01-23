package com.smartchoice.crawler.lookup.service;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.lookup.observable.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LazadaSearchProductService implements Observer {

  @Value("${lookup.resource.endpoint.lazada}")
  private String searchEndpoint;

  private static final String SEARCH_PRODUCT_PARAMS = "?productName={productName}";

  @Autowired private RestTemplate restTemplate;

  public LazadaSearchProductService(Observable crawlerProduct) {
    crawlerProduct.addApiResource(this);
  }

  @Override
  public List<Product> searchProduct(String productName) {

    final ResponseEntity<List<Product>> responseEntity =
        restTemplate.exchange(
            searchEndpoint + SEARCH_PRODUCT_PARAMS,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Product>>() {},
            productName);

    return Optional.ofNullable(responseEntity)
        .filter(response -> HttpStatus.OK.equals(response.getStatusCode()))
        .map(ResponseEntity::getBody)
        .orElse(Collections.emptyList());
  }
}
