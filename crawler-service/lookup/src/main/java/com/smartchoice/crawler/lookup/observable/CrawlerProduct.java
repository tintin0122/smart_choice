package com.smartchoice.crawler.lookup.observable;

import com.smartchoice.crawler.domain.Product;
import com.smartchoice.crawler.lookup.service.Observer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CrawlerProduct implements Observable {

  private List<Observer> apiResources = new ArrayList<>();

  @Value("${lookup.external.service.timeout}")
  private static Integer timeout;

  @Override
  public List<Product> searchProductByName(String productName) {
    final List<CompletableFuture<List<Product>>> productFutures =
        apiResources.stream()
            .map(api -> getProductInformation(api, productName))
            .collect(Collectors.toList());

    CompletableFuture<Void> allFutures =
        CompletableFuture.allOf(
            productFutures.toArray(new CompletableFuture[productFutures.size()]));

    CompletableFuture<List<List<Product>>> allFutureDone =
        allFutures.thenApply(
            future ->
                productFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()));

    return allFutureDone
        .thenApply(futures -> futures.stream().flatMap(List::stream).collect(Collectors.toList()))
        .join();
  }

  @Override
  public void addApiResource(Observer observer) {
    apiResources.add(observer);
  }

  @Override
  public void removeApiResource(Observer observer) {
    apiResources.remove(observer);
  }

  private static CompletableFuture<List<Product>> getProductInformation(
      Observer observer, String productName) {
    return CompletableFuture.supplyAsync(() -> observer.searchProduct(productName))
        .orTimeout(3, TimeUnit.SECONDS)
        .exceptionally(
            exception -> {
              System.err.println("exception: " + exception);
              return Collections.emptyList();
            });
  }
}
