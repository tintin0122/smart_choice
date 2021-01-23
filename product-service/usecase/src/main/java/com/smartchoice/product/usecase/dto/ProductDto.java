package com.smartchoice.product.usecase.dto;

import com.smartchoice.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {
    List<Product> products;
}
