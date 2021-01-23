package com.smartchoice.product.lookup.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.smartchoice.product.domain.Product;
import com.smartchoice.product.usecase.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SearchProductService {

  @Value("${service.crawler.id}")
  private String crawlerServiceId;

  @Autowired private EurekaClient eurekaClient;

  @Autowired private RestTemplate restTemplate;

  public List<Product> searchProduct(String productName) {
    final InstanceInfo instanceInfo =
        eurekaClient.getApplication(crawlerServiceId).getInstances().get(0);
    String url =
        "http://"
            + instanceInfo.getIPAddr()
            + ":"
            + instanceInfo.getPort()
            + "/api/v1/products/search?productName={productName}";
    final ResponseEntity<ProductDto> responseEntity =
        restTemplate.getForEntity(url, ProductDto.class, productName);

    return Optional.ofNullable(responseEntity)
        .filter(response -> HttpStatus.OK.equals(response.getStatusCode()))
        .map(ResponseEntity::getBody)
        .map(ProductDto::getProducts)
        .orElse(Collections.emptyList());
  }
}
