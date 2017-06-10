package com.saltside.test.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Created by krsna on 04/06/2017.
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse(404, 4041, ex.getMessage());
    }

    @ExceptionHandler(value = {JsonMappingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse jsonMappingException(JsonMappingException ex) {
        return new ApiErrorResponse(400, 4002, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse unknownException(Exception ex) {
        return new ApiErrorResponse(400, 4002, "Bad Request");
    }
}