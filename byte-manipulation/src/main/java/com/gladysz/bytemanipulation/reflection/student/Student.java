package com.gladysz.bytemanipulation.reflection.student;

import java.util.Random;

public class Student {

    private final String indexNumber;
    private static final Random rand = new Random();


    public Student(int z) {

        this.indexNumber = generateIndexNumber(z);
    }


    private static String generateIndexNumber(int z) {

        String allChars = "0123456789";
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < z; i++) {
            sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        }
        return sb.toString();
    }
}
