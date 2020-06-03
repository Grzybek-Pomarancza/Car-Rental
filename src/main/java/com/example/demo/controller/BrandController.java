package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Brand;
import com.example.demo.model.dao.ResponseStatus;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping(value = "/brand")
    public ResponseStatus addNewBrand(@RequestBody Brand newBrand) {

        try {
            brandService.addNewObject(newBrand);
        } catch (ObjectAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand Already Exists.", exception);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input.", exception);
        }
        return new ResponseStatus("Brand Succesfully Added.");
    }

    @GetMapping(value = "/brand")
    public Brand getBrandbyId(@RequestParam Long id) {
        try {
            return brandService.getBrandById(id);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Don't find brand with " + id + " Id");
        }
    }

    @GetMapping(value = "/brands")
    public List<Brand> getBrands() {
        return brandService.getBrands();
    }
}