package com.deloitte.customer.customerservice.service;


import com.deloitte.customer.customerservice.model.Customer;
import com.deloitte.customer.customerservice.model.request.CustomerRequest;

public interface CustomerService {
	
	Iterable<Customer> fetchAllCustomers();
	
	Customer fetchCustomerById(String customerId);
	
	void deleteCustomer(String customerId);
	
	Customer addCustomer(CustomerRequest customerRequest);
	
	Customer updateCustomer(String customerId, CustomerRequest customerRequest);
	

}
