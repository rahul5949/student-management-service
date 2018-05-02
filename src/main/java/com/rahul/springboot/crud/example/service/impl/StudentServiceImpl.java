package com.rahul.springboot.crud.example.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.springboot.crud.example.entity.Student;
import com.rahul.springboot.crud.example.models.StudentRequest;
import com.rahul.springboot.crud.example.repository.StudentRepository;
import com.rahul.springboot.crud.example.service.IStudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Student createStudent(StudentRequest studentRequest) {
		log.info("action=\"creteStudent\", description=\"adding student record\", studentRequest " + studentRequest);
		final Student student = new Student();
		student.setEnRoll(studentRequest.getEnRoll());
		student.setFirstName(studentRequest.getFirstName());
		student.setLastName(studentRequest.getLastName());
		student.setEmail(studentRequest.getEmail());

		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long enRoll) {
		log.info("action=\"deleteStudent\", description=\"deleting student record\", studentRequest " + enRoll);
		studentRepository.deleteByEnRoll(enRoll);
	}

	@Override
	public Student updateStudent(StudentRequest studentRequest) {
		log.info("action=\"updateStudent\", description=\"updating student record\", studentRequest " + studentRequest);
		final Student student = new Student();
		student.setEnRoll(studentRequest.getEnRoll());
		student.setFirstName(studentRequest.getFirstName());
		student.setLastName(studentRequest.getLastName());
		student.setEmail(studentRequest.getEmail());

		return studentRepository.save(student);
	}

	@Override
	public Iterable<Student> retrieveAllStudents() {
		log.info("action=\"retrieveAllStudent\", description=\"retrieve all student record\", studentRequest ");
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> retrieveStudent(Long enRoll) {
		log.info("action=\"retrieveStudent\", description=\"retrieve  student record\", studentRequest ");
		Optional<Student> student = studentRepository.findByEnRoll(enRoll);
		if (!student.isPresent())
			throw new RuntimeException("invalid enroll no ." + enRoll);
		return student;
	}
	
}
