package com.oracle.coffeeRestApi.resource;

import com.oracle.coffeeRestApi.api.CoffeeMachineRequest;
import com.oracle.coffeeRestApi.api.CoffeeMachineResponse;
import com.oracle.coffeeRestApi.model.CoffeeMachine;
import com.oracle.coffeeRestApi.service.CoffeeMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller consumes and produces in Json format.
 */
@RestController
public class CoffeeMachineController {

    @Autowired
    CoffeeMachineService coffeeMachineService;

    @PostMapping(value = "/coffees/")
    CoffeeMachineResponse prepareCoffee(@RequestBody CoffeeMachineRequest coffeeRequest) {
        return coffeeMachineService.prepareCoffee(coffeeRequest);
    }

    @GetMapping("/coffeemachines/{id}")
    CoffeeMachineResponse getCoffee(@PathVariable Integer id) {
        return coffeeMachineService.getCoffee(id);
    }

    @GetMapping("/coffeemachines/")
    Map<Integer, CoffeeMachine> listCoffeeMachines() {
        return coffeeMachineService.listCoffeeMachines();
    }
}
