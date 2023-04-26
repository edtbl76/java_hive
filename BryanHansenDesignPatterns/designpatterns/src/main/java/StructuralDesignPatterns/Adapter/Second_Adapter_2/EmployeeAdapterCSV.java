package StructuralDesignPatterns.Adapter.Second_Adapter_2;

public class EmployeeAdapterCSV implements Employee{

    private EmployeeCSV instance;

    public EmployeeAdapterCSV(EmployeeCSV instance) {
        this.instance = instance;
    }

    /*
        Shows example of type conversion in an adapter.
     */
    @Override
    public String getId() {
        return String.valueOf(instance.getId());
    }

    @Override
    public String getFirstName() {
        return instance.getFirstname();
    }

    @Override
    public String getLastName() {
        return instance.getLastname();
    }

    @Override
    public String getEmail() {
        return instance.getEmailAddress();
    }

    @Override
    public String toString() {
        return "ID: " + instance.getId();
    }
}
