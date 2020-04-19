package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListOfFilters {

        private String model;
        private String brand;
        private int min;
        private int max;
        private java.sql.Date rentDate;
        private java.sql.Date  returnDate;

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

    public java.sql.Date  getRentDate() {
        return rentDate;
    }

    public java.sql.Date  getReturnDate() {
        return returnDate;
    }
}
