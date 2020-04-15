package com.example.demo.model;


import javax.persistence.*;

@Entity(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String street;
    private String number;
    private String code;
    private String city;

    public Address() {}

    public Address(String street, String number, String code, String city) {
        this.street = street;
        this.number = number;
        this.code = code;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, street='%s',number=%d , code='%s', city='%s']",
                id, street, number, code, city);
    }

    public Long getId() {
        return id;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}