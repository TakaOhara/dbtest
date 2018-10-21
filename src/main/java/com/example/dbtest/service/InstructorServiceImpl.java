package com.example.dbtest.service;

import com.example.dbtest.controllers.InstructorForm;
import com.example.dbtest.entity.Instructor;
import com.example.dbtest.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

	private final InstructorRepository repository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository repository) {
        this.repository = repository;
    }

    @Override
	public List<Instructor> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<InstructorForm> getInstructorForm(int id) {
		Optional<Instructor> inst = repository.findById(id);

		if(!inst.isPresent()) return Optional.empty(); // TODO empty()を使う

        return inst.map(instructor -> new InstructorForm(
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getEmail(),
                instructor.getInstructorDetail().getYoutubeChannel(),
                instructor.getInstructorDetail().getHobby(),
                false));
	}

	@Override
	public void save(Instructor instructor) {
		repository.saveAndFlush(instructor);

	}

	@Override
	public void deleteById(int id) {
		Optional<Instructor> inst =  repository.findById(id);
		System.out.println(inst.isPresent());
		if(inst.isPresent()) {
			repository.deleteById(id);
		}

	}

}
