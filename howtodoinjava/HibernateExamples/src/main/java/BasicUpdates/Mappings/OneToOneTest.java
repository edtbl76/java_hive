package BasicUpdates.Mappings;

import HibernateUtils.AccountEntity;
import HibernateUtils.EmployeeEntity;
import HibernateUtils.HibernateUtil5;
import org.hibernate.Session;

public class OneToOneTest {

    public static void main(String[] args) {
        /*
            NOTE: make sure that the Associations in AccountEntity and EmployeeEntity are both @OneToOne
         */
        Session session1 = HibernateUtil5.getSessionFactory().openSession();
        session1.beginTransaction();

        // Create new Employee Object
        EmployeeEntity emp = new EmployeeEntity();
        emp.setFirstName("Ed");
        emp.setLastName("Mangini");
        emp.setEmail("whatever");

        // Create account
        AccountEntity acc = new AccountEntity();
        acc.setAccountNumber("DUMMY");
        emp.setAccount(acc);
        acc.setEmployee(emp);

        session1.save(acc);
        session1.save(emp);
        session1.getTransaction().commit(); // COMMIT!

        // === Get THe Ids.

        Integer empId = emp.getEmployeeId();
        Integer accId = acc.getAccountId();

        Session session2 = HibernateUtil5.getSessionFactory().openSession();
        session2.beginTransaction();

        EmployeeEntity employee = session2.get(EmployeeEntity.class, empId);
        AccountEntity account = session2.get(AccountEntity.class, accId);

        System.out.println(employee.getEmployeeId());
        System.out.println(employee.getAccount().getAccountId());
        System.out.println(account.getAccountId());
        System.out.println(account.getEmployee().getEmployeeId());

        HibernateUtil5.shutdown();
    }
}
