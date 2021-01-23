package com.smartchoice.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    private Long id;
    private String productName;
    private String shop;
    private String information;
    private String url;
    private String title;
    private BigDecimal price;
    private BigDecimal discount;
}
