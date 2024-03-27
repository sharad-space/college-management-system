package com.college.service;

import com.college.dto.UserDtlsDto;

public interface UserDtlsService {

	// Access modifer , return type, method name (DataType variable name)
	public UserDtlsDto saveUser(UserDtlsDto userDtls);

	public Boolean existEmail(String email);

}
