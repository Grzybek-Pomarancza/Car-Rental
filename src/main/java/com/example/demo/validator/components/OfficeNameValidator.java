package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import org.springframework.stereotype.Component;

@Component
public class OfficeNameValidator {
    public void validate(String attribute) throws InvalidDataException {
        if (attribute == null) {
            throw new InvalidDataException();
        } else if (attribute.length() > 100) {
            throw new InvalidDataException();
        } else if (attribute.length() < 3) {
            throw new InvalidDataException();
        }
    }
}
