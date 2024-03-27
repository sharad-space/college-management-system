package com.college.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.college.dto.DepartmentDto;

import jakarta.validation.Valid;

public interface DepartmentEndpoint {

	@PostMapping("/")
	public ResponseEntity<?> saveDepartment(@RequestBody @Valid DepartmentDto departmentDto);

	@GetMapping("/departments")
	public ResponseEntity<?> getAllDepartment();
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable Integer id);
	
	@GetMapping("/college/{collegeId}")
	public ResponseEntity<?> getDepartmentByCollage(@PathVariable Integer collegeId);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable Integer id); 

	// Update department : TASK
	
}
