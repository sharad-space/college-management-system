package com.college.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.college.dto.UserDtlsDto;

public interface UserDtlsEndpoint {

	@PostMapping("/")
	public ResponseEntity<?> saveUser(@RequestBody UserDtlsDto userDtlsDto);

}
