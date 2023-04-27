package collections.arraylists.comparisons;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Generated;

@Getter @Setter
@EqualsAndHashCode
@ToString
@Generated
public class Member implements Comparable<Member> {

    private String name;
    private int age;

    public Member(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /*
        Demonstrates a compareTo() method
        based on an integer.
     */
    @Override
    public int compareTo(Member member) {
        /*
            We'll sort the employee based on age in ascending order
            - returns a negative integer if this.age is < member.getAge()
            - returns 0 if this.age == member.getAge()
            - returns positive integer if this.age > member.getAge()
         */
        return (this.age - member.getAge());
    }
}
