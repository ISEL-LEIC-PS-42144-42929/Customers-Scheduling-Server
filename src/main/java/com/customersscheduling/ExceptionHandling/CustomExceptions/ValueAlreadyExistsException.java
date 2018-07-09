package com.customersscheduling.ExceptionHandling.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValueAlreadyExistsException extends RuntimeException {

    public ValueAlreadyExistsException(String msg){
        super(msg);
    }

    public ValueAlreadyExistsException(){
        super();
    }
}
