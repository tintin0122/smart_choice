package com.smartchoice.product.repository.service;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.repository.CustomerRepository;
import com.smartchoice.product.repository.mapper.ProductMapper;
import com.smartchoice.product.repository.model.CustomerModel;
import com.smartchoice.product.repository.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  private CustomerRepository customerRepository;
  private ProductMapper productMapper;

  @Autowired
  public CustomerService(CustomerRepository customerRepository, ProductMapper productMapper) {
    this.customerRepository = customerRepository;
    this.productMapper = productMapper;
  }

  public CustomerModel findByCustomer(String customerId) {
    return customerRepository.findById(customerId).orElse(null);
  }

  public CustomerModel updateCustomer(String customerId, List<Product> products) {
    final CustomerModel customerModel =
        customerRepository.findById(customerId).orElse(new CustomerModel());
    customerModel.setCustomerId(customerId);
    customerModel.setProducts(
        products.stream()
            .map(product -> productMapper.toProductModel(product, new ProductModel()))
            .collect(Collectors.toList()));
    return customerRepository.save(customerModel);
  }
}
