package com.microservices.oauth.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservices.oauth.service.clients.UserFeignClient;
import com.microservices.oauth.service.models.entity.User;

@Service
public class UserService implements IUserService, UserDetailsService{

	@Autowired
	private UserFeignClient client;
	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = client.findByUsername(username);

		if (user == null) {
			log.error("Error in login. Username not found");
			throw new UsernameNotFoundException("Error in login. Username not found");
		}

		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> log.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		log.info("Username: " + username);
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(),
				user.getEnabled(), true, true, true, authorities);
	}

	@Override
	public User findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public User update(User user, Long id) {
		return client.update(user, id);
	}

}
