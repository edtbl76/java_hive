package com.java8.DefaultMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ClassUsersInterfaceExample2 {

    public static void main(String[] args) {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal());
        list.add(new Animal());
        list.add(new Animal());

        list.forEach((Consumer<ExampleInterface>) ExampleInterface::doSomething);
    }
}

class Animal implements ExampleInterface { }