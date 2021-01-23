package com.smartchoice.product.repository.mapper;

import com.smartchoice.product.domain.Product;
import com.smartchoice.product.repository.model.ProductModel;
import org.springframework.stereotype.Service;

@Service("repoProductMapper")
public class ProductMapper {

    public ProductModel toProductModel(Product productDomain, ProductModel productModel) {
        productModel.setProductName(productDomain.getProductName());
        productModel.setDiscount(productDomain.getDiscount());
        productModel.setPrice(productDomain.getPrice());
        productModel.setId(productDomain.getId());
        productModel.setInformation(productDomain.getInformation());
        productModel.setShop(productDomain.getShop());
        productModel.setTitle(productDomain.getTitle());
        productModel.setUrl(productDomain.getUrl());
        return productModel;
    }

    public Product toProductDomain(ProductModel productModel, Product productDomain) {
        productDomain.setProductName(productModel.getProductName());
        productDomain.setDiscount(productModel.getDiscount());
        productDomain.setPrice(productModel.getPrice());
        productDomain.setId(productModel.getId());
        productDomain.setInformation(productModel.getInformation());
        productDomain.setShop(productModel.getShop());
        productDomain.setTitle(productModel.getTitle());
        productDomain.setUrl(productModel.getUrl());
        return productDomain;
    }
}
