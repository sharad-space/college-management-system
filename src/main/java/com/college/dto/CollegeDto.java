package com.college.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollegeDto {

	private Integer id;

	@NotEmpty(message = "name shouldn't empty")
	@Size(min = 3, max = 10, message = "name length min 3 and max 10 character")
	@Pattern(regexp = "[a-zA-Z][a-zA-Z ]*", message = "name is invalid")
	private String name;

	@NotEmpty(message = "address shouldn't empty")
	private String address;

	@NotEmpty(message = "city shouldn't empty")
	private String city;

	@NotEmpty(message = "state shouldn't empty")
	private String state;

	@NotEmpty(message = "pincode shouldn't empty")
	private String pincode;

	@NotNull
	private Boolean status;

	@NotNull
	private Boolean isDeleted;
}
