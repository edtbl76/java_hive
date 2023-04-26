package BehavioralDesignPatterns.Memento.MementoDemo_1;

public class MementoDemo {

    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();

        Employee employee = new Employee();

        employee.setName("Jenny");
        employee.setAddress("MA");
        employee.setPhone("867-5309");

        System.out.println(" --- Jenny Before save: ");
        System.out.println(employee);
        caretaker.save(employee);

        // Add area code
        employee.setPhone("978-867-5309");
        caretaker.save(employee);

        System.out.println(" --- Jenny After save: ");
        System.out.println(employee);

        // Accident.
        employee.setPhone("8014ythvsfd");
        // UNDO
        caretaker.revert(employee);

        System.out.println(" --- Undo Mistake: ");
        System.out.println(employee);

        // revert again!
        caretaker.revert(employee);
        System.out.println(" --- Undo Again: ");
        System.out.println(employee);


    }
}
