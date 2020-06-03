package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Rank;
import com.example.demo.model.dao.ResponseStatus;
import com.example.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @RequestMapping(method = RequestMethod.POST, value = "/newrank")
    public ResponseStatus addNewRank(@RequestBody Rank newRank) {
        try {
            rankService.addNewObject(newRank);
        } catch (ObjectAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rank Already Exists.", exception);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input", exception);
        }
        return new ResponseStatus("Rank successfully added.");
    }
}