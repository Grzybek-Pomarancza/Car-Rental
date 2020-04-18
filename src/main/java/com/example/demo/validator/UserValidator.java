package com.example.demo.validator;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.validator.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserValidator  {

    private List<iUserAttributesValidator> validators;

    public UserValidator(){
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new FirstNameValidator());
        validators.add(new LastNameValidator());
        validators.add(new PasswordValidator());
    }

    public List<String> validate(User user){

        return validators.stream()
                .map(e -> e.validate(user))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}