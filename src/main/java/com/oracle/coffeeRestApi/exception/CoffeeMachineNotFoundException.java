package com.oracle.coffeeRestApi.exception;

public class CoffeeMachineNotFoundException extends RuntimeException {

    public CoffeeMachineNotFoundException(int id) {
        super("Coffee machine " + id + " does not exist.");
    }
}
