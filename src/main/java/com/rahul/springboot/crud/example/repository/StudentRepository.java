package com.rahul.springboot.crud.example.repository;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.rahul.springboot.crud.example.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
	@Transactional
	public void deleteByEnRoll(Long enRoll);

	Optional<Student> findByEnRoll(Long enRoll);
}
