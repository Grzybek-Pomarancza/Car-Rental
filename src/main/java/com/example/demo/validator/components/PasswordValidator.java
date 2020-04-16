package com.example.demo.validator.components;

import com.example.demo.model.User;

public class PasswordValidator implements iUserAttributesValidator {

    @Override
    public String validate(User user) {
        String attribute = user.getPassword();
        if(attribute.length() > 30){
            return "password is too long";
        } else if(attribute.length() < 3) {
            return "password is too short";
        } else if(attribute.contains(" ")){
            return "password contains illegal character";
        }
        return null;
    }
}
