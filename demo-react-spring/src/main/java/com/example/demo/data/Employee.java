package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Table(name = "employee")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = PRIVATE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "description")
    private String description;

    @Version
    @JsonIgnore
    @Column(name = "version")
    private Long version;

    @ManyToOne
    private Manager manager;

    public Employee(String firstName, String lastName, String description, Manager manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(description, employee.description) &&
                Objects.equals(version, employee.version) &&
                Objects.equals(manager, employee.manager);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, description, version, manager);
    }
}