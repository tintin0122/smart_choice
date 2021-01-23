package com.smartchoice.audit.service;

import com.smartchoice.audit.dto.CustomerDto;
import com.smartchoice.audit.model.Customer;
import com.smartchoice.audit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired private CustomerRepository customerRepository;

  public String insetCustomerActivity(CustomerDto customerDto) {
    final Customer customer = new Customer();
    customer.setId(customerDto.getId());
    customer.setUsername(customerDto.getUsername());
    customer.setAction(customerDto.getAction());
    customer.setCreatedDate(customerDto.getCreatedDate());

    return customerRepository.save(customer).getId();
  }
}
