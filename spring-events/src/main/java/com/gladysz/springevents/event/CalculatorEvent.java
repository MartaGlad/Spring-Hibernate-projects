package com.gladysz.springevents.event;

import org.springframework.context.ApplicationEvent;

public class CalculatorEvent extends ApplicationEvent {

    private final String operation;
    private final Double number1;
    private final Double number2;
    private final Double result;


    public CalculatorEvent(Object source, String operation,
                           Double number1, Double number2, Double result) {
        super(source);
        this.operation = operation;
        this.number1 = number1;
        this.number2 = number2;
        this.result = result;
    }


    public String getOperation() {

        return operation;
    }


    public Double getNumber1() {

        return number1;
    }


    public Double getNumber2() {

        return number2;
    }


    public Double getResult() {

        return result;
    }
}
