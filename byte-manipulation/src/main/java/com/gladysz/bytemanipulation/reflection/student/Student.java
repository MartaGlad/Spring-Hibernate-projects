package com.gladysz.bytemanipulation.reflection.student;

import java.util.Random;

public class Student {

    private String indexNumber;

    private static String generateIndexNumber(int z) {
        String allChars = "0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < z; i++) {
            sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        }
        return sb.toString();
    }


    public Student(int z) {
        this.indexNumber = generateIndexNumber(z);
    }
}
