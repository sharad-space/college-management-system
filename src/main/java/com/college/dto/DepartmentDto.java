package com.college.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentDto {
	
	private Integer id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	@NotNull
	private Boolean status;

	@NotNull
	private Boolean isDeleted;
	
	@Valid
	private CollegeDto college;

}
