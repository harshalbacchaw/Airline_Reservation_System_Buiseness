package com.wipro.velocity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.velocity.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
