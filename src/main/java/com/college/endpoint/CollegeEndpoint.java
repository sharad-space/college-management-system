package com.college.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.college.dto.CollegeDto;

import jakarta.validation.Valid;

public interface CollegeEndpoint {

	@PostMapping("/saveCollege")
	public ResponseEntity<?> saveCollege(@RequestBody @Valid CollegeDto collegeDto);

	@GetMapping("/get-all-college")
	public ResponseEntity<?> getAllCollege();

	@GetMapping("/get-college/{id}")
	public ResponseEntity<?> getCollegeById(@PathVariable Integer id);

}
