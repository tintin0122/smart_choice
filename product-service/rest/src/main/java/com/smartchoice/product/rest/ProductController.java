package com.smartchoice.product.rest;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.usecase.CompareProductUseCase;
import com.smartchoice.product.usecase.ProductDetailUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

  @Autowired private ProductDetailUseCase productDetailUseCase;

  @Autowired private CompareProductUseCase compareProductUseCase;

  @GetMapping("/api/v1/customers/{customer-id}/products/{product-id}")
  public ResponseEntity<Product> getProductDetail(
      @PathVariable("customer-id") String customerId,
      @PathVariable("product-id") String productId) {

    return ResponseEntity.ok(productDetailUseCase.getProductDetail(customerId, productId));
  }

  @GetMapping("/api/v1/customers/{customer-id}/products/search")
  public ResponseEntity<List<Product>> searchProduct(
      @PathVariable("customer-id") String customerId,
      @RequestParam("product-name") String productName) {

    return ResponseEntity.ok(compareProductUseCase.searchProductByName(customerId, productName));
  }
}
