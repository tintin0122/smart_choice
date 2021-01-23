package com.smartchoice.crawler.rest.dto;

import com.smartchoice.crawler.domain.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ProductDto {
    List<Product> products;
}
