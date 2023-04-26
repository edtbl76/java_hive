package BasicUpdates.EntityEqualityIdentity;

import HibernateUtils.EmployeeEntity;
import HibernateUtils.HibernateUtil5;
import org.hibernate.Session;

public class ObjectsFetchedFromSameSession {

    public static void main(String[] args) {
        Session session1 = HibernateUtil5.getSessionFactory().openSession();

        // Create a session that creates/saves/commits an object to DB
        session1.beginTransaction();
        EmployeeEntity emp = new EmployeeEntity();
        emp.setFirstName("Joe");
        emp.setLastName("Hoe");
        emp.setEmail("joe@hoe.com");
        session1.save(emp);
        session1.getTransaction().commit();

        // Get the gen'd iD
        Integer id = emp.getEmployeeId();

        // Create a new Session
        Session session2 = HibernateUtil5.getSessionFactory().openSession();
        session2.beginTransaction();

        // Create two more objects based on get()
        EmployeeEntity eo1 = session2.get(EmployeeEntity.class, id);
        EmployeeEntity eo2 = session2.get(EmployeeEntity.class, id);

        // Compare them
        System.out.println(eo1 == eo2);

        /*
            This is going to show "TRUE"
            - This means that if we get two instances of the same object in a single session... they will actually
            point to the SAME Java Object instance.
         */
        HibernateUtil5.shutdown();

    }
}
