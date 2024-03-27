package com.college.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ObjectUtils;

import com.college.dto.CollegeDto;

public class Validation {

	public Map<String, Object> collegeDtoValidation(CollegeDto collegeDto) {

		Map<String, Object> validation = new HashMap<>();

		if(ObjectUtils.isEmpty(collegeDto.getName()))
		{
			validation.put("Name", "Name is empty");
		}
		
		return validation;
	}

}
