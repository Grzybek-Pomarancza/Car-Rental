package com.example.demo.validator.components;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.model.Rent;
import org.springframework.stereotype.Component;
import java.util.Calendar;

@Component
public class RentDateValidator {

    public void validate(Rent rent) throws InvalidDataException {
        if (!(rent.getRentDate() instanceof java.sql.Date) || !(rent.getReturnDate() instanceof java.sql.Date) ) {
            throw new InvalidDataException();
        }
        java.sql.Date rentDate = rent.getRentDate();
        java.sql.Date returnDate = rent.getReturnDate();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if(rentDate.compareTo(date) < 0){
            throw new InvalidDataException();
        } else  if(returnDate.compareTo(date) < 0){
            throw new InvalidDataException();
        } else  if(returnDate.compareTo(rentDate) == 0){
            throw new InvalidDataException();
        } else  if(returnDate.compareTo(rentDate) < 0){
            throw new InvalidDataException();
        }
    }
}
