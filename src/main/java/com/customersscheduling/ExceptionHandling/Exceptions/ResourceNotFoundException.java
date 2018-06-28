package com.customersscheduling.ExceptionHandling.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
