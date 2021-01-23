package com.smartchoice.product.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
  @Value("${spring.rabbitmq.queue}")
  String queueName;

  @Value("${smartchoice.rabbitmq.exchange}")
  String topicExchangeName;

  @Value("${smartchoice.rabbitmq.routingKey}")
  String routingKey;

  @Value("${spring.rabbitmq.username}")
  String username;

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange(topicExchangeName);
  }

  @Bean
  public Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }

  @Bean
  public Queue myQueue() {
    return new Queue(queueName, true);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(jsonMessageConverter());
    return template;
  }
}
