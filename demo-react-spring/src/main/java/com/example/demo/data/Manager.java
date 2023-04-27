package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Table(name = "manager")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Manager {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    private String[] roles;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public Manager(String name, String password, String... roles) {
        this.name = name;
        this.setPassword(password);
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) &&
                Objects.equals(name, manager.name) &&
                Objects.equals(password, manager.password) &&
                Arrays.equals(roles, manager.roles);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name, password);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }
}