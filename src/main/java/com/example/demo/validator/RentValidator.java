package com.example.demo.validator;


import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.Rent;
import com.example.demo.validator.components.RentCarValidator;
import com.example.demo.validator.components.RentDateValidator;
import com.example.demo.validator.components.RentUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentValidator {

    private final RentCarValidator rentCarValidator;
    private final RentUserValidator rentUserValidator;
    private final RentDateValidator rentDateValidator;

    @Autowired
    public RentValidator(RentCarValidator rentCarValidator,
                         RentUserValidator rentUserValidator,
                         RentDateValidator rentDateValidator){
        this.rentCarValidator=rentCarValidator;
        this.rentDateValidator=rentDateValidator;
        this.rentUserValidator=rentUserValidator;
    }

    public void validate(Rent rent) throws InvalidDataException {
        rentCarValidator.validate(rent);
        rentUserValidator.validate(rent);
        rentDateValidator.validate(rent);
    }
}
