package com.emangini.Builders;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BuilderEmployee {

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
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder(getId() % 2 == 0 ? getId() + 1 : getId(), PRIME).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(obj == this)
            return true;

        if(obj.getClass() != getClass())
            return false;

        BuilderEmployee be = (BuilderEmployee) obj;

        return new EqualsBuilder().append(getId(), be.getId()).isEquals();
    }
}
