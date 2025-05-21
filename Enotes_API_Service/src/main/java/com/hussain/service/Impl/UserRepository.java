package com.hussain.service.Impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Boolean existsByEmail(String email);
	
	User findByEmail(String email);


}
