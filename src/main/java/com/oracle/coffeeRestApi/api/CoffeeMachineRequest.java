package com.oracle.coffeeRestApi.api;

import com.oracle.coffeeRestApi.model.CoffeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class CoffeeMachineRequest {

    @NonNull
    Integer coffeeMachineId;
    @NonNull
    CoffeeType coffeeType;
}
