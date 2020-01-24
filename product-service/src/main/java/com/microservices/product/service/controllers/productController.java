package com.microservices.product.service.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.models.entity.Product;
import com.microservices.product.service.models.service.IProductService;

@RestController
public class productController {

	@Value("${server.port}")
	private Integer port;
	@Autowired
	private IProductService productService;
	
	@GetMapping("/list")
	public List<Product> listProducts(){
		return productService.findAll()
				.stream()
				.map(p-> {
					p.setPort(port);
					return p;
				})
				.collect(Collectors.toList());
	}
	
	@GetMapping("/watch/{id}")
	public Product detail(@PathVariable Long id){
		Product p = productService.findById(id);
		p.setPort(port);
		return p;
	}
	
}
