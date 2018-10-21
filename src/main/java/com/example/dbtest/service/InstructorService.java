package com.example.dbtest.service;

import com.example.dbtest.controllers.InstructorForm;
import com.example.dbtest.entity.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService {

	List<Instructor> findAll();
		
	Optional<InstructorForm> getInstructorForm(int id);

	void save(Instructor instructor);
	
	void deleteById(int id);
}
