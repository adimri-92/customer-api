package com.deloitte.customer.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.customer.customerservice.model.Customer;
import com.deloitte.customer.customerservice.model.request.CustomerRequest;
import com.deloitte.customer.customerservice.service.CustomerService;


@RestController
@RequestMapping(value = "/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping(path = "/customer")
	public ResponseEntity<Iterable<Customer>> fetchCustomerList() {
		return ResponseEntity.ok(customerService.fetchAllCustomers());

	}

	@GetMapping(path = "/customer/{customerId}")
	public ResponseEntity<Customer> fetchCustomerById(@PathVariable String customerId) {
		return ResponseEntity.ok(customerService.fetchCustomerById(customerId));

	}

	@PostMapping(path = "/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok(customerService.addCustomer(customerRequest));
	}

	@PatchMapping(path = "/customer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerRequest customerRequest,
			@PathVariable String customerId) {
		return ResponseEntity.ok(customerService.updateCustomer(customerId, customerRequest));
	}

	@DeleteMapping(path = "/customer/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.ok().build();
	}

}
