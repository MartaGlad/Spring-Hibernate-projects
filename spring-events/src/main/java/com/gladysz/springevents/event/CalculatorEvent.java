package com.gladysz.springevents.event;

import org.springframework.context.ApplicationEvent;

public class CalculatorEvent extends ApplicationEvent {

    private String operation;
    private Double numebr1;
    private Double numebr2;
    private Double result;


    public CalculatorEvent(Object source, String operation,
                           Double numebr1, Double numebr2,  Double result) {
        super(source);
        this.operation = operation;
        this.numebr1 = numebr1;
        this.numebr2 = numebr2;
        this.result = result;
    }


    public String getOperation() {

        return operation;
    }


    public Double getNumebr1() {

        return numebr1;
    }


    public Double getNumebr2() {

        return numebr2;
    }


    public Double getResult() {

        return result;
    }
}
