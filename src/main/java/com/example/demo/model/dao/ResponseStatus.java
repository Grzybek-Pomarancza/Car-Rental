package com.example.demo.model.dao;

public class ResponseStatus {

    private final String message;

    public ResponseStatus(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}