package com.example.demo.validator.components;

import com.example.demo.model.User;

public class EmailValidator implements iUserAttributesValidator {
    @Override
    public String validate(User user) {
        String attribute = user.getEmail();
        if(!attribute.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            return "email incorrect";
        }
        return null;
    }

}