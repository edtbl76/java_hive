package collections.hashmap.identityhashmap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import utils.Generated;

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

    @SuppressWarnings({"java:S2259"})
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Employee other = (Employee) obj;
        if (this.id != other.getId())
            return false;
        if (this.name == null) {
            return other.getName() == null;
        } else return this.name.equals(other.getName());
    }
}
