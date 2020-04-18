package com.example.demo.validator;


import com.example.demo.repository.ModelRepository;
import com.example.demo.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelValidator {

    @Autowired
    ModelRepository modelRepository;
    @Autowired
    BrandValidator brandValidator;

    public boolean checkIfObjectExists(Model modelToValidate) {

        if(modelRepository.findByName(modelToValidate.getName()).isPresent())
            return false;
        else
            return true;
    }

    public boolean validateObject(Model modelToValidate) {

        if(modelToValidate.getName() == null || modelToValidate.getName().isEmpty())
            return false;
        if(!brandValidator.validateObject(modelToValidate.getBrand()))
            return false;
        return true;
    }
}
