package BasicUpdates.Mappings;

import HibernateUtils.*;
import org.hibernate.Session;

public class OneToOneTest2 {

    public static void main(String[] args) {
        /*
            This MUST use AccountEntity2.
            - this is using a an association that is managed by the EmployeeEntity
            - (AccountEntity is annotated w/ mappedBy)
         */
        Session session1 = HibernateUtil5.getSessionFactory().openSession();
        session1.beginTransaction();

        // Create new Employee Object
        EmployeeEntity2 emp = new EmployeeEntity2();
        emp.setFirstName("Ed");
        emp.setLastName("Mangini");
        emp.setEmail("whatever");

        // Create account
        AccountEntity2 acc = new AccountEntity2();
        acc.setAccountNumber("DUMMY");

        /**
         * We only need to set the account that is managed by the Employee.
         * This is the value of having the entire association managed by one end.
         */
        emp.setAccount(acc);

        session1.save(acc);
        session1.save(emp);
        session1.getTransaction().commit(); // COMMIT!

        // === Get THe Ids.

        Integer empId = emp.getEmployeeId();
        Integer accId = acc.getAccountId();

        Session session2 = HibernateUtil5.getSessionFactory().openSession();
        session2.beginTransaction();

        EmployeeEntity2 employee = session2.get(EmployeeEntity2.class, empId);
        AccountEntity2 account = session2.get(AccountEntity2.class, accId);

        System.out.println(employee.getEmployeeId());
        System.out.println(employee.getAccount().getAccountId());
        System.out.println(account.getAccountId());
        System.out.println(account.getEmployee().getEmployeeId());

        HibernateUtil5.shutdown();
    }
}
