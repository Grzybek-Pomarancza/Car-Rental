package com.example.demo.service;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ModelRepository;
import com.example.demo.repository.OfficeRepository;
import com.example.demo.repository.RankRepository;
import com.example.demo.validator.BrandValidator;
import com.example.demo.validator.CarValidator;
import com.example.demo.validator.ModelValidator;
import com.example.demo.validator.RankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final RankRepository rankRepository;
    private final CarValidator carValidator;
    private final ModelValidator modelValidator;
    private final RankValidator rankValidator;
    private final BrandValidator brandValidator;

    @Autowired
    CarService(CarRepository carRepository, RankValidator rankValidator, ModelValidator modelValidator,
               BrandValidator brandValidator, RankRepository rankRepository, CarValidator carValidator, ModelRepository modelRepository, OfficeRepository officeRepository) {

        this.modelRepository = modelRepository;
        this.carRepository = carRepository;
        this.rankRepository = rankRepository;
        this.modelValidator = modelValidator;
        this.rankValidator = rankValidator;
        this.carValidator = carValidator;
        this.brandValidator = brandValidator;
    }

    public void addNewObject(Car newCar) throws ObjectAlreadyExistsException, ObjectNotFoundException, InvalidDataException {

        if (carValidator.checkIfObjectExists(newCar)) {
            if (carValidator.validateObject(newCar)) {
                if ((modelValidator.checkIfObjectExists(newCar.getModel()))
                        || (rankValidator.checkIfObjectExists(newCar.getRank()))
                        || (brandValidator.checkIfObjectExists(newCar.getModel().getBrand()))) {
                    throw new ObjectNotFoundException();
                }
                carRepository.save(new Car(newCar.getLicense(), newCar.getYear(),
                        modelRepository.findByName(newCar.getModel().getName()).get(),
                        rankRepository.findByName(newCar.getRank().getName()).get()));
            } else
                throw new InvalidDataException();
        } else
            throw new ObjectAlreadyExistsException();
    }

}