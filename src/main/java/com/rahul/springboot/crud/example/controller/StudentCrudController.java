package com.rahul.springboot.crud.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.springboot.crud.example.commons.rest.ApiServiceResponse;
import com.rahul.springboot.crud.example.entity.Student;
import com.rahul.springboot.crud.example.models.StudentRequest;
import com.rahul.springboot.crud.example.service.IStudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentCrudController {

	@Autowired
	IStudentService studentService;

	@GetMapping("/health")
	public ResponseEntity<String> index() {
		return ResponseEntity.ok("Welcome to Student Management System");
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public ResponseEntity handle() {
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(path = "/home/student/{id}")
	public ResponseEntity<ApiServiceResponse> retrieveStudent(@PathVariable String id) {
		log.info("action=\"displayAllStudent\", description=\"display all student record\" ");
		ApiServiceResponse serviceResponse = null;
		try {
			Optional<Student> student = studentService.retrieveStudent(Long.parseLong(id));
			log.info("=========== Student  ======== " + student);
			serviceResponse = new ApiServiceResponse(200, false, "sucessfully retrieve a student", student);
		} catch (Exception e) {
			log.error("action=\"retrieveStudent\", description=\"INTERNAL_SERVER_ERROR\"", e);
			serviceResponse = new ApiServiceResponse(500, true, "failed to retrieve a student record", null);
		}
		log.debug("action=\"retrieveStudent\", description=\"response student\"", serviceResponse);
		return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
	}

	@GetMapping(path = "/home")
	public ResponseEntity<ApiServiceResponse> retrieveAllStudents() {
		log.info("action=\"retrieveAllStudents\", description=\"display all student record\" ");
		ApiServiceResponse serviceResponse = null;
		try {
			Iterable<Student> students = studentService.retrieveAllStudents();
			log.info("=========== Students  ======== " + students);
			serviceResponse = new ApiServiceResponse(200, false, "sucessfully retrieve all students", students);
		} catch (Exception e) {
			log.error("action=\"retrieveAllStudents\", description=\"INTERNAL_SERVER_ERROR\"", e);
			serviceResponse = new ApiServiceResponse(500, true, "failed to retrieve all student record", null);
		}
		log.debug("action=\"retrieveAllStudents\", description=\"response student\"", serviceResponse);
		return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
	}

	@PostMapping(path = "home/add/students", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> createStudent(@RequestBody StudentRequest studentRequest) {
		log.info("action=\"addStudent\", description=\"adding student record\", studentRequest " + studentRequest);
		ResponseEntity<Void> response = null;
		try {
			Student student = studentService.createStudent(studentRequest);
			log.debug("=========== Student  ======== " + student);
			response = ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			log.error("action=\"addStudent\", description=\"INTERNAL_SERVER_ERROR\"", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		log.debug("action=\"addStudent\", description=\"response student\"", response);
		return response;
	}

	@GetMapping(path = "home/delete/students/{enRoll}")
	public ResponseEntity<Void> deleteStudent(@PathVariable String enRoll) {
		log.info("action=\"deletingStudent\", description=\"delete student record\", enroll no. " + enRoll);
		ResponseEntity<Void> response = null;

		try {
			studentService.deleteStudent(Long.parseLong(enRoll));
			response = ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			log.error("action=\"deleteStudent\", description=\"INTERNAL_SERVER_ERROR\"", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		log.debug("action=\"deleteStudent\", description=\"response student\"", response);
		return response;

	}

	@PostMapping(path = "home/update/students", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiServiceResponse> updateStudent(@RequestBody StudentRequest studentRequest) {
		ApiServiceResponse serviceResponse = null;
		try {
			Student student = studentService.updateStudent(studentRequest);
			log.info("=========== Student  ======== " + student);
			serviceResponse = new ApiServiceResponse(200, false, "sucessfully updated", student);
		} catch (Exception e) {
			log.error("action=\"updateStudent\", description=\"INTERNAL_SERVER_ERROR\"", e);
			serviceResponse = new ApiServiceResponse(500, true, "failed to update student record", null);
		}
		log.debug("action=\"updateStudent\", description=\"response student\"", serviceResponse);
		return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
	}
}
