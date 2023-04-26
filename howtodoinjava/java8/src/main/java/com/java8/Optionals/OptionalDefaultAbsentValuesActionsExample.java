package com.java8.Optionals;

import java.util.Optional;

public class OptionalDefaultAbsentValuesActionsExample {

    public static void main(String[] args) {
        Optional<Company> companyOptional = Optional.empty();

        Company company1 = companyOptional.orElse(new Company());
        System.out.println(company1);


        try {
            Company company2 = companyOptional.orElseThrow(IllegalStateException::new);
            System.out.println(company2);   // this is moot. We won't get here, because the statement above throws.
        }  catch  (IllegalStateException ise) {
            System.out.println(ise.toString());
        }

    }
}

class Company {

    private String name;

    Company() {
        this.name = "Whatever";
    }

    Company(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "My Company";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
