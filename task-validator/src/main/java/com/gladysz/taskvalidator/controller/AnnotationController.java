package com.gladysz.taskvalidator.controller;

import com.gladysz.taskvalidator.annotation.ShowSuper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/annotations")
public class AnnotationController {

    @GetMapping("/")
    public ResponseEntity<String> getFiltered(@RequestParam String fullClassName) {

        try {
            Class<?> clazz = AnnotationController.class.getClassLoader().loadClass(fullClassName);
            String response = clazz.getCanonicalName();

            if (clazz.isAnnotationPresent(ShowSuper.class)){
                ShowSuper annotation = clazz.getAnnotation(ShowSuper.class);
                response = clazz.getGenericSuperclass().getTypeName() + annotation.separator() + response;
            }
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ClassNotFoundException e) {
            return new ResponseEntity<>(fullClassName + " is unknow", HttpStatus.BAD_REQUEST);
        }
    }
}
