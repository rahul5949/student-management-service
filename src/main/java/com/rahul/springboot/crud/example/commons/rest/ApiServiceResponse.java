package com.rahul.springboot.crud.example.commons.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiServiceResponse {
	int statusCode;
	
	
	@JsonProperty("is_error")
	boolean isError;
	
	@JsonProperty("msg")
	String message;
	
	@JsonProperty("data")
	Object orderId;
	

}
