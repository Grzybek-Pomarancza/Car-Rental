package com.example.demo.validator;

import com.example.demo.model.User;
import com.example.demo.validator.components.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserValidator  {

    private List<iUserAttributesValidator> validators;

    public UserValidator(EmailValidator emailValidator){
        validators = new ArrayList<>();
        validators.add(emailValidator);
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