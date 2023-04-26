package StructuralDesignPatterns.Adapter.Second_Adapter_2;

/*
    This is very straightforward.
    - we map the fields we want to call to the ones in the client,
    so that the client isn't aware that the backend is using an incompatible
    legacy interface.


 */
public class EmployeeAdapterLdap implements Employee {

    private EmployeeLdap instance;

    public EmployeeAdapterLdap(EmployeeLdap instance) {
        this.instance = instance;
    }

    @Override
    public String getId() {
        return instance.getCn();
    }

    @Override
    public String getFirstName() {
        return instance.getGivenName();
    }

    @Override
    public String getLastName() {
        return instance.getSurname();
    }

    @Override
    public String getEmail() {
        return instance.getMail();
    }

    /*
            NOTE: "Technically if I provide more information in this Adapter, we are providing a
             Decorator.. which adds MORE information, rather than just translating two interfaces)

             Some would argue that additional helper code to make the interfaces match is just
             "part of the Adapter"
     */
    public String toString() {
        return "ID: " + instance.getCn();

    }
}
