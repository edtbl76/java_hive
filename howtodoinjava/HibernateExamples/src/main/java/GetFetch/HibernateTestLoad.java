package GetFetch;

import HibernateUtils.EmployeeEntity;
import HibernateUtils.HibernateUtil;
import org.hibernate.Session;

public class HibernateTestLoad {

    public static void main(String[] args) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        // Create Object
        EmployeeEntity emp = new EmployeeEntity();
        emp.setFirstName("Ed");
        emp.setLastName("Mangini");
        emp.setEmail("eddie@hibernateTestLoad.com");

        // Save it
        sessionOne.save(emp);

        //  Store gen'd Id
        Integer id = emp.getEmployeeId();
        sessionOne.getTransaction().commit();

        // =====

        // new session for testing  load() methods
        Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
        sessionTwo.beginTransaction();

        // First load() method ex
        EmployeeEntity emp1 =  (EmployeeEntity) sessionTwo.load(EmployeeEntity.class, id);
        System.out.println(emp1.getFirstName() + " - " + emp1.getLastName());

        sessionTwo.getTransaction().commit();

        // =====

        Session sessionThree = HibernateUtil.getSessionFactory().openSession();
        sessionThree.beginTransaction();

        EmployeeEntity emp2 = (EmployeeEntity) sessionThree.load(EmployeeEntity.class.getName(), id);
        System.out.println(emp2.getFirstName() + " - " + emp2.getLastName());

        sessionThree.getTransaction().commit();

        // =====

        Session sessionFour = HibernateUtil.getSessionFactory().openSession();
        sessionFour.beginTransaction();

        EmployeeEntity emp3 = new EmployeeEntity();
        sessionFour.load(emp3, id);
        System.out.println(emp3.getFirstName() + " - " + emp3.getLastName());

        sessionFour.getTransaction().commit();

        HibernateUtil.shutdown();
    }
}
