package com.example.demo.validator.components;

import com.example.demo.model.User;

public class LastNameValidator implements iUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getLastName();
        if(attribute == null){
            return "enter last name";
        } else if(attribute.length() > 30){
            return "lastName is too long";
        } else if(attribute.length() < 3) {
            return "lastName is too short";
        } else if(!attribute.matches("[a-zA-Z]+")){
            return "lastName contains illegal character";
        }
        return null;
    }
}