package BasicUpdates.JPACascadeTypes;

import HibernateUtils.AccountEntityOR;
import HibernateUtils.EmployeeEntityOR;
import HibernateUtils.HibernateUtil5;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrphanRemovalCascade {

    public static void main(String[] args) {

        Integer id = setupTestData();

        // ===== Play with the data

        Session s1 = HibernateUtil5.getSessionFactory().openSession();
        Transaction tx = s1.beginTransaction();

        // Get the employee and print no. of accts (should be 3)
        EmployeeEntityOR e1 = s1.load(EmployeeEntityOR.class, id);
        System.out.println("Step 1: " + e1.getAccounts().size());

        // remove account, verify that 2 accts remain
        e1.getAccounts().remove(e1.getAccounts().iterator().next());
        System.out.println("Step 2: " + e1.getAccounts().size());

        tx.commit();
        s1.close();

        // ====== Verify that what we did above actually persisted
        Session s2 = HibernateUtil5.getSessionFactory().openSession();
        s2.beginTransaction();

        // create a new instance and now verify that we have 2 accounts associated w/ the employee
        EmployeeEntityOR e2 = s2.load(EmployeeEntityOR.class, id);
        System.out.println("Step 3: " + e2.getAccounts().size() + " ");

        // Verify only 2 accounts in acct table
        Query query = s2.createQuery("from Account ");
        @SuppressWarnings("unchecked")
        List<AccountEntityOR> accounts = query.list();
        System.out.println("Step 4: " + accounts.size());

        s2.close();
        HibernateUtil5.shutdown();
    }

    private static int setupTestData() {
        Session session = HibernateUtil5.getSessionFactory().openSession();
        session.beginTransaction();

        // Create Employee
        EmployeeEntityOR emp = new EmployeeEntityOR();
        emp.setEmployeeId(1);
        emp.setEmail("BatmanWasAnOrphan");
        emp.setFirstName("Ed");
        emp.setLastName("Mangini");
        session.save(emp);

        // Create Accounts
        AccountEntityOR acc1 = new AccountEntityOR();
        AccountEntityOR acc2 = new AccountEntityOR();
        AccountEntityOR acc3 = new AccountEntityOR();

        acc1.setAccountId(1);
        acc2.setAccountId(2);
        acc3.setAccountId(3);

        acc1.setAccountNumber("11");
        acc2.setAccountNumber("22");
        acc3.setAccountNumber("33");

        acc1.setEmployee(emp);
        acc2.setEmployee(emp);
        acc3.setEmployee(emp);

        session.save(acc1);
        session.save(acc2);
        session.save(acc3);

        session.getTransaction().commit();

        Integer id = emp.getEmployeeId();
        session.close();

        return id;
    }
}
