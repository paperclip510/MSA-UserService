package com.shdh.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {

	@NotNull(message = "Email cannot be null")
	@Size(min = 2, message = "Email cannot be less than two characters")
	private String email;

	@NotNull(message = "Name cannot be null")
	@Size(min = 2, message = "Name cannot be less than two characters")
	private String name;

	@NotNull(message = "Password cannot be null")
	@Size(min = 8, message = "Password cannot be less than eight characters")
	private String pwd;

}