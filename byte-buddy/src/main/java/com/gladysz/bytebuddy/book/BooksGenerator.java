package com.gladysz.bytebuddy.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BooksGenerator {

    private static final Random RANDOM = new Random();


    public static List<Book> generate(int howMuch) {
        List<Book> books = new ArrayList<>();
        for (int n = 0; n < howMuch; n++)
            books.add(randomBook());
        return books;
    }

    private BooksGenerator() {}


    private static Book randomBook() {

        return new Book(randomTitle(), randomAuthor(),RANDOM.nextInt(30) + 1980);
    }


    private static String randomAuthor() {

        return randomString(10, 20);
    }


    private static String randomTitle() {

        return randomString(5, 30);
    }


    private static String randomString(int minLen, int maxLen) {

        int howLong = RANDOM.nextInt(maxLen - minLen) + minLen;
        StringBuilder sb = new StringBuilder();

        for(int n = 0; n < howLong; n++) {
            char c = (char) (RANDOM.nextInt(90 - 65) + 65);
            sb.append(c);
        }
     return sb.toString();
    }
}
