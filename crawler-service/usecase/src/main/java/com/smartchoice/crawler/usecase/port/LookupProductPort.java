package com.smartchoice.crawler.usecase.port;

import com.smartchoice.crawler.domain.Product;

import java.util.List;

public interface LookupProductPort {
    List<Product> crawlProductFromAllExternalApi(String productName);
}
