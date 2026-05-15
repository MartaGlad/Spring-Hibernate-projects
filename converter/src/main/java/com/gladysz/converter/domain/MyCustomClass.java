package com.gladysz.converter.domain;

public class MyCustomClass {

    private final String fieldOne;
    private final String fieldTwo;
    private final String fieldThree;

    public MyCustomClass(String field1, String field2, String field3) {
        this.fieldOne = field1;
        this.fieldTwo = field2;
        this.fieldThree = field3;
    }

    public String getFieldOne() {

        return fieldOne;
    }

    public String getFieldTwo() {

        return fieldTwo;
    }

    public String getFieldThree() {

        return fieldThree;
    }
}
