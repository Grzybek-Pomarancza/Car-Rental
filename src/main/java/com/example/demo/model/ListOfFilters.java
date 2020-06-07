package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListOfFilters {

        private String model;
        private String brand;
        private Long officeId;
        private Integer min;
        private Integer max;
        private java.sql.Date rentDate;
        private java.sql.Date  returnDate;

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public java.sql.Date  getRentDate() {
        return rentDate;
    }

    public java.sql.Date  getReturnDate() {
        return returnDate;
    }
}
