package com.example.demo.service;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Rank;
import com.example.demo.repository.RankRepository;
import com.example.demo.validator.RankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankService {

    private final RankRepository rankRepository;
    private final RankValidator rankValidator;

    @Autowired
    RankService(RankRepository rankRepository, RankValidator rankValidator) {
        this.rankRepository = rankRepository;
        this.rankValidator = rankValidator;
    }

    public void addNewObject(Rank newRank) throws ObjectAlreadyExistsException, InvalidDataException {

        if (rankValidator.checkIfObjectExists(newRank))
            if (rankValidator.validateObject(newRank))
                rankRepository.save(newRank);
            else
                throw new InvalidDataException();
        else
            throw new ObjectAlreadyExistsException();
    }
}