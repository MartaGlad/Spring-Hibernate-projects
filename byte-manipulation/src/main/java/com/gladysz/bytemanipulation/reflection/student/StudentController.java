package com.gladysz.bytemanipulation.reflection.student;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    @PostMapping(path = "/create")
    public Map<Integer, String> createStudents(@RequestParam(defaultValue = "20") int n,
                                               @RequestParam(defaultValue = "10") int z)
            throws NoSuchFieldException, IllegalAccessException {

        Student[] studentsTab = new Student[n];
        Map<Integer, String> studentsMap = new HashMap<>();

        Field indexNumberField = Student.class.getDeclaredField("indexNumber");
        indexNumberField.setAccessible(true);

        for (int i = 0; i < n; i++) {
            Student student = new Student(z);
            studentsTab[i] = student;
        }

        for (Student student : studentsTab) {
            String value = (String) indexNumberField.get(student);
            studentsMap.put(System.identityHashCode(student), value);
        }
        return studentsMap;
    }
}






