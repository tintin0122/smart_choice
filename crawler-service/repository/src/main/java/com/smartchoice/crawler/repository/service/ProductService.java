package com.smartchoice.crawler.repository.service;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.repository.ProductRepository;
import com.smartchoice.crawler.repository.mapper.ProductMapper;
import com.smartchoice.crawler.repository.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

  private ProductRepository productRepository;

  private ProductMapper productMapper;

  @Autowired
  public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Transactional
  public List<ProductModel> insertProducts(List<Product> products) {
    final List<ProductModel> productModels =
        products.stream()
            .map(product -> productMapper.toProductModel(product, new ProductModel()))
            .collect(Collectors.toList());
    return new ArrayList<>(productRepository.saveAll(productModels));
  }

  public List<Product> getProductByName(String productName) {
    final List<ProductModel> productModels =
            productRepository.searchProductByName(productName.toLowerCase());
    return productModels.stream()
        .map(model -> productMapper.toProductDomain(model, new Product()))
        .collect(Collectors.toList());
  }
}
