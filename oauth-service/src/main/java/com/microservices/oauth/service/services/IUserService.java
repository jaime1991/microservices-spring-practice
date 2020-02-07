package com.microservices.oauth.service.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.oauth.service.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);
	
	public User update(User user, Long id);
	
}
