package com.customersscheduling.ExceptionHandling.CustomExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBodyException extends RuntimeException {

    public InvalidBodyException(String msg){
        super(msg);
    }


    public InvalidBodyException(){
        super();
    }
}