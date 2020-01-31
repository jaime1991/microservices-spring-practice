package com.microservices.oauth.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.microservices.oauth.service.models.entity.User;

@FeignClient(name = "user-service")
public interface UserFeignClient {
	
	@GetMapping("users/search/by-username")
	public User findByUsername(@RequestParam("name") String name);

}
