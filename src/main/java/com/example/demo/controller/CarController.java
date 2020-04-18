package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(method= RequestMethod.POST,value="/newcar")
    public String addNewCar(@RequestBody Car newCar)  {
        try{
            carService.addNewObject(newCar);
        } catch (ObjectAlreadyExistsException exception1) {
            return "Car with given license already exists.";
        } catch (ObjectNotFoundException exception2) {
            return "Model, brand or rank does not exists.";
        } catch (InvalidDataException exception) {
            return "Invalid data.";
        }
        return "Car sucessfully added.";
    }
}
