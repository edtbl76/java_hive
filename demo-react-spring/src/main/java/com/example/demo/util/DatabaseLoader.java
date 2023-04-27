package com.example.demo.util;

import com.example.demo.data.Employee;
import com.example.demo.data.EmployeeRepository;
import com.example.demo.data.Manager;
import com.example.demo.data.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employees;
    private final ManagerRepository managers;

    @Autowired
    public DatabaseLoader(EmployeeRepository employees, ManagerRepository managers) {
        this.employees = employees;
        this.managers = managers;
    }


    @Override
    public void run(String... args)  {

        Manager ed = this.managers.save(new Manager("Edward", "Mangini", "ROLE_MANAGER"));
        Manager vanessa= this.managers.save(new Manager("Vanessa", "Mangini", "ROLE_MANAGER"));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("Edward", "whocares",
                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")));


        List<Employee> boys = List.of(
                new Employee("Michael", "Mangini", "College Student", ed),
                new Employee("Connor", "Mangini", "Complains About Pants", ed),
                new Employee("James", "Perry", "Complains About Everything", ed)
        );

        this.employees.saveAll(boys);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("Vanessa", "whocares",
                        AuthorityUtils.createAuthorityList("ROLE_MANAGER")));


        List<Employee> notBoys = List.of(
                new Employee("Parker", "Underwood", "Tazmanian Devil", vanessa),
                new Employee("Gravy", "Beaglestorm", "Puppy Dawg", vanessa)
        );

        this.employees.saveAll(notBoys);


        SecurityContextHolder.clearContext();
    }
}

