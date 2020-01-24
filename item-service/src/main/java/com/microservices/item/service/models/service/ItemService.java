package com.microservices.item.service.models.service;

import java.util.List;

import com.microservices.item.service.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer quantity);
	
}
