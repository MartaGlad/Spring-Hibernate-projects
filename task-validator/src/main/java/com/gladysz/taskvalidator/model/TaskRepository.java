package com.gladysz.taskvalidator.model;

import com.gladysz.taskvalidator.annotation.ShowSuper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ShowSuper(separator = " =>> ")
public class TaskRepository {

    public static List<TaskDto> getRepository() {

        List<TaskDto> dtos = new ArrayList<>();

        dtos.add(new TaskDto(LocalDate.of(2022, 3, 10), "Walk the dog",  4));
        dtos.add(new TaskDto(LocalDate.of(2022, 3, 10), "Walk the dog", 2));
        dtos.add(new TaskDto(LocalDate.of(2022, 4, 11), "Do the laundry", 3));
        dtos.add(new TaskDto(LocalDate.of(2022, 4, 18), "Pay the rent", 2));

        return dtos;
    }
}