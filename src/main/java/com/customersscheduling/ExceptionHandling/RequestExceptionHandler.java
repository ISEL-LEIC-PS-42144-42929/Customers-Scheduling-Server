package com.customersscheduling.ExceptionHandling;

import com.customersscheduling.ExceptionHandling.Exceptions.ResourceNotFoundException;
import javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.el.MethodNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<VndErrors> handle(HttpRequestMethodNotSupportedException ex, HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<VndErrors> handle(ResourceNotFoundException ex, HttpServletRequest request){
        return error(ex,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<VndErrors> handle(Exception ex, HttpServletRequest request){
        return error(ex,HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors("error", message), httpStatus);
    }

}
