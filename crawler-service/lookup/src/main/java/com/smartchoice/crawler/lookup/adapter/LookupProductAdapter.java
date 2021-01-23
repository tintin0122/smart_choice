package com.smartchoice.crawler.lookup.adapter;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.lookup.observable.Observable;
import com.smartchoice.crawler.usecase.port.LookupProductPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LookupProductAdapter implements LookupProductPort {

  @Autowired
  private Observable crawlerProduct;

  @Override
  public List<Product> crawlProductFromAllExternalApi(String productName) {
    return crawlerProduct.searchProductByName(productName);
  }

}
