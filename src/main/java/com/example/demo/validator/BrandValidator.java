package com.example.demo.validator;

import com.example.demo.repository.BrandRepository;
import com.example.demo.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandValidator {

    @Autowired
    BrandRepository brandRepository;

    public boolean checkIfObjectExists(Brand brandToValidate) {

        if(brandRepository.findByName(brandToValidate.getName()).isPresent())
            return false;
        else
            return true;
    }

    public boolean validateObject(Brand brandToValidate) {

        if( brandToValidate.getName() != null && (!brandToValidate.getName().isEmpty()) )
            return true;
        else
            return false;
    }
}