package com.microservices.oauth.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.microservices.oauth.service.models.entity.User;
import com.microservices.oauth.service.services.IUserService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private IUserService userService;
	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		log.info("Success login: " + userDetails.getUsername());
		
		User user = userService.findByUsername(authentication.getName());
		if(user.getAttempts() != null && user.getAttempts() > 0) {
			user.setAttempts(0);
			userService.update(user, user.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		log.error("Error in login: " + exception.getMessage());
		try {
			User user = userService.findByUsername(authentication.getName());
			if(user.getAttempts() == null) {
				user.setAttempts(0);
			}
			
			log.info(String.format("current attempts: %d", user.getAttempts()));
			user.setAttempts(user.getAttempts() + 1);
			
			if(user.getAttempts() >= 3) {
				log.error(String.format("Maximun attempts exceded by user %s", user.getName()));
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());
		}catch(FeignException e) {
			log.error(String.format("The username %s not exist in the system", authentication.getName()));
		}
	}

}
