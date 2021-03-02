package com.oracle.coffeeRestApi.repository;

import com.oracle.coffeeRestApi.model.CoffeeMachine;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simplified repository for coffee machines.
 */
@Repository
public class CoffeeMachineRepository {

    private final ConcurrentHashMap<Integer, CoffeeMachine> coffeeMachineMap = new ConcurrentHashMap<>();

    CoffeeMachineRepository() {
        coffeeMachineMap.put(1, new CoffeeMachine(1, 1));
        coffeeMachineMap.put(2, new CoffeeMachine(1, 2));
        coffeeMachineMap.put(3, new CoffeeMachine(2, 1));
        coffeeMachineMap.put(4, new CoffeeMachine(2, 2));
        coffeeMachineMap.put(5, new CoffeeMachine(3, 1));
        coffeeMachineMap.put(6, new CoffeeMachine(3, 2));
    }

    public CoffeeMachine getCoffeeMachine(int coffeeMachineId) {
        return coffeeMachineMap.get(coffeeMachineId);
    }

    public Map<Integer, CoffeeMachine> getCoffeeMachines() {
        return coffeeMachineMap;
    }
}
