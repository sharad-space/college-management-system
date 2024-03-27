package com.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.DepartmentDto;
import com.college.endpoint.DepartmentEndpoint;
import com.college.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController implements DepartmentEndpoint {

	@Autowired
	private DepartmentService departmentService;

	@Override
	public ResponseEntity<?> saveDepartment(DepartmentDto departmentDto) {
		DepartmentDto saveDepartment = null;
		try {

			Boolean existDepartment = departmentService.existDepartmentByCollage(departmentDto.getName().trim(),
					departmentDto.getCollege().getId());

			if (existDepartment) {
				return new ResponseEntity<>("Department already Exist", HttpStatus.CONFLICT);
			} else {
				saveDepartment = departmentService.saveDepartment(departmentDto);

				if (ObjectUtils.isEmpty(saveDepartment)) {
					return new ResponseEntity<>("Department not saved", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllDepartment() {
		List<DepartmentDto> allDepartment = null;
		try {
			allDepartment = departmentService.getAllDepartment();
			if (CollectionUtils.isEmpty(allDepartment)) {
				return new ResponseEntity<>("Depart not available", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return new ResponseEntity<>(allDepartment, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getDepartmentById(Integer id) {
		DepartmentDto departmentById = null;
		try {
			departmentById = departmentService.getDepartmentById(id);
			if (ObjectUtils.isEmpty(departmentById)) {
				return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return new ResponseEntity<>(departmentById, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getDepartmentByCollage(Integer collegeId) {
		List<DepartmentDto> allDepartmentByCollege = null;
		try {
			allDepartmentByCollege = departmentService.getAllDepartmentByCollege(collegeId);
			if (CollectionUtils.isEmpty(allDepartmentByCollege)) {
				return new ResponseEntity<>("Depart not available", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return new ResponseEntity<>(allDepartmentByCollege, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteDepartment(Integer id) {
		try {
			Boolean deleteDepartment = departmentService.deleteDepartment(id);
			if (deleteDepartment) {
				return new ResponseEntity<>("Delete sucessfully", HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Error :{}", e.getMessage());
		}
		return new ResponseEntity<>("Department not Deleted", HttpStatus.NOT_FOUND);
	}

}
