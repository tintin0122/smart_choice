package com.smartchoice.audit.repository;

import com.smartchoice.audit.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "customer-activity", path = "customer-activity")
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {}
