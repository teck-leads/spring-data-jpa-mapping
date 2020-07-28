package com.techleasds.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techleasds.app.model.Customer;
import com.techleasds.app.model.Product;
import com.techleasds.app.repository.CustomerRepository;
import com.techleasds.app.repository.ProductRepository;
@RestController
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;

//https://grokonez.com/spring-framework/spring-boot/spring-jpa-hibernate-one-to-many-association-postgresql-springboot-crud-restapis-post-get-put-delete-example
	@GetMapping(value = { "/products/{id}" })
	public ResponseEntity<Product> findProductById(@PathVariable("id") Integer id) {
		Optional<Product> findById = productRepository.findById(id);
		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}

	@GetMapping(value = { "/products" })
	public ResponseEntity<List<Product>> findAllProducts() {
		List<Product> findAllProducts = new ArrayList<>();
		findAllProducts = productRepository.findAll();

		if (findAllProducts.size() == 0) {
			Product p = new Product();
			findAllProducts.add(p);
			return new ResponseEntity<>(findAllProducts, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(findAllProducts, HttpStatus.OK);

	}
	
	@DeleteMapping(value = { "/products/{id}" })
	public ResponseEntity<List<Product>> deleteProductById(@PathVariable("id") Integer id) {
		Optional<Product> findById = productRepository.findById(id);
		if (findById.isPresent()) {
			productRepository.deleteById(id);
		}
		
		List<Product> findAll = productRepository.findAll();
		if (findAll.size() == 0) {
		
			Product p = new Product();
			List<Product> products = new ArrayList<>();
			products.add(p);
		
			findAll.add(p);
		}
		return new ResponseEntity<>(findAll, HttpStatus.OK);

	}
}
