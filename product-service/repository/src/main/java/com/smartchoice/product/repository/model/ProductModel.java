package com.smartchoice.product.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Indexed
    private Long id;

    private String productName;

    private String shop;

    private String information;

    private String url;

    private String title;

    private BigDecimal price;

    private BigDecimal discount;

}
