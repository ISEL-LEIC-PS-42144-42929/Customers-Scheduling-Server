package com.customersscheduling.ExceptionHandling.CustomExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtTokenException extends RuntimeException {
    public JwtTokenException(String msg){
        super(msg);
    }
}
