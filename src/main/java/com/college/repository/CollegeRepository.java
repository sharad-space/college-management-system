package com.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.model.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {

	public Boolean existsByName(String name);

}
