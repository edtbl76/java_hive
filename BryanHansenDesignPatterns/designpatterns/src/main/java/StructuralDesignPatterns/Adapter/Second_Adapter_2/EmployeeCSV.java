package StructuralDesignPatterns.Adapter.Second_Adapter_2;

public class EmployeeCSV {
    private int id;
    private String firstname;
    private String lastname;
    private String emailAddress;

    public EmployeeCSV(String values) {
        String[] split = values.split(",");
        if (split.length == 4) {
            this.id = Integer.parseInt(split[0]);
            this.firstname = split[1];
            this.lastname = split[2];
            this.emailAddress = split[3];
        }
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
