package com.example.dbtest.validator;

import com.example.dbtest.entity.UserInfo;
import com.example.dbtest.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserInfoExistsValidator implements ConstraintValidator<UserInfoExists, String> {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public void initialize(UserInfoExists annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return true;

        UserInfo userInfo = userInfoRepository.findByEmail(value);

        return userInfo == null;

    }
}
