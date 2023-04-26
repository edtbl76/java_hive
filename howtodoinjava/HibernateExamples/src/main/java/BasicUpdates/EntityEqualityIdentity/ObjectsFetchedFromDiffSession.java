package BasicUpdates.EntityEqualityIdentity;

import HibernateUtils.EmployeeEntity;
import HibernateUtils.HibernateUtil5;
import org.hibernate.Session;

public class ObjectsFetchedFromDiffSession {

    public static void main(String[] args) {
        Session baseSession = HibernateUtil5.getSessionFactory().openSession();

        baseSession.beginTransaction();
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmail("1");
        emp.setFirstName("Ed");
        emp.setLastName("Mangini");
        baseSession.save(emp);
        baseSession.getTransaction().commit();

        Integer id = emp.getEmployeeId();
        // ====

        Session thing1 = HibernateUtil5.getSessionFactory().openSession();
        thing1.beginTransaction();
        EmployeeEntity thing1Emp = thing1.get(EmployeeEntity.class, id);

        Session thing2 = HibernateUtil5.getSessionFactory().openSession();
        thing2.beginTransaction();
        EmployeeEntity thing2Emp = thing2.get(EmployeeEntity.class, id);

        /*
            The result of the first comparison is FALSE.
            this demonstrates that the 2 instances are each separate
            objects in memory

            The result of the second comparison is TRUE.
            (This is because I properly implemented equals() and
            hashCode() in my object class.
         */
        System.out.println(thing1Emp == thing2Emp);
        System.out.println(thing1Emp.equals(thing2Emp));

        HibernateUtil5.shutdown();


    }
}
