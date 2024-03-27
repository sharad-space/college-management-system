package com.college.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

	private Integer status;
	private String message;

	public ErrorResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ErrorResponse() {
		super();
	}

}
