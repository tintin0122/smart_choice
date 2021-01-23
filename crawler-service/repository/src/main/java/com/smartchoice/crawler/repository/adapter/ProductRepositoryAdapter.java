package com.smartchoice.crawler.repository.adapter;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.repository.service.ProductService;
import com.smartchoice.crawler.usecase.port.ProductRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public class ProductRepositoryAdapter implements ProductRepositoryPort {

    @Autowired
    private ProductService productService;

    @Override
    @Async
    public void insertProductsAsync(List<Product> products) {
        productService.insertProducts(products);
    }

    @Override
    public List<Product> searchProductByName(String productName) {
        return productService.getProductByName(productName);
    }
}
