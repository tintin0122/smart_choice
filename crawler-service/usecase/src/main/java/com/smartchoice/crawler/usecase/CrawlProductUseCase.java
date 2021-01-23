package com.smartchoice.crawler.usecase;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.usecase.port.LookupProductPort;
import com.smartchoice.crawler.usecase.port.ProductRepositoryPort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CrawlProductUseCase {

  private LookupProductPort lookupProductPort;

  private ProductRepositoryPort productRepositoryPort;

  public CrawlProductUseCase(LookupProductPort lookupProductPort, ProductRepositoryPort productRepositoryPort) {
    this.lookupProductPort = lookupProductPort;
    this.productRepositoryPort = productRepositoryPort;
  }

  public List<Product> getAllProductInformation(String productName) {
    final List<Product> products = productRepositoryPort.searchProductByName(productName);

    return Optional.ofNullable(products)
        .filter(list -> !list.isEmpty())
        .orElseGet(
            () -> {
              final List<Product> results =
                  lookupProductPort.crawlProductFromAllExternalApi(productName);
              return Optional.ofNullable(results)
                  .filter(list -> !list.isEmpty())
                  .map(
                      list -> {
                        productRepositoryPort.insertProductsAsync(list);
                        return list;
                      })
                  .orElseGet(Collections::emptyList);
            });
  }
}
