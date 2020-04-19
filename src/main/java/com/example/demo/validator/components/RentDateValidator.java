package com.example.demo.validator.components;

import com.example.demo.model.Rent;

import java.util.Calendar;

public class RentDateValidator implements iRentDateValidator {
    @Override
    public String validate(Rent rent) {
        if (!(rent.getRentDate() instanceof java.sql.Date) || !(rent.getReturnDate() instanceof java.sql.Date) ) {
            return "not data type";
        }
        java.sql.Date rentDate = rent.getRentDate();
        java.sql.Date returnDate = rent.getReturnDate();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if(rentDate.compareTo(date) < 0){
            return "rent date is before today ";
        } else  if(returnDate.compareTo(date) < 0){
            return "return date is before today ";
        } else  if(returnDate.compareTo(rentDate) == 0){
            return "return and rent dates are equal ";
        } else  if(returnDate.compareTo(rentDate) < 0){
            return "return date is before rent date ";
        }
        return null;
    }
}
