package com.example.demo.controller;

import com.example.demo.ResponseStatus.ResponseStatus;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Model;
import com.example.demo.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(method=RequestMethod.POST,value="/newmodel")
    public ResponseStatus addNewBrand(@RequestBody Model newModel) {

        try{
            modelService.addNewObject(newModel);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input", exception);
        } catch (ObjectAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Model Already Exists.", exception);
        } catch (ObjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Brand Does Not Exists.",exception);
        }
        return new ResponseStatus("Model Sucessfully Added.");

    }
}
