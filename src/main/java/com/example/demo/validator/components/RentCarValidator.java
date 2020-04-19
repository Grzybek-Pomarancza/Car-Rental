package com.example.demo.validator.components;

import com.example.demo.model.Rent;

public class RentCarValidator implements iRentDateValidator {
    @Override
    public String validate(Rent rent) {
        if(rent.getCar()==null){
            return "car error";
        }
        return null;
    }
}
