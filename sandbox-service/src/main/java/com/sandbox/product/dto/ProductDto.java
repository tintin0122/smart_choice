package com.sandbox.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String productName;
    private String shop;
    private String information;
    private String url;
    private String title;
    private BigDecimal price;
    private BigDecimal discount;
}
