package com.gladysz.springevents.domain;

public class NumbersDto {

    private Double number1;
    private Double number2;

    public NumbersDto() {
    }


    public NumbersDto(Double number1, Double number2) {
        this.number1 = number1;
        this.number2 = number2;
    }


    public Double getNumber1() {
        return number1;
    }


    public void setNumber1(Double number1) {
        this.number1 = number1;
    }


    public Double getNumber2() {
        return number2;
    }


    public void setNumber2(Double number2) {
        this.number2 = number2;
    }
}
