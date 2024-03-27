package com.college.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.college.utils.CommonUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(NullPointerException.class)
//	public ResponseEntity<?> nullPointerExceptionHandler(Exception exception)
//	{
//		return new ResponseEntity<>(exception.getMessage(),HttpStatus.);
//	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(Exception ex) {
		ErrorResponse error = ErrorResponse.builder().status(HttpStatus.NOT_FOUND.ordinal()).message(ex.getMessage())
				.build();
		/*
		 * Map<String, Object> errorResponse = new LinkedHashMap<>();
		 * errorResponse.put("Status", HttpStatus.NOT_FOUND.ordinal());
		 * errorResponse.put("Message", ex.getMessage());
		 */
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationHandler(MethodArgumentNotValidException ex) {

		Map<String, Object> error = new HashMap<>();

		Map<String, Map<String, Object>> globalError = new HashMap<>();

		List<ObjectError> allErrors = ex.getAllErrors();

		allErrors.stream().forEach(e -> {

			String message = e.getDefaultMessage();
			String field = ((FieldError) (e)).getField();
			
			
			error.put(field, message);

			globalError.put(e.getObjectName(), error);
		});

		//return new ResponseEntity<>(globalError, HttpStatus.BAD_REQUEST);
		return CommonUtils.createBuildResponse("failed", globalError, HttpStatus.BAD_REQUEST);
	}
}
