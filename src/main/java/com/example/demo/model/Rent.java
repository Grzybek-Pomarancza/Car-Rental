package com.example.demo.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "RENTS")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Car car;
   // @Temporal(TemporalType.DATE)
    java.sql.Date rentDate;
    //@Temporal(TemporalType.DATE)
    java.sql.Date  returnDate;

    public Rent(){}

    public Rent(Car car, User user){
        this.car=car;
        this.user=user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setRentDate(java.sql.Date  rentDate) {
        this.rentDate = rentDate;
    }

    public void setReturnDate(java.sql.Date  returnDate) {
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public java.sql.Date getRentDate() {
        return rentDate;
    }

    public java.sql.Date getReturnDate() {
        return returnDate;
    }
}