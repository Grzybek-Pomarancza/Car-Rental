package com.example.demo.model;

import javax.persistence.*;
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
    @Temporal(TemporalType.DATE) java.util.Date rentDate;
    @Temporal(TemporalType.DATE) java.util.Date retunrnDate;

    public Rent(){}

    public void setUser(User user) {
        this.user = user;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public void setRetunrnDate(Date retunrnDate) {
        this.retunrnDate = retunrnDate;
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

    public Date getRentDate() {
        return rentDate;
    }

    public Date getRetunrnDate() {
        return retunrnDate;
    }
}