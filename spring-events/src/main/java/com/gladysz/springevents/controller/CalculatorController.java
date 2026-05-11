package com.gladysz.springevents.controller;

import com.gladysz.springevents.domain.NumbersDto;
import com.gladysz.springevents.event.CalculatorEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/calculator")
public class CalculatorController {

    private final ApplicationEventPublisher publisher;

    CalculatorController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping(path = "/add")
    public Double add(@RequestBody NumbersDto numbersDto) {

        Double result = numbersDto.getNumber1() + numbersDto.getNumber2();

        publisher.publishEvent(
                new CalculatorEvent(
                        this,
                        "add",
                        numbersDto.getNumber1(),
                        numbersDto.getNumber2(),
                        result
                )
        );
        return result;
    }


    @PostMapping(path = "/subtract")
    public Double subtract(@RequestBody NumbersDto numbersDto) {

        Double result = numbersDto.getNumber1() - numbersDto.getNumber2();

        publisher.publishEvent(
                new CalculatorEvent(
                        this,
                        "subtract",
                        numbersDto.getNumber1(),
                        numbersDto.getNumber2(),
                        result
                )
        );
        return result;
    }


    @PostMapping(path = "/multiply")
    public Double multiply(@RequestBody NumbersDto numbersDto) {

        Double result = numbersDto.getNumber1() * numbersDto.getNumber2();

        publisher.publishEvent(
                new CalculatorEvent(
                        this,
                        "multiply",
                        numbersDto.getNumber1(),
                        numbersDto.getNumber2(),
                        result
                )
        );
        return result;
    }


    @PostMapping(path = "/divide")
    public Double divide(@RequestBody NumbersDto numbersDto) {

        if (numbersDto.getNumber2() == 0) {

            publisher.publishEvent(
                    new CalculatorEvent(
                            this,
                            "divide by zero attempt",
                            numbersDto.getNumber1(),
                            numbersDto.getNumber2(),
                            null
                    )
            );

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot divide by zero!");
        }

        Double result = numbersDto.getNumber1() / numbersDto.getNumber2();

        publisher.publishEvent(
                new CalculatorEvent(
                        this,
                        "divide",
                        numbersDto.getNumber1(),
                        numbersDto.getNumber2(),
                        result
                )
        );
        return result;
    }
}
