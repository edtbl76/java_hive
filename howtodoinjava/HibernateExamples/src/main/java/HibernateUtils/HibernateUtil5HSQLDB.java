package HibernateUtils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil5HSQLDB {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();
                configuration.setProperties(setTheseProperties());
                configuration.addAnnotatedClass(EmployeeEntity.class);
                configuration.addAnnotatedClass(AccountEntity.class);

                // registrate!
                registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                // Create SF
                sessionFactory = configuration.buildSessionFactory(registry);

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null)
            StandardServiceRegistryBuilder.destroy(registry);
    }

    private static Properties setTheseProperties() {
        Properties props = new Properties();

        props.put(Environment.DRIVER, "org.hsqldb.jdbc.JDBCDriver");
        props.put(Environment.URL, "jdbc:hsqldb:mem:hibernatetest");
        props.put(Environment.USER, "sa");
        props.put(Environment.PASS, "");
        props.put(Environment.DIALECT, "org.hibernate.dialect.HSQLDialect");
        props.put(Environment.SHOW_SQL, "true");
        props.put(Environment.HBM2DDL_AUTO, "update");

        return props;
    }
}
