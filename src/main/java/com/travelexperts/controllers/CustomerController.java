package com.travelexperts.controllers;


import java.util.ArrayList;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelexperts.model.Customer;
import com.travelexperts.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/customers")

public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(CustomerController.class); 
	
	@Autowired
	CustomerService custService;

	@RequestMapping("/all")  
	public ArrayList<Customer> getAll() {
		//logger.info("all was called");
		return custService.getAll();
	}

	@RequestMapping("{id}")
	public Customer getCustomer(@PathVariable("id") String id) {
		return custService.getCustomer(id);
	}
	
	@PostMapping("/insert_customer")
	public void addCustomer(Customer cust) {
		custService.insertCustomer(cust);
	}
	
	@PutMapping("/update_customer")
	public void updateCustomer(Customer cust) {
		custService.updateCustomer(cust);
	}
}
