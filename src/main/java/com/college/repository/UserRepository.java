package com.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.model.UserDtls;


public interface UserRepository extends JpaRepository<UserDtls, Integer> {

	Boolean existsByEmail(String email);

}
