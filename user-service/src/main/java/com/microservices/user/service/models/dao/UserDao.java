package com.microservices.user.service.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.microservices.user.service.models.entity.User;

@RepositoryRestResource
public interface UserDao extends PagingAndSortingRepository<User, Long> {

	@RestResource(path = "by-username")
	public User findByUsername(@Param("name") String username);
}
