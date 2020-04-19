package com.example.demo.validator;


import com.example.demo.model.Rent;
import com.example.demo.validator.components.RentCarValidator;
import com.example.demo.validator.components.RentDateValidator;
import com.example.demo.validator.components.RentUserValidator;
import com.example.demo.validator.components.iRentDateValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RentValidator {

    private List<iRentDateValidator> validators;

    public RentValidator(){
        validators = new ArrayList<>();
        validators.add(new RentDateValidator());
        validators.add(new RentUserValidator());
        validators.add(new RentCarValidator());
    }

    public List<String> validate(Rent rent){

        return validators.stream()
                .map(e -> e.validate(rent))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
