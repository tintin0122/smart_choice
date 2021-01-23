package com.smartchoice.audit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id private String id;
  private String username;
  private String action;
  private LocalDateTime createdDate;
}
