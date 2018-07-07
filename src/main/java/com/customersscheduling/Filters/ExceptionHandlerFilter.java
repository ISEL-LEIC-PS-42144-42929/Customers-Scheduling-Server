package com.customersscheduling.Filters;

import com.customersscheduling.ExceptionHandling.CustomExceptions.JwtTokenException;
import com.customersscheduling.ExceptionHandling.ErrorModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorModel error = new ErrorModel("Authentication Error", e.getMessage(), request.getRequestURI());
            response.getWriter().write(convertObjectToJson(error));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
