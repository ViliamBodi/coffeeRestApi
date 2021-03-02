package com.oracle.coffeeRestApi.service;

import com.oracle.coffeeRestApi.api.CoffeeMachineRequest;
import com.oracle.coffeeRestApi.api.CoffeeMachineResponse;
import com.oracle.coffeeRestApi.exception.CoffeeMachineNotFoundException;
import com.oracle.coffeeRestApi.model.CoffeeMachine;
import com.oracle.coffeeRestApi.repository.CoffeeMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CoffeeMachineService {

    @Autowired
    CoffeeMachineRepository coffeeMachineRepository;

    /**
     * Coffee machine can produce 1 coffee at a time until the coffee is picked up.
     * @param request
     * @return
     */
    public CoffeeMachineResponse prepareCoffee(CoffeeMachineRequest request) {
        Integer coffeeMachineId = request.getCoffeeMachineId();
        CoffeeMachine coffeeMachine = coffeeMachineRepository.getCoffeeMachine(coffeeMachineId);

        if (coffeeMachine == null) {
            throw new CoffeeMachineNotFoundException(coffeeMachineId);
        }

        if (coffeeMachine.getCompletionTime() == null) {
            coffeeMachine.setCompletionTime(
                    LocalDateTime.now().plusSeconds(request.getCoffeeType().getPreparationTime()));
            return buildResponse("Your " + request.getCoffeeType() + " will be ready in "
                    + request.getCoffeeType().getPreparationTime() + " seconds.", HttpStatus.CREATED);
        } else {
            return buildResponse("This coffee machine is in use.", HttpStatus.ACCEPTED);
        }
    }

    /**
     * Checks the status of the coffee machine. If the the coffee is ready, it is expected that it is picked up asap.
     * @param id
     * @return
     */
    public CoffeeMachineResponse getCoffee(int id) {
        CoffeeMachine coffeeMachine = coffeeMachineRepository.getCoffeeMachine(id);

        if (coffeeMachine == null) {
            throw new CoffeeMachineNotFoundException(id);
        }

        if (coffeeMachine.getCompletionTime() == null) {
            return buildResponse("This machine is ready to use.", HttpStatus.OK);
        } else {
            int ccaReadyTime = coffeeMachine.getCompletionTime().getSecond() - LocalDateTime.now().getSecond();
            if (ccaReadyTime > 0) {
                return buildResponse("Your coffee will be ready in " + ccaReadyTime + " seconds.", HttpStatus.OK);
            } else {
                coffeeMachine.setCompletionTime(null);
                return buildResponse("Your coffee is ready. You can pick it up.", HttpStatus.OK);
            }
        }
    }

    private CoffeeMachineResponse buildResponse(String message, HttpStatus status) {
        return CoffeeMachineResponse.builder()
                .status(status.value())
                .message(message)
                .build();
    }

    public Map<Integer, CoffeeMachine> listCoffeeMachines() {
        return coffeeMachineRepository.getCoffeeMachines();
    }
}
