<<<<<<< HEAD
package com.example.demo.model;


import javax.persistence.*;

@Entity(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String street;
    private int number;
    private String code;
    private String city;

    public Address() {}

    public Address(String street, int number, String code, String city) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
=======
package com.example.demo.model;


import javax.persistence.*;

@Entity(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String street;
    private int number;
    private String code;
    private String city;

    public Address() {}

    public Address(String street, int number, String code, String city) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
>>>>>>> 9437c9efa08817ba060e4dd81a4c945cf1f836ea
}