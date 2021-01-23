package com.smartchoice.product.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Data
@RedisHash("customerSearch")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    @Id
    @Indexed
    private String customerId;

    private List<ProductModel> products;
}
