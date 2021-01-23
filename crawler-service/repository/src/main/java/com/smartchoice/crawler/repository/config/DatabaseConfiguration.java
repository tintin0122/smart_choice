package com.smartchoice.crawler.repository.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@PropertySource(value = "classpath:repository.properties")
@EnableJpaRepositories("com.smartchoice.crawler.repository")
@EntityScan("com.smartchoice.crawler.repository")
public class DatabaseConfiguration implements AsyncConfigurer {
  private static final Logger logger = LogManager.getLogger(DatabaseConfiguration.class);

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(20);
    executor.setMaxPoolSize(100);
    executor.setQueueCapacity(75);
    executor.setThreadNamePrefix("repository-worker-exec-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (throwable, method, objects) ->
        logger.error("Repository asynchronously call: " + throwable.getMessage(), throwable);
  }
}
