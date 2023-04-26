package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class DeepCopying {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Create ArrayList
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Ed", new Date(1976, 10 , 15)));
        System.out.println(employees);

        // Clone it
        ArrayList<Employee> employeesClone = new ArrayList<>();

        // You have to use the Iterator to perform a deep copy. Any other tricks will lead to a shallow copy
        /*
            The deep copy will ensure that an object is copied WITH its references.
            Shallow copies don't.
         */
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            employeesClone.add((Employee) iterator.next().clone());
        }

        // Change it
        employeesClone.get(0).setDob(new Date(1981, 10, 29));
        employeesClone.get(0).setId(2L);
        employeesClone.get(0).setName("V");

        System.out.println(employees);
        System.out.println(employeesClone);
    }
}

class Employee implements Cloneable {

    private Long id;
    private String name;
    private Date dob;

    public Employee(Long id, String name, Date dob) {
        super();
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Employee clone = null;
        try {
            clone = (Employee) super.clone();

            // Copy new date object to cloned method
            clone.setDob((Date) this.getDob().clone());
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
        return clone;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + "]";
    }
}
