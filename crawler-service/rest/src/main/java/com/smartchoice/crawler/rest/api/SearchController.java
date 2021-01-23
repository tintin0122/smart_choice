package com.smartchoice.crawler.rest.api;

import com.smartchoice.crawler.rest.dto.ProductDto;
import com.smartchoice.crawler.usecase.CrawlProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

  @Autowired private CrawlProductUseCase crawlProductUseCase;

  @GetMapping("/api/v1/products/search")
  public ResponseEntity<ProductDto> searchProduct(
      @RequestParam(value = "productName", required = false) String productName) {
    ProductDto dto = new ProductDto();
    dto.setProducts(crawlProductUseCase.getAllProductInformation(productName));
    return ResponseEntity.ok(dto);
  }
}
