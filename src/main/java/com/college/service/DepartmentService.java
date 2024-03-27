package com.college.service;

import java.util.List;

import com.college.dto.DepartmentDto;

public interface DepartmentService {

	public DepartmentDto saveDepartment(DepartmentDto departmentDto);

	public List<DepartmentDto> getAllDepartment();

	public List<DepartmentDto> getAllDepartmentByCollege(Integer collegeId);

	public DepartmentDto getDepartmentById(Integer id);

	public Boolean deleteDepartment(Integer id);

	public Boolean existDepartmentByCollage(String departmentName,Integer collegeId);
	
}
