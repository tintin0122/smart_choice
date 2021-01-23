package com.smartchoice.product.aop;

import com.smartchoice.product.domain.CustomerActivity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerActivityAspect {

  @Autowired private RabbitTemplate rabbitTemplate;

  @Value("${smartchoice.rabbitmq.exchange}")
  String topicExchangeName;

  @Value("${smartchoice.rabbitmq.routingKey}")
  String routingKey;

  @Before("execution(* com.smartchoice.product.rest.ProductController.searchProduct(..))")
  @Async
  public void logForFindById(JoinPoint joinPoint) {
    final Object[] joinPointArgs = joinPoint.getArgs();

    final CustomerActivity customerActivity = new CustomerActivity((String) joinPointArgs[0]);
    customerActivity.setAction((String) joinPointArgs[1]);
    rabbitTemplate.convertAndSend(topicExchangeName, routingKey, customerActivity);
  }
}
