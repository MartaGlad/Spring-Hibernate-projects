package com.gladysz.taskvalidator.controller;

import com.gladysz.taskvalidator.model.TaskDto;
import com.gladysz.taskvalidator.model.TaskRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Validated
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @GetMapping("/{priority}")
    public ResponseEntity<List<TaskDto>> getFiltered(@PathVariable @Min(1) @Max(5) int priority) {

        List<TaskDto> dtos = TaskRepository.getRepository()
                .stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskDto taskDto) {

        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException exc) {

        Map<String, String> errorsMap = new HashMap<>();

        List<ObjectError> errorsList = exc.getBindingResult().getAllErrors();

        errorsList.forEach((errorObject) -> {
            FieldError fieldError = (FieldError) errorObject;
            String name = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorsMap.put(name, message);
        });
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleException(ConstraintViolationException exc) {

        Map<String, String> resultMap = new HashMap<>();
        String[] errorArray = exc.getMessage().split(":");
        resultMap.put(errorArray[0], errorArray[1]);

        return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
    }
}
