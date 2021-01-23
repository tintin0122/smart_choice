package com.smartchoice.audit.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDto {
    private String id;
    private String username;
    private String action;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;
}
