package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class LastNameValidator {

    public void validate(User user) throws InvalidDataException {
        String attribute = user.getLastName();
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