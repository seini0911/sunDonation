package com.sunshine.donations.exceptions;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
