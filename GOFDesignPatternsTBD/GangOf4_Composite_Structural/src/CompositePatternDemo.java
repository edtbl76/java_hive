public class CompositePatternDemo {

    public static void main(String[] args) {

        Employee CEO = new Employee("John", "CEO", 30000 );
        Employee vpSales = new Employee("Robert", "Sales", 20000);
        Employee vpMarketing = new Employee("Michelle", "Marketing", 20000);
        Employee clerk1 = new Employee("Laura", "Marketing", 10000);
        Employee clerk2 = new Employee("Bill", "Marketing", 10000);
        Employee salesExec1 = new Employee("Richie", "Sales", 10000);
        Employee salesExec2 = new Employee("Lou", "Sales", 10000);

        CEO.add(vpSales);
        CEO.add(vpMarketing);

        vpSales.add(salesExec1);
        vpSales.add(salesExec2);

        vpMarketing.add(clerk1);
        vpMarketing.add(clerk2);

        System.out.println(CEO);

        for (Employee headEmployee : CEO.getDirectReports()) {
            System.out.println(headEmployee);

            for (Employee emp : headEmployee.getDirectReports()) {
                System.out.println(emp);
            }
        }

    }
}
