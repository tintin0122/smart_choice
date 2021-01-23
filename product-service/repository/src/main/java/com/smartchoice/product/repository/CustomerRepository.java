package com.smartchoice.product.repository;

import com.smartchoice.product.repository.model.CustomerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, String> {
}
