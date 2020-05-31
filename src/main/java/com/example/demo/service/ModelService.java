package com.example.demo.service;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ModelRepository;
import com.example.demo.validator.BrandValidator;
import com.example.demo.validator.ModelValidator;
import com.example.demo.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final BrandValidator brandValidator;
    private final ModelValidator modelValidator;
    private final BrandRepository brandRepository;

    @Autowired
    ModelService(ModelValidator modelValidator, ModelRepository modelRepository, BrandValidator brandValidator, BrandRepository brandRepository) {

        this.modelValidator=modelValidator;
        this.modelRepository=modelRepository;
        this.brandRepository=brandRepository;
        this.brandValidator=brandValidator;
    }

    public void addNewObject(Model newModel) throws ObjectAlreadyExistsException, ObjectNotFoundException, InvalidDataException {

        if(modelValidator.checkIfObjectExists(newModel)) {
            if(modelValidator.validateObject(newModel)) {
                if (!brandValidator.checkIfObjectExists(newModel.getBrand())) {
                    Model model = new Model(newModel.getName(), brandRepository.findByName(newModel.getBrand().getName()).get());
                    modelRepository.save(model);
                } else if (brandValidator.checkIfObjectExists(newModel.getBrand())) {
                    throw new ObjectNotFoundException();
                }
            }
            else
                throw new InvalidDataException();
        }
        else
            throw new ObjectAlreadyExistsException();
    }
}