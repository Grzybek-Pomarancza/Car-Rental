package com.example.demo.validator;

import com.example.demo.model.Brand;
import com.example.demo.repository.RankRepository;
import com.example.demo.model.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankValidator {

    @Autowired
    RankRepository rankRepository;

    public boolean checkIfObjectExists(Rank rankToValidate) {

        if(rankRepository.findByName(rankToValidate.getName()).isPresent())
            return false;
        else
            return true;
    }

    public boolean validateObject(Rank rankToValidate) {

        if( rankToValidate.getName() != null && (!rankToValidate.getName().isEmpty()) &&
            rankToValidate.getDeposit() != 0 && rankToValidate.getPrice() != 0)
            return true;
        else
            return false;
    }
}
