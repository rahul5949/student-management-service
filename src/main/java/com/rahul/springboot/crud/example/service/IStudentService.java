package com.rahul.springboot.crud.example.service;

import java.util.Optional;

import com.rahul.springboot.crud.example.entity.Student;
import com.rahul.springboot.crud.example.models.StudentRequest;

public interface IStudentService {
	Student createStudent(StudentRequest studentRequest);
	public void  deleteStudent(Long enRoll);
	Student updateStudent(StudentRequest studentRequest);
	Iterable<Student> retrieveAllStudents();
	Optional<Student> retrieveStudent(Long enRoll);
}
