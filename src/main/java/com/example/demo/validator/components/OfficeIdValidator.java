package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficeIdValidator {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeIdValidator(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public void validate(Long attribute) throws InvalidDataException, ObjectNotFoundException {
        if (attribute == null) {
            throw new InvalidDataException();
        } else if (officeRepository.findById(attribute).isEmpty()) {
            throw new ObjectNotFoundException();
        }
    }
}
