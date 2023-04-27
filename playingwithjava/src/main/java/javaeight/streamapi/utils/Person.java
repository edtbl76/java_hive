package javaeight.streamapi.utils;

import lombok.*;
import utils.Generated;

@Generated
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Person {

    private String name;
    private int age;

    public Person(String name) {
        this.name = name;
    }
}
