package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
    private final UserService userService;

    @Autowired
    public EmailValidator(UserService userService) {
        this.userService = userService;
    }

    public void validate(User user) throws InvalidDataException, ObjectAlreadyExistsException {
        String attribute = user.getEmail();
        if (attribute == null) {
            throw new InvalidDataException();
        } else if (!attribute.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new InvalidDataException();
        } else if (userService.findByEmail(user.getEmail()).isPresent()) {
            throw new ObjectAlreadyExistsException();
        }
    }

}