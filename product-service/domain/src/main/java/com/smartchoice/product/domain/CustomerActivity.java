package com.smartchoice.product.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CustomerActivity {
  private String id;
  private String username;
  private String action;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime createdDate;

  public CustomerActivity(String username) {
    this.id = UUID.randomUUID().toString();
    this.username = username;
    this.createdDate = LocalDateTime.now();
  }
}
