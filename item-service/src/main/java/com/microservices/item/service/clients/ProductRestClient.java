package com.microservices.item.service.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.item.service.models.Product;

@FeignClient(name = "product-service")
public interface ProductRestClient {
	
	@GetMapping("/list")
	List<Product> listProducts();
	
	@GetMapping("/watch/{id}")
	Product detail(@PathVariable("id") Long id);
	
	@PostMapping("/create")
	public Product crear(@RequestBody Product producto);
	
	@PutMapping("/edit/{id}")
	public Product edit(@RequestBody Product producto, @PathVariable("id") Long id);
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id);

}

