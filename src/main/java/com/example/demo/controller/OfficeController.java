package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.Car;
import com.example.demo.model.Office;
import com.example.demo.model.dao.ResponseStatus;
import com.example.demo.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/carsInOffice/{id}")
    public List<Car> getCarsInOffice(@PathVariable Long id) {
        try {
            return officeService.getCarsInOffice(id);
        } catch (ObjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model, brand or rank does not exists.", exception);
        }
    }
    @GetMapping("/officesAll")
    public List<Office> getAllOffices(){
        try {
            return officeService.getAllOffices();
        }catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Database error!");
        }
    }

    @PostMapping("/office")
    public ResponseStatus postOffice(@RequestBody Office office) {
        try {
            officeService.addNewOffice(office);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Data!", exception);
        }
        return new ResponseStatus("Successfully created! Id: " + office.getId());
    }

    @PatchMapping("/office")
    public ResponseStatus patchOffice(@RequestBody Office office) {
        try {
            officeService.patchOffice(office);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Data!", exception);
        } catch (ObjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office not found!", exception);
        }
        return new ResponseStatus("Successfully patched!");
    }

    @PatchMapping("/officeAddress/{id}")
    public ResponseStatus patchOfficeAddress(@PathVariable Long id, @RequestBody Address address) {
        try {
            officeService.patchOfficeAddress(address, id);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Data!", exception);
        } catch (ObjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office not found!", exception);
        }
        return new ResponseStatus("Successfully patched!");
    }

}
