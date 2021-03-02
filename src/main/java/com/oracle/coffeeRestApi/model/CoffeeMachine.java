package com.oracle.coffeeRestApi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CoffeeMachine {

    private final Integer floor;
    private final Integer kitchen;

    public CoffeeMachine(Integer floor, Integer kitchen) {
        this.floor = floor;
        this.kitchen = kitchen;
    }

    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime completionTime;
}
