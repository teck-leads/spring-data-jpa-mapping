package com.techleasds.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techleasds.app.model.Customer;
import com.techleasds.app.model.OrderDetails;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	
	@Query("SELECT new com.techleasds.app.model.OrderDetails(c.id, c.name, p.productName) FROM Customer c JOIN c.products p")
	public List<OrderDetails> findCIdsCNamesPNames();

}
