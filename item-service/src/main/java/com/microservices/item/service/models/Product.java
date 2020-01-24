package com.microservices.item.service.models;

import java.util.Date;

public class Product{

	private Long id;
	private String name;
	private Double price;
	private Date creteAt;
	private Integer port;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreteAt() {
		return creteAt;
	}
	public void setCreteAt(Date creteAt) {
		this.creteAt = creteAt;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
}
