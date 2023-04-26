package com.java8.Optionals;

import java.util.Optional;

public class OptionalAndFilter_RejectingValues {

    public static void main(String[] args) {
        Optional<Company> companyOptional = Optional.empty();

        companyOptional.filter(department -> "Finance".equals(department.getName()))
                .ifPresent(System.out::println);
    }
}
