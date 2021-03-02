package com.oracle.coffeeRestApi.exception;

import com.oracle.coffeeRestApi.api.CoffeeMachineResponse;
import com.oracle.coffeeRestApi.model.CoffeeType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

/**
 * Error handler producing custom exceptions with some simple hints on how to operate the machine.
 */
@ControllerAdvice
public class CoffeeMachineErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CoffeeMachineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CoffeeMachineResponse coffeeMachineNotFoundHandler(CoffeeMachineNotFoundException ex) {
        return CoffeeMachineResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .message("Use /coffeemachines/ to see available coffee machines.")
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        CoffeeMachineResponse response = CoffeeMachineResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("Coffee machine can only make one of " + Arrays.toString(CoffeeType.values())
                        + " and must be specified with a valid coffeeMachineId.")
                .build();
        return buildResponseEntity(response, status);
    }

    private ResponseEntity<Object> buildResponseEntity(CoffeeMachineResponse response, HttpStatus status) {
        return new ResponseEntity<>(response, status);
    }
}