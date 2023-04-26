package HelloWorld;

import HibernateUtils.EmployeeEntity;
import HibernateUtils.HibernateUtil;
import org.hibernate.Session;

public class ExecCode {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmail("eddie@demo.com");
        employeeEntity.setFirstName("eddie");
        employeeEntity.setLastName("fast");

        session.save(employeeEntity);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
