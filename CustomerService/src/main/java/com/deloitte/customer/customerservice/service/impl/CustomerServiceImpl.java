package com.deloitte.customer.customerservice.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.customer.customerservice.exception.ResourceNotFoundException;
import com.deloitte.customer.customerservice.model.Customer;
import com.deloitte.customer.customerservice.model.request.CustomerRequest;
import com.deloitte.customer.customerservice.repository.CustomerRepository;
import com.deloitte.customer.customerservice.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Iterable<Customer> fetchAllCustomers() {
       Iterable<Customer> customerList = customerRepository.findAll();
		return customerList;
	}

	@Override
	public Customer fetchCustomerById(String customerId) {
		 Optional<Customer> book = customerRepository.findById(customerId);
	        if (book.isPresent()) {
	            return book.get();
	        }
	        throw new ResourceNotFoundException("Cant find any customer with Customer Id: "+ customerId);
	}

	@Override
	public Customer addCustomer(CustomerRequest customerRequest) {
		if(customerRequest.getCustomerId() != null)
		{
		  Optional<Customer> customer = customerRepository.findById(customerRequest.getCustomerId());
	        if (customer.isPresent()) {
		        throw new ResourceNotFoundException("Customer already exists "+ customerRequest.getCustomerId());
	        }
	        
		}
	        Customer newCustomer = new Customer();
	        BeanUtils.copyProperties(customerRequest, newCustomer);
	        newCustomer.setCustomerId(customerRequest.getCustomerId());
	        return customerRepository.save(newCustomer);
	}

	@Override
	public Customer updateCustomer(String customerId, CustomerRequest customerRequest) {
		  Optional<Customer> customer = customerRepository.findById(customerId);
	        if (!customer.isPresent()) {
		        throw new ResourceNotFoundException("Cant find any customer with Customer Id: "+ customerRequest.getCustomerId());
	        }
	           Customer updatCustomer = customer.get();
	           updatCustomer.setLastName(customerRequest.getLastName());
	     	   updatCustomer.setFirstName(customerRequest.getFirstName());  
               updatCustomer.setAddress(customerRequest.getAddress());
	           return customerRepository.save(updatCustomer);
	}
	

	@Override
	public void deleteCustomer(String customerId) {
         customerRepository.deleteById(customerId);	
	}


}







