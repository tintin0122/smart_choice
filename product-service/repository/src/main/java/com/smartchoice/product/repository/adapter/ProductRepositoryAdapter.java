package com.smartchoice.product.repository.adapter;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.repository.mapper.ProductMapper;
import com.smartchoice.product.repository.service.CustomerService;
import com.smartchoice.product.usecase.port.ProductRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepositoryAdapter implements ProductRepositoryPort {

  @Autowired private CustomerService customerService;

  @Autowired private ProductMapper productMapper;

  @Override
  public List<Product> getProductByCustomer(String customerId) {
    return Optional.ofNullable(customerService.findByCustomer(customerId))
        .map(
            customer ->
                customer.getProducts().stream()
                    .map(product -> productMapper.toProductDomain(product, new Product()))
                    .collect(Collectors.toList()))
        .orElse(Collections.emptyList());
  }

  @Override
  @Async
  public void updateProductToCustomerAsync(String customerId, List<Product> products) {
    customerService.updateCustomer(customerId, products);
  }
}
