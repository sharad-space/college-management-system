package com.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.CollegeDto;
import com.college.endpoint.CollegeEndpoint;
import com.college.handler.GenericResponseHandler;
import com.college.service.CollegeService;
import com.college.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/college/")
public class CollegeController implements CollegeEndpoint {

	@Autowired
	private CollegeService collegeService;

//	@Override
//	public ResponseEntity<?> saveCollege(CollegeDto collegeDto) {
//		CollegeDto saveCollegeDto = null;
//
//		try {
//			Boolean exist = collegeService.existCollege(collegeDto.getName());
//			if (exist) {
//			//	return CommonUtils.createBuildResponse("Collage name already exists", null, HttpStatus.CONFLICT);
//				// return new ResponseEntity<>("Collage name already exists",
//				// HttpStatus.CONFLICT);
//			} else {
//				saveCollegeDto = collegeService.saveCollage(collegeDto);
//				if (ObjectUtils.isEmpty(saveCollegeDto)) {
//				//	return CommonUtils.createBuildResponse("Collage not Saved", null, HttpStatus.INTERNAL_SERVER_ERROR);
//					// return new ResponseEntity<>("Collage not saved",
//					// HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}
//		} catch (Exception e) {
//			log.error("Error:{}", e.getMessage());
//			e.printStackTrace();
//		}
//		// return new ResponseEntity<>(saveCollegeDto, HttpStatus.CREATED);
//		//return CommonUtils.createBuildResponse("Collage saved success", saveCollegeDto, HttpStatus.CREATED);
//	}

	@Override
	public ResponseEntity<?> saveCollege(CollegeDto collegeDto) {
		CollegeDto saveCollegeDto = null;
		GenericResponseHandler handler = null;
		ResponseEntity<?> response = null;
		try {
			Boolean exist = collegeService.existCollege(collegeDto.getName());
			if (exist) {
				return CommonUtils.createBuildResponse("failed", "Collage name already exists", HttpStatus.CONFLICT);
//				handler = GenericResponseHandler.builder().status(HttpStatus.CONFLICT.value())
//						.message("Collage name already exists").build();
//				return handler.create();
				// return new ResponseEntity<>(handler, HttpStatus.CONFLICT);
//				return new ResponseEntity<>("Collage name already exists",HttpStatus.CONFLICT);
			} else {
				saveCollegeDto = collegeService.saveCollage(collegeDto);
				if (ObjectUtils.isEmpty(saveCollegeDto)) {
					return CommonUtils.createBuildResponse("failed", "Collage not Saved",
							HttpStatus.INTERNAL_SERVER_ERROR);
//					handler = GenericResponseHandler.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//							.message("Collage not saved").build();
//					return handler.create();
					// return new ResponseEntity<>(handler, HttpStatus.INTERNAL_SERVER_ERROR);

//					return new ResponseEntity<>("Collage not saved",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
			e.printStackTrace();
		}
		return CommonUtils.createBuildResponse("success", saveCollegeDto, HttpStatus.CREATED);
		// return new ResponseEntity<>(saveCollegeDto, HttpStatus.CREATED);
//		handler = GenericResponseHandler.builder().status(HttpStatus.CREATED.value()).data(saveCollegeDto)
//				.message("Collage saved success").build();
//
//		return handler.create();

		// return new ResponseEntity<>(handler, HttpStatus.CREATED);
		// return new ResponseEntity<>(saveCollegeDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllCollege() {
		List<CollegeDto> colleges = null;
		try {
			colleges = collegeService.getAllCollege();
			if (CollectionUtils.isEmpty(colleges)) {
				return new ResponseEntity<>("Colleges not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(colleges, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCollegeById(Integer id) {
		CollegeDto college = null;
		try {
			college = collegeService.getCollegeById(id);
			if (ObjectUtils.isEmpty(college)) {
				return new ResponseEntity<>("Colleges not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(college, HttpStatus.OK);
	}

}
