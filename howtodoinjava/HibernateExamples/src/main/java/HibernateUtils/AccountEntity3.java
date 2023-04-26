package HibernateUtils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Account")
public class AccountEntity3 implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "ID",unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer accountId;

    @Column(name = "ACC_NO", unique = false, nullable = false, length = 100)
    private String accountNumber;


    @OneToOne (mappedBy = "accounts", fetch = FetchType.LAZY)
    EmployeeEntity2 employee;


    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public EmployeeEntity2 getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity2 employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (getClass() != obj.getClass())
            return false;
        AccountEntity3 ae = (AccountEntity3) obj;
        return new EqualsBuilder().append(getAccountId(), ((AccountEntity3) obj).getAccountId()).isEquals();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder(getAccountId() % 2 == 0 ? getAccountId() + 1 : getAccountId(), PRIME).toHashCode();
    }
}
