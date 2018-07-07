package com.customersscheduling.ExceptionHandling;

import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.JwtTokenException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.*;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.TransactionRequiredException;
import java.util.Optional;

@RestControllerAdvice
public class RequestExceptionHandler {

    private final Log logger = LogFactory.getLog(getClass());

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorModel> handle(HttpRequestMethodNotSupportedException ex, HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST, "Bad method", request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorModel> handle(ResourceNotFoundException ex, HttpServletRequest request){
        return error(ex,HttpStatus.NOT_FOUND, "Some resource couldn't be found", request.getRequestURI());
    }

    @ExceptionHandler(InvalidBodyException.class)
    public final ResponseEntity<ErrorModel> handle(InvalidBodyException ex, HttpServletRequest request){
        return error(ex,HttpStatus.NOT_FOUND, "Body values are incorrect", request.getRequestURI());
    }

    @ExceptionHandler(TransactionRequiredException.class)
    public final ResponseEntity<ErrorModel> handle(TransactionRequiredException ex, HttpServletRequest request){
        return error(ex,HttpStatus.NOT_FOUND, "Some problem occurred while accessing database", request.getRequestURI());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<ErrorModel> handle(Exception ex, HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST, "Bad URI", request.getRequestURI());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<ErrorModel> handle(DuplicateKeyException ex,HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST, "Duplicate value", request.getRequestURI());
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<ErrorModel> handle(DataAccessException ex,HttpServletRequest request){
        return error( ex, HttpStatus.BAD_REQUEST, "Database access", request.getRequestURI());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorModel> handle(InvalidFormatException ex,HttpServletRequest request){
        return error( ex, HttpStatus.BAD_REQUEST, "Incorrect body data format.", request.getRequestURI());
    }

    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<ErrorModel> handle(NumberFormatException ex,HttpServletRequest request){
        return error( ex, HttpStatus.BAD_REQUEST, "Incorrect query data format.", request.getRequestURI());
    }

    @ExceptionHandler(NonTransientDataAccessException.class)
    public final ResponseEntity<ErrorModel> handle(NonTransientDataAccessException ex, HttpServletRequest request){
        return error( ex, HttpStatus.INTERNAL_SERVER_ERROR, "Database access", request.getRequestURI());
    }

    @ExceptionHandler(TransientDataAccessException.class)
    public final ResponseEntity<ErrorModel> handle(TransientDataAccessException ex, HttpServletRequest request){
        return error( ex, HttpStatus.INTERNAL_SERVER_ERROR, "Database access", request.getRequestURI());
    }

    @ExceptionHandler(RecoverableDataAccessException.class)
    public final ResponseEntity<ErrorModel> handle(RecoverableDataAccessException ex, HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "error", request.getRequestURI());
    }

    @ExceptionHandler(ScriptException.class)
    public final ResponseEntity<ErrorModel> handle(ScriptException ex, HttpServletRequest request){
        return error( ex, HttpStatus.NOT_FOUND, "error", request.getRequestURI());
    }

    private ResponseEntity<ErrorModel> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef, final String uri) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        logger.error(message);
        return new ResponseEntity<>(new ErrorModel(logRef, message, uri), headers, httpStatus);
    }

}
