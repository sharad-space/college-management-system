package com.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.college.model.College;
import com.college.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public List<Department> findByCollegeId(Integer collegeId);

	// Get dapartment by collage name
	// @Query("select p from Department where p.college.name=:name and
	// p.college.address=:address")
	public List<Department> findByCollegeNameAndCollegeAddress(String name, String address);

	public Boolean existsByNameAndCollegeId(String departmentName, Integer collegeId);

	public Boolean existsByNameAndCollege(String departmentName, College college);

}
