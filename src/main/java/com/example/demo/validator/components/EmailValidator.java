package com.example.demo.validator.components;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements iUserAttributesValidator {
    @Autowired
    private UserService userService;
    @Override
    public String validate(User user) {
        String attribute = user.getEmail();
        if(attribute == null){
            return "enter email";
        } else if(!attribute.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            return "email incorrect";
        } else if(userService.findByEmail(user.getEmail()) != null){
            return "email taken";
        }
        return null;
    }

}