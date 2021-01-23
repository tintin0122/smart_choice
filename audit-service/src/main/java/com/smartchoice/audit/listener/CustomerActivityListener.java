package com.smartchoice.audit.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartchoice.audit.dto.CustomerDto;
import com.smartchoice.audit.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerActivityListener implements MessageListener {

  private static final Logger logger = LogManager.getLogger(CustomerActivityListener.class);

  private CustomerService customerService;

  public CustomerActivityListener(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  @Async
  public void onMessage(Message message) {
    Optional.ofNullable(message)
        .map(Message::getBody)
        .filter(bytes -> bytes.length > 1)
        .ifPresent(
            bytes -> {
              final ObjectMapper mapper = new ObjectMapper();
              try {
                final CustomerDto customerDto =
                    mapper.readValue(new String(bytes), CustomerDto.class);
                customerService.insetCustomerActivity(customerDto);
              } catch (JsonProcessingException e) {
                logger.error(e);
              }
            });
  }
}
