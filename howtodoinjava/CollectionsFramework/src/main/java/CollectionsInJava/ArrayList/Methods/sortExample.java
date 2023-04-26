package CollectionsInJava.ArrayList.Methods;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;

public class sortExample {

    public static void main(String[] args) {
        ArrayList<Employee1> emps = new ArrayList<>();


        emps.add(new Employee1("Jesus", LocalDate.of(0, Month.DECEMBER, 25)));
        emps.add(new Employee1("Ed", LocalDate.of(1976, Month.OCTOBER, 15)));
        emps.add(new Employee1("Nina", LocalDate.of(1900, Month.JULY, 7)));
        emps.add(new Employee1("Shazam", LocalDate.of(1950, Month.APRIL, 23)));

        System.out.println("Sorted By Name: ");
        emps.sort(new NameSorter());
        System.out.println(emps);

        System.out.println("Sorted By Age: ");
        emps.sort(new AgeSorter());
        System.out.println(emps);
    }
}

class NameSorter implements Comparator<Employee1> {

    @Override
    public int compare(Employee1 o1, Employee1 o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}

class AgeSorter implements Comparator<Employee1> {

    @Override
    public int compare(Employee1 o1, Employee1 o2) {
        return o2.getDob().compareTo(o1.getDob());
    }
}

