package com.example.demo.model.dao;

import java.sql.Date;

public class ListOfFilters {

    private String model;
    private String brand;
    private int min;
    private int max;
    private java.sql.Date rentDate;
    private java.sql.Date returnDate;

    public ListOfFilters() {
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public java.sql.Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public java.sql.Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
