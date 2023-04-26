/*
Example of class/static variables. (CONSTANTS!)
 */
@SuppressWarnings("FieldCanBeLocal")
public class ClassVarEx {

    // Class Variable that is NOT A CONSTANT (no final modifier)
    @SuppressWarnings("FieldCanBeLocal")
    private static double salary;

    // Run of the mill Class/Static Constant
    static final String DEPARTMENT = "Home Goods";

    public static void main(String[] args) {
        salary = 1000;
        System.out.println(DEPARTMENT + " average salary: " + salary);
    }
}
