package com.gladysz.taskvalidator.model;

import com.gladysz.taskvalidator.annotation.DateMin;
import com.gladysz.taskvalidator.annotation.Range;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class TaskDto {

    @NotNull
    @DateMin("2021-01-01")
    LocalDate when;

    @NotNull
    String title;

    @Range(min = 1, max = 5)
    int priority;
}
