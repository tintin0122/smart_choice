package com.smartchoice.crawler.repository.dataset;

import com.smartchoice.crawler.repository.ProductRepository;
import com.smartchoice.crawler.repository.model.ProductModel;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

public class ProductData {

    public void create(ProductRepository productRepository) {
        final ProductModel productTiki = new ProductModel();
        productTiki.setProductName("test name");
        productTiki.setDiscount(new BigDecimal(10));
        productTiki.setPrice(new BigDecimal(10));
        productTiki.setInformation("test information");
        productTiki.setShop("TIKI");
        productTiki.setTitle("test title");
        productTiki.setUrl("url");
        productTiki.setCreatedDate(OffsetDateTime.now());

        final ProductModel productLazada = new ProductModel();
        productLazada.setProductName("test name");
        productLazada.setDiscount(new BigDecimal(10));
        productLazada.setPrice(new BigDecimal(10));
        productLazada.setInformation("test information");
        productLazada.setShop("LAZADA");
        productLazada.setTitle("test title");
        productLazada.setUrl("url");
        productTiki.setCreatedDate(OffsetDateTime.now());

        productRepository.saveAll(Arrays.asList(productTiki, productLazada));
    }
}
