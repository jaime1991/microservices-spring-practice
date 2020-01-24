package com.microservices.item.service.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservices.item.service.models.Product;

@FeignClient(name = "product-service")
public interface ProductRestClient {

	@GetMapping("/list")
	public List<Product> listProducts();
	
	@GetMapping("/watch/{id}")
	public Product detail(Long id);
	
}
