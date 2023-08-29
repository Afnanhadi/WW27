package com.example.hw27.Api;

public class ApiException extends RuntimeException {
    public ApiException(String message){
        super(message);
    }
}