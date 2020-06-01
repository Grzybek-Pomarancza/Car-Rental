package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class FirstNameValidator {

    public void validate(User user) throws InvalidDataException {
        String attribute = user.getFirstName();
        if(attribute == null){
            throw new InvalidDataException();
        } else if(attribute.length() > 30){
            throw new InvalidDataException();

        } else if(attribute.length() < 3) {
            throw new InvalidDataException();

        } else if(!attribute.matches("[a-zA-Z]+")){
            throw new InvalidDataException();
        }
    }
}