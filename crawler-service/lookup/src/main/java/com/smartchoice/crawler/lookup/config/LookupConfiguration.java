package com.smartchoice.crawler.lookup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:lookup.properties")
public class LookupConfiguration {
}
