package com.smartchoice.crawler.repository.mapper;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.repository.model.ProductModel;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component("repoProductMapper")
public class ProductMapper {
    public ProductModel toProductModel(final Product domain, final ProductModel model) {
        model.setProductName(domain.getProductName());
        model.setInformation(domain.getInformation());
        model.setPrice(domain.getPrice());
        model.setShop(domain.getShop());
        model.setTitle(domain.getTitle());
        model.setUrl(domain.getUrl());
        model.setDiscount(domain.getDiscount());
        model.setCreatedDate(OffsetDateTime.now());
        return model;
    }

    public Product toProductDomain(final ProductModel model, final Product domain) {
        domain.setId(model.getId());
        domain.setProductName(model.getProductName());
        domain.setInformation(model.getInformation());
        domain.setPrice(model.getPrice());
        domain.setShop(model.getShop());
        domain.setTitle(model.getTitle());
        domain.setUrl(model.getUrl());
        domain.setDiscount(model.getDiscount());
        return domain;
    }
}
