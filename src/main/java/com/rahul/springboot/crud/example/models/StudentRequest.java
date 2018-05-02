package com.rahul.springboot.crud.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
	Long enRoll;
	String firstName;
	String lastName;
	String email;
}
