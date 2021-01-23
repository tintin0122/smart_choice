package com.smartchoice.product.usecase.port;

import com.smartchoice.product.domain.Product;

import java.util.List;

public interface ProductRepositoryPort {

    List<Product> getProductByCustomer(String customerId);

    void updateProductToCustomerAsync(String customerId, List<Product> products);
}
