package com.oracle.coffeeRestApi.model;

import lombok.Getter;

public enum CoffeeType {

    ESPRESSO(3),
    CAPPUCCINO(5),
    LATTE(7);

    @Getter
    private final int preparationTime;

    /**
     * In seconds.
     * @param preparationTime
     */
    CoffeeType(int preparationTime) {
        this.preparationTime = preparationTime;
    }
}
