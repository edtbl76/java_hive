package collections.hashmap.goodkeydemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import utils.Generated;

@SuppressWarnings("all")
@Generated
@Getter @Setter
@AllArgsConstructor
public class Employee {

    private int id;
    private String name;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.id;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Employee employee = (Employee) obj;
        return this.id == employee.getId();
    }
}
