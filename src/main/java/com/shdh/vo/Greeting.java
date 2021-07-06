package com.shdh.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Greeting {

	@Value("${greeting.message}")
	private String message;
}
