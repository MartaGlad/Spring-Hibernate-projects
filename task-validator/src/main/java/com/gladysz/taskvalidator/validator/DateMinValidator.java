package com.gladysz.taskvalidator.validator;

import com.gladysz.taskvalidator.annotation.DateMin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateMinValidator implements ConstraintValidator<DateMin, LocalDate> {

    private String annotationValue;

    @Override
    public boolean isValid(LocalDate dateValue, ConstraintValidatorContext context) {

        LocalDate dateMin = LocalDate.parse(annotationValue, DateTimeFormatter.ISO_DATE);

        return dateValue.isAfter(dateMin);
    }


    @Override
    public void initialize(DateMin constraintAnnotation) {

        annotationValue = constraintAnnotation.value();
    }
}
