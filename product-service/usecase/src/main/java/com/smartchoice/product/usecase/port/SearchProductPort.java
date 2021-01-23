package com.smartchoice.product.usecase.port;

import com.smartchoice.product.domain.Product;

import java.util.List;

public interface SearchProductPort {
    List<Product> searchProductByName(String productName);
}
