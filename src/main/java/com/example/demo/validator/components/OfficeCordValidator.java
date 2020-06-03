package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import org.springframework.stereotype.Component;

@Component
public class OfficeCordValidator {
    public void validate(Double cord) throws InvalidDataException {
        if (cord == null) {
            throw new InvalidDataException();
        }
        //TODO VALIDATE COORDINATE
    }
}
