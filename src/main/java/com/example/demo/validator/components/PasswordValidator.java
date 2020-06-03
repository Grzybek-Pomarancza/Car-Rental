package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    public String validate(User user) throws InvalidDataException {
        String attribute = user.getPassword();
        if(attribute == null){
            throw new InvalidDataException();
        } else if(attribute.length() > 30){
            throw new InvalidDataException();
        } else if(attribute.length() < 3) {
            throw new InvalidDataException();
        } else if(attribute.contains(" ")){
            throw new InvalidDataException();
        }
        return null;
    }
}
