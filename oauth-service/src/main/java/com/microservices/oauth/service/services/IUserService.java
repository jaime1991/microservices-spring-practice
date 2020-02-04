package com.microservices.oauth.service.services;

import com.microservices.oauth.service.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);
	
}
