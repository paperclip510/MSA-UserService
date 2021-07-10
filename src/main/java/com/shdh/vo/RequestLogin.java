package com.shdh.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RequestLogin {
	@NotNull(message = "Email cannot be null")
	@Size(min = 2, message = "Email not be less then two characters")
	private String email;

	@NotNull(message = "Pasword cannot be null")
	@Size(min = 8, message = "Email must be quals or grater then eight characters")
	private String password;
}
