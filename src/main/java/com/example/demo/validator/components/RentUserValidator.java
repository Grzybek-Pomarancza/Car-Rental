package com.example.demo.validator.components;

import com.example.demo.model.Rent;

public class RentUserValidator implements iRentDateValidator {
    @Override
    public String validate(Rent rent) {
        if(rent.getUser()==null){
            return "user error";
        }
        return null;
    }
}
