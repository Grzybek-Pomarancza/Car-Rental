package com.example.demo.validator;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.validator.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator  {

    private final EmailValidator emailValidator;
    private final FirstNameValidator firstNameValidator;
    private final LastNameValidator lastNameValidator;
    private final PasswordValidator passwordValidator;

    @Autowired
    public UserValidator(EmailValidator emailValidator,
            FirstNameValidator firstNameValidator,
            LastNameValidator lastNameValidator,
            PasswordValidator passwordValidator) {
        this.emailValidator=emailValidator;
        this.firstNameValidator=firstNameValidator;
        this.lastNameValidator=lastNameValidator;
        this.passwordValidator=passwordValidator;
    }

    public void validate(User userToValidate) throws InvalidDataException, ObjectAlreadyExistsException {
        this.emailValidator.validate(userToValidate);
        this.firstNameValidator.validate(userToValidate);
        this.lastNameValidator.validate(userToValidate);
        this.passwordValidator.validate(userToValidate);
    }

}