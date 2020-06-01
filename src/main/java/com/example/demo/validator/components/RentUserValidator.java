package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.Rent;
import org.springframework.stereotype.Component;

@Component
public class RentUserValidator {
    public void validate(Rent rent) throws InvalidDataException {
        if(rent.getUser()==null){
            throw new InvalidDataException();
        }
    }
}
