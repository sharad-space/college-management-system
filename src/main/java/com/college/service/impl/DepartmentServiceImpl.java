package com.college.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.college.dto.DepartmentDto;
import com.college.exception.ResourceNotFoundException;
import com.college.model.Department;
import com.college.repository.DepartmentRepository;
import com.college.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department saveDepartment = null;
		try {
			Department department = mapper.map(departmentDto, Department.class);
			saveDepartment = departmentRepo.save(department);

			if (ObjectUtils.isEmpty(saveDepartment)) {
				return null;
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}

		return mapper.map(saveDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {
		List<DepartmentDto> filterDepartmentDto = null;
		try {
			List<Department> allDepartment = departmentRepo.findAll();

			List<Department> filterDepartment = allDepartment.stream()
					.filter(department -> department.getStatus() && !department.getIsDeleted())
					.collect(Collectors.toList());

			filterDepartmentDto = filterDepartment.stream().map(depart -> mapper.map(depart, DepartmentDto.class))
					.collect(Collectors.toList());

			if (CollectionUtils.isEmpty(filterDepartmentDto)) {
				return null;
			}

		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
			e.printStackTrace();
		}

		return filterDepartmentDto;
	}

	@Override
	public List<DepartmentDto> getAllDepartmentByCollege(Integer collegeId) {

		List<DepartmentDto> collegeDepartmentDtos = null;
		try {
			List<Department> collegeDepartments = departmentRepo.findByCollegeId(collegeId);

			collegeDepartmentDtos = collegeDepartments.stream().map(depart -> mapper.map(depart, DepartmentDto.class))
					.collect(Collectors.toList());

			if (CollectionUtils.isEmpty(collegeDepartmentDtos)) {
				return null;
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return collegeDepartmentDtos;
	}

	@Override
	public DepartmentDto getDepartmentById(Integer id) {
		DepartmentDto departmentDto = null;

		try {
			Department department = departmentRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Department not found with id=" + id));

			if (!ObjectUtils.isEmpty(department)) {
				departmentDto = mapper.map(department, DepartmentDto.class);
			}

		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}

		return departmentDto;
	}

	@Override
	public Boolean deleteDepartment(Integer departmentId) {
		try {
			Department department = departmentRepo.findById(departmentId)
					.orElseThrow(() -> new ResourceNotFoundException("Department not found with id=" + departmentId));

			if (!ObjectUtils.isEmpty(department)) {

				department.setIsDeleted(true);
				Department deleteDepartment = departmentRepo.save(department);

				if (!ObjectUtils.isEmpty(deleteDepartment) && deleteDepartment.getIsDeleted()) {
					return true;
				}
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean existDepartmentByCollage(String departmentName, Integer collegeId) {
		return departmentRepo.existsByNameAndCollegeId(departmentName, collegeId);
	}

}
