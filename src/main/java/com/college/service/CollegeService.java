package com.college.service;

import java.util.List;

import com.college.dto.CollegeDto;

public interface CollegeService {

	public CollegeDto saveCollage(CollegeDto collegeDto);

	public List<CollegeDto> getAllCollege();

	public CollegeDto getCollegeById(Integer id);

	public Boolean existCollege(String collegeName);

}
