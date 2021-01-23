package com.smartchoice.crawler.lookup.observable;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.lookup.service.Observer;

import java.util.List;

public interface Observable {
  List<Product> searchProductByName(String productName);

  void addApiResource(Observer observer);

  void removeApiResource(Observer observer);
}
