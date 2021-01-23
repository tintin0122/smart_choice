package com.smartchoice.crawler.lookup.service;

import com.smartchoice.crawler.domain.Product;

import java.util.List;

public interface Observer {
  List<Product> searchProduct(String productName);
}
