package com.microservices.item.service.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.item.service.clients.ProductRestClient;
import com.microservices.item.service.models.Item;
import com.microservices.item.service.models.Product;

@Service
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductRestClient productRestClient;
	
	@Override
	public List<Item> findAll() {
		return productRestClient.listProducts()
				.stream()
				.map(p -> new Item(p, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(productRestClient.detail(id), quantity);
	}
	
	@Override
	public Product save(Product producto) {
		return productRestClient.crear(producto);
	}

	@Override
	public Product update(Product producto, Long id) {
		return productRestClient.edit(producto, id);
	}

	@Override
	public void delete(Long id) {
		productRestClient.delete(id);
	}

}
