package com.college.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dto.UserDtlsDto;
import com.college.model.UserDtls;
import com.college.repository.UserRepository;
import com.college.service.UserDtlsService;

@Service
public class UserDtlsServiceImpl implements UserDtlsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDtlsDto saveUser(UserDtlsDto userDtlsDto) {
		UserDtls user = mapper.map(userDtlsDto, UserDtls.class);
		UserDtls saveUser = userRepo.save(user);
		if(saveUser!=null)
		{
			//sendEmail(saveUser);
		}
		return mapper.map(saveUser, UserDtlsDto.class);
	}

	@Override
	public Boolean existEmail(String email) {
		Boolean existsByEmail = userRepo.existsByEmail(email);
		return existsByEmail;
	}

}
