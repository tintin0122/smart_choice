package com.sandbox.product.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandbox.product.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

  private static ObjectMapper objectMapper = new ObjectMapper();

  @GetMapping("/lazada/search")
  public ResponseEntity<List<ProductDto>> searchProductFromLazada(
      @RequestParam(name = "productName") String productName) throws IOException {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    final InputStream productStream = classloader.getResourceAsStream("product/lazada.json");

    final List<ProductDto> productDtos =
        objectMapper.readValue(productStream, new TypeReference<>() {});

    final List<ProductDto> products =
        productDtos.stream()
            .filter(item -> item.getProductName().contains(productName))
            .collect(Collectors.toList());

    return ResponseEntity.ok(products);
  }

  @GetMapping("/tiki/search")
  public ResponseEntity<List<ProductDto>> searchProductFromTiki(
      @RequestParam(name = "productName") String productName) throws IOException {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    final InputStream productStream = classloader.getResourceAsStream("product/tiki.json");

    final List<ProductDto> productDtos =
        objectMapper.readValue(productStream, new TypeReference<>() {});

    final List<ProductDto> products =
        productDtos.stream()
            .filter(item -> item.getProductName().contains(productName))
            .collect(Collectors.toList());

    return ResponseEntity.ok(products);
  }

  @GetMapping("/shopee/search")
  public ResponseEntity<List<ProductDto>> searchProductFromShopee(
      @RequestParam(name = "productName") String productName) throws IOException {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    final InputStream productStream = classloader.getResourceAsStream("product/shopee.json");

    final List<ProductDto> productDtos =
        objectMapper.readValue(productStream, new TypeReference<>() {});

    final List<ProductDto> products =
        productDtos.stream()
            .filter(item -> item.getProductName().contains(productName))
            .collect(Collectors.toList());

    return ResponseEntity.ok(products);
  }
}
