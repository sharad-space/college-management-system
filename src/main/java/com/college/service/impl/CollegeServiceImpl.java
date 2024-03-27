package com.college.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.college.dto.CollegeDto;
import com.college.exception.ResourceNotFoundException;
import com.college.model.College;
import com.college.repository.CollegeRepository;
import com.college.service.CollegeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private CollegeRepository collegeRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CollegeDto saveCollage(CollegeDto collegeDto) {
		College saveCollege = null;
		try {
			College college = mapper.map(collegeDto, College.class);
			saveCollege = collegeRepo.save(college);
			if (ObjectUtils.isEmpty(saveCollege)) {
				return null;
			}

		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return mapper.map(saveCollege, CollegeDto.class);
	}

	@Override
	public List<CollegeDto> getAllCollege() {
		List<CollegeDto> collegeDto = null;
		try {
			List<College> colleges = collegeRepo.findAll();
			collegeDto = colleges.stream().map(college -> mapper.map(college, CollegeDto.class))
					.collect(Collectors.toList());
			if (CollectionUtils.isEmpty(colleges)) {
				return null;
			}

		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return collegeDto;
	}

	@Override
	public CollegeDto getCollegeById(Integer id) {

		CollegeDto collegeDto = null;
		try {
			College college = collegeRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("College not found with id=" + id));

//				Optional<College> college = collegeRepo.findById(id);
//				if (!college.isPresent()) {
//					return null;
//				}
			collegeDto = mapper.map(college, CollegeDto.class);
		} catch (Exception e) {
			log.error("Error : {}", e.getMessage());
		}
		return collegeDto;
	}

	@Override
	public Boolean existCollege(String collegeName) {
		Boolean existsByName = null;
		try {
			existsByName = collegeRepo.existsByName(collegeName);
		} catch (Exception e) {
			log.error("Error : {}", e.getMessage());
		}
		return existsByName;
	}

}
