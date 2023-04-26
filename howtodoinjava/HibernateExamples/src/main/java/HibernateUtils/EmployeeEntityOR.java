package HibernateUtils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity()
@Table(name = "Employee")
public class EmployeeEntityOR implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer employeeId;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @OneToMany(orphanRemoval = true, mappedBy = "employee")
    private Set<AccountEntityOR> accounts;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<AccountEntityOR> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Set<AccountEntityOR> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (getClass() != obj.getClass())
            return false;
        EmployeeEntityOR ee = (EmployeeEntityOR) obj;
        return new EqualsBuilder().append(getEmployeeId(), ((EmployeeEntityOR) obj).getEmployeeId()).isEquals();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder(getEmployeeId() % 2 == 0 ? getEmployeeId() + 1 : getEmployeeId(), PRIME).toHashCode();
    }
}
