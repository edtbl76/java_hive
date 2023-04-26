package Persist;

import HibernateUtils.EmployeeEntity;
import HibernateUtils.HibernateUtil;
import org.hibernate.Session;

public class InsertQueryExample {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmail("somebody@somebody.com");
        emp.setFirstName("some");
        emp.setLastName("body");

        session.save(emp);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
