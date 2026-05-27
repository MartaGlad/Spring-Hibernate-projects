package com.gladysz.taskvalidator.annotation;

import com.gladysz.taskvalidator.validator.DateMinValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateMinValidator.class)
public @interface DateMin {

    String message() default "Earlier date than expected";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "2020-01-01";
}
