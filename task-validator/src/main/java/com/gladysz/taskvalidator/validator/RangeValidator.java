package com.gladysz.taskvalidator.validator;

import com.gladysz.taskvalidator.annotation.Range;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class RangeValidator implements ConstraintValidator<Range, Integer> {

    private int min, max;


    @Override
    public void initialize(Range constraintAnnotation) {

        min =  constraintAnnotation.min();
        max =  constraintAnnotation.max();
    }


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return value == null || (value >= min && value <= max);
    }
}


