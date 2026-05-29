package com.gladysz.bytemanipulation.reflection.student;


import com.gladysz.taskvalidator.annotation.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/byte-manipulation/student")
public class StudentController {

    private static final Field INDEX_NUMBER_FIELD;

    static {
        try {
            INDEX_NUMBER_FIELD = Student.class.getDeclaredField("indexNumber");
            INDEX_NUMBER_FIELD.setAccessible(true);

        } catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        }
    }


    @GetMapping(path = "/generate")
    public Map<Integer, String> generateStudents(
            @RequestParam(defaultValue = "20") @Range(min = 1, max = 100) Integer n,
            @RequestParam(defaultValue = "10") @Range(min = 1, max = 50) Integer z)
            throws IllegalAccessException {


        Map<Integer, String> studentsMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Student student = new Student(z);
            String value = (String) INDEX_NUMBER_FIELD.get(student);

            studentsMap.put(
                    System.identityHashCode(student),
                    value
            );
        }
        return studentsMap;
    }
}






