package com.example.demo.validator.components;

import com.example.demo.model.User;

public class FirstNameValidator implements iUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getFirstName();
        if(attribute.length() > 30){
            return "firstName is too long";
        } else if(attribute.length() < 3) {
            return "firstName is too short";
        } else if(!attribute.matches("[a-zA-Z]+")){
            return "firstName contains illegal character";
        }
        return null;
    }
}