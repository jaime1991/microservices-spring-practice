package com.microservices.item.service.models.service;

import java.util.List;

import com.microservices.item.service.models.Item;
import com.microservices.item.service.models.Product;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer quantity);

	public Product save(Product producto);

	public Product update(Product producto, Long id);

	public void delete(Long id);

}
