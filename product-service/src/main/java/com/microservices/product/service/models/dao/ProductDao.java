package com.microservices.product.service.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.microservices.product.service.models.entity.Product;

public interface ProductDao extends CrudRepository<Product, Long>{

}
