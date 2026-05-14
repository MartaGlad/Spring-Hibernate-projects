package com.gladysz.bytebuddy.user;

public class User {

    private final String name;

    public User(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void doSomething() {
        System.out.println(this.name + " is doing something.");
    }
}
