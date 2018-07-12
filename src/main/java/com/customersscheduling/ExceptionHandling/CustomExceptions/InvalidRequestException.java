package com.customersscheduling.ExceptionHandling.CustomExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String msg){
        super(msg);
    }


    public InvalidRequestException(){
        super();
    }
}