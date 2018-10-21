package com.example.dbtest.validator;

import com.example.dbtest.entity.Instructor;
import com.example.dbtest.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class InstructorExistsValidator implements ConstraintValidator<InstructorExists, String> {

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public void initialize(InstructorExists annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return true;

        Optional<Instructor> inst = instructorRepository.findByEmail(value);

        return !inst.isPresent();

    }
}
