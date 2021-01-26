package com.deloitte.customer.customerservice.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.deloitte.customer.customerservice.model.Customer;

@EnableScan
public interface CustomerRepository extends CrudRepository<Customer, String>{

}
