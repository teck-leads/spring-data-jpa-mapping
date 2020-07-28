package com.techleasds.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer prodId;
	private String productName;
	private Integer quantity;
	private Double price;
	@ManyToOne
	@JsonIgnore 
	private Customer customer;
	
	@Transient
	private String customerName;

}
