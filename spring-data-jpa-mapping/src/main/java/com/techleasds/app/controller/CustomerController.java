package com.techleasds.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleasds.app.model.Customer;
import com.techleasds.app.model.OrderDetails;
import com.techleasds.app.model.Product;
import com.techleasds.app.repository.CustomerRepository;
import com.techleasds.app.repository.ProductRepository;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	 @Transactional
	@PostMapping(value = { "/customers" })
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer saved = customerRepository.save(customer);
		if(customerRepository.findById(saved.getId()).isPresent()) {
			
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(saved, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = { "/customers" })
	public ResponseEntity<List<Customer>> findAllCustomers() {
		List<Customer> findAll = customerRepository.findAll();
		if (findAll.size() == 0) {
			Customer c = new Customer();
			Product p = new Product();
			List<Product> products = new ArrayList<>();
			products.add(p);
			c.setProducts(products);
			findAll.add(c);
		}
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	@GetMapping(value = { "/customers/{id}" })
	public ResponseEntity<Customer> findCustomerById(@PathVariable("id") Integer id) {
		Optional<Customer> findById = customerRepository.findById(id);
		if (findById.isPresent()) {
			Customer customer = findById.get();
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Customer(), HttpStatus.NO_CONTENT);
		}

	}
	/**
	 * Using JPA join query 
	 * @return
	 */
	@GetMapping(value = { "/orders" })
	public ResponseEntity<List<OrderDetails>> findOrderDetails() {
		List<OrderDetails> findCIdsCNamesPNames = customerRepository.findCIdsCNamesPNames();
		if (findCIdsCNamesPNames.size() == 0) {
			OrderDetails od=new OrderDetails();
			findCIdsCNamesPNames.add(od);
		}
		return new ResponseEntity<>(findCIdsCNamesPNames, HttpStatus.OK);
	}
	
	@PutMapping(value = { "/customers" })
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer updated = customerRepository.save(customer);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping(value = { "/customers/{id}" })
	public ResponseEntity<List<Customer>> deleteCustomerById(@PathVariable("id") Integer id) {
		
		Optional<Customer> findById = customerRepository.findById(id);
		if (findById.isPresent()) {
			customerRepository.deleteById(id);
		}
		
		List<Customer> findAll = customerRepository.findAll();
		if (findAll.size() == 0) {
			Customer c = new Customer();
			Product p = new Product();
			List<Product> products = new ArrayList<>();
			products.add(p);
			c.setProducts(products);
			findAll.add(c);
		}
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}


}
