package com.college.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.college.handler.GenericResponseHandler;

public class CommonUtils {

	public static ResponseEntity<?> createBuildResponse(String message, Object object, HttpStatusCode status) {
		GenericResponseHandler build = GenericResponseHandler.builder().status(status.value()).message(message)
				.data(object).build();
		return build.create();
	}

}
