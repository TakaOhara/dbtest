package com.example.dbtest.repositories;

import com.example.dbtest.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	Optional<Instructor> findById(int id);
	Optional<Instructor> findByEmail(String email);
	void deleteById(int id);
}
