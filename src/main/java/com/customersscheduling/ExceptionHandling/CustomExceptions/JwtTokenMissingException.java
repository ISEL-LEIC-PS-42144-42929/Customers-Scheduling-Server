package com.customersscheduling.ExceptionHandling.CustomExceptions;

public class JwtTokenMissingException extends RuntimeException {
    public JwtTokenMissingException(String msg){
        super(msg);
    }
}
