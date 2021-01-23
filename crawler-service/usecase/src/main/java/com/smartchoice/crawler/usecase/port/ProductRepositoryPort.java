package com.smartchoice.crawler.usecase.port;

import com.smartchoice.crawler.domain.Product;

import java.util.List;

public interface ProductRepositoryPort {
    void insertProductsAsync(List<Product> products);

    List<Product> searchProductByName(String productName);
}
