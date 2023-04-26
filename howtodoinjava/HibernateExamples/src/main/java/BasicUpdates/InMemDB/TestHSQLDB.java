package BasicUpdates.InMemDB;

import HibernateUtils.EmployeeEntity;
import HibernateUtils.HibernateUtil5HSQLDB;
import org.hibernate.Session;

public class TestHSQLDB {

    public static void main(String[] args) {
        Session session = HibernateUtil5HSQLDB.getSessionFactory().openSession();
        session.beginTransaction();

        EmployeeEntity emp = new EmployeeEntity();
        emp.setFirstName("Ed");
        emp.setLastName("Mangini");
        emp.setEmail("assbucket");
        session.save(emp);
        session.getTransaction().commit();
        HibernateUtil5HSQLDB.shutdown();
    }
}
