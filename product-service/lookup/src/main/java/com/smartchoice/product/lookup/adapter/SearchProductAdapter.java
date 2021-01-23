package com.smartchoice.product.lookup.adapter;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.lookup.service.SearchProductService;
import com.smartchoice.product.usecase.port.SearchProductPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchProductAdapter implements SearchProductPort {

    @Autowired
    private SearchProductService searchProductService;

    @Override
    public List<Product> searchProductByName(String productName) {
        return searchProductService.searchProduct(productName);
    }
}
