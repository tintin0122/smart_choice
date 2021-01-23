package com.smartchoice.audit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartchoice.audit.listener.CustomerActivityListener;
import com.smartchoice.audit.service.CustomerService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
  @Value("${spring.rabbitmq.queue}")
  String queueName;

  @Bean
  public MessageConverter jsonMessageConverter(ObjectMapper mapper) {
    return new Jackson2JsonMessageConverter(mapper);
  }

  @Bean
  public Queue queue() {
    return new Queue(queueName, true);
  }

  @Bean
  public MessageListenerContainer messageListenerContainer(
      ConnectionFactory connectionFactory, CustomerService customerService) {
    final SimpleMessageListenerContainer simpleMessageListenerContainer =
        new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueues(queue());
    simpleMessageListenerContainer.setMessageListener(
        new CustomerActivityListener(customerService));
    return simpleMessageListenerContainer;
  }
}
