package com.emangini.ContractBetweenEqualsAndHashCode;

public class BetterEmployee {

    private Integer id;
    private String firstName;
    private String lastName;
    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (getClass() != obj.getClass())
            return false;

        BetterEmployee be = (BetterEmployee)obj;
        return (this.getId() == (be.getId()));
    }
}
