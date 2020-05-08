package com.example.demo.service;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.repository.BrandRepository;
import com.example.demo.validator.BrandValidator;
import com.example.demo.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {


    private final BrandRepository brandRepository;
    private final BrandValidator brandValidator;
    
    @Autowired
    public BrandService(BrandRepository brandRepository, BrandValidator brandValidator){
        this.brandValidator=brandValidator;
        this.brandRepository=brandRepository;
    }

    public void addNewObject(Brand newBrand) throws ObjectAlreadyExistsException, InvalidDataException {

        if(brandValidator.checkIfObjectExists(newBrand))
            if(brandValidator.validateObject(newBrand))
                brandRepository.save(newBrand);
            else
                throw new InvalidDataException();
        else
            throw new ObjectAlreadyExistsException();
    }
}
