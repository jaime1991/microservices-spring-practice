package com.microservices.product.service.models.service;

import java.util.List;

import com.microservices.product.service.models.entity.Product;

public interface IProductService {
	
	public List<Product> findAll();
	public Product findById(Long id);

}
