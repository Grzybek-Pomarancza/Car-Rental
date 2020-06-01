package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.Rent;
import org.springframework.stereotype.Component;

@Component
public class RentCarValidator {

    public void validate(Rent rent) throws InvalidDataException {
        if(rent.getCar()==null){
            throw new InvalidDataException();
        }
    }
}
