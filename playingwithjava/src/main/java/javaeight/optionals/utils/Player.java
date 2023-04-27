package javaeight.optionals.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Generated;

import java.util.Optional;

@Getter @Setter
@AllArgsConstructor
@ToString
@Generated
public class Player {

    private String name;
    private int salary;

    public Player(String name) {
        this.name = name;
    }

    public Optional<Integer> getSalary() {
        return Optional.of(this.salary);
    }

}
