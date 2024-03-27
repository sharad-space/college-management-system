package com.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.UserDtlsDto;
import com.college.endpoint.UserDtlsEndpoint;
import com.college.service.UserDtlsService;

@RestController
@RequestMapping("/user/")
public class UserDtlsController implements UserDtlsEndpoint {

	@Autowired
	private UserDtlsService userDtlsService;

	@Override
	public ResponseEntity<?> saveUser(UserDtlsDto userDtlsDto) {
		UserDtlsDto saveUser = null;
		try {
			Boolean existEmail = userDtlsService.existEmail(userDtlsDto.getEmail());
			if (existEmail) {
				return new ResponseEntity<>("Email id already exists", HttpStatus.CONFLICT);
			} else {
				saveUser = userDtlsService.saveUser(userDtlsDto);
				if (ObjectUtils.isEmpty(saveUser)) {
					return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}

}
