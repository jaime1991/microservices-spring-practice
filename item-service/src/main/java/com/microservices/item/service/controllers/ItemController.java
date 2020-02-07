package com.microservices.item.service.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microservices.item.service.models.Item;import com.microservices.item.service.models.Product;
import com.microservices.item.service.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Value("${config.text}")
	private String text;
	@Autowired
	private Environment env;
	
	@GetMapping("/list")
	public List<Item> list(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoError")
	@GetMapping("/watch/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity){
		
		return itemService.findById(id, quantity);
	}
	
	public Item metodoError(Long id, Integer quantity) {
		Product p = new Product();
		p.setId(10L);
		p.setName("Huawei");
		p.setPrice(110.0);
		p.setCreteAt(new Date());
		return new Item(p, quantity);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product crear(@RequestBody Product producto) {
		return itemService.save(producto);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product producto, @PathVariable Long id) {
		return itemService.update(producto, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.delete(id);
	}
	
	@GetMapping
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port){
		Map<String, String> json = new HashedMap();
		json.put("texto", text);
		json.put("port", port);
		json.put("env", env.getActiveProfiles()[0]);
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
}
