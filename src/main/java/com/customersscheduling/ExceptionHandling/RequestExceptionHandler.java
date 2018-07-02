package com.customersscheduling.ExceptionHandling;

import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.dao.*;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<VndErrors> handle(HttpRequestMethodNotSupportedException ex, HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST, "error");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<VndErrors> handle(ResourceNotFoundException ex, HttpServletRequest request){
        return error(ex,HttpStatus.NOT_FOUND, "Some resource couldn't be found");
    }

    @ExceptionHandler(InvalidBodyException.class)
    public final ResponseEntity<VndErrors> handle(InvalidBodyException ex, HttpServletRequest request){
        return error(ex,HttpStatus.NOT_FOUND, "Body values are incorrect");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<VndErrors> handle(Exception ex, HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST, "error");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<VndErrors> handle(DuplicateKeyException ex,HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST, "error");
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<VndErrors> handle(DataAccessException ex,HttpServletRequest request){
        return error( ex, HttpStatus.BAD_REQUEST, "error");
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<VndErrors> handle(InvalidFormatException ex,HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "Incorrect body data format.");
    }

    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<VndErrors> handle(NumberFormatException ex,HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "Incorrect query data format.");
    }

    @ExceptionHandler(NonTransientDataAccessException.class)
    public final ResponseEntity<VndErrors> handle(NonTransientDataAccessException ex, HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "error");
    }

    @ExceptionHandler(TransientDataAccessException.class)
    public final ResponseEntity<VndErrors> handle(TransientDataAccessException ex, HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "error");
    }

    @ExceptionHandler(RecoverableDataAccessException.class)
    public final ResponseEntity<VndErrors> handle(RecoverableDataAccessException ex, HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "error");
    }

    @ExceptionHandler(ScriptException.class)
    public final ResponseEntity<VndErrors> handle(ScriptException ex, HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "error");
    }

    private ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

}
