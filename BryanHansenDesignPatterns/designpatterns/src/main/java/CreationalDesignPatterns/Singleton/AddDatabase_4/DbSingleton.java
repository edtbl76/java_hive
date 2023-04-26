package CreationalDesignPatterns.Singleton.AddDatabase_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingleton {

    /*
        volatile is used to make the instance thread safe.
     */
    private static volatile DbSingleton instance = null;
    private static volatile Connection connection = null;

    /*
        prevents others from instantiating this object using the 'new' keyword.
     */
    private DbSingleton() {

        /*
            Register driver
         */
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
            Prevents creation of new instance via reflection
         */
        if (connection != null) {
            throw new RuntimeException("Use getConnection() method to create");
        }
        /*
            Prevents creation of new instance via reflection
         */
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    /*
        Standard accessor for a singleton.
        - avoid make entire class ALWAYS synchronized due to being a performance hit
        - only synchronize on creation.
     */
    public static DbSingleton getInstance() {
        /*
            This null check introduces lazy loaded
         */
        if (instance == null) {
            // DoubleLocking Check, used for providing thread safety (standard lazy load is not thread safe)
            synchronized (DbSingleton.class) {
                if (instance == null) {
                    instance = new DbSingleton();
                }
            }

        }
        return instance;
    }

    /*
        Not static, because we'll probably want to pool/lazily load connections from the static
        CreationalDesignPatterns.Singleton instance.
        - This means we can't have it static, because we should be able to support multiple concurrent connections
     */
    public Connection getConnection() {
        if (connection == null) {
            synchronized (DbSingleton.class) {
                if (connection == null) {
                    try {
                        String dbUrl = "jdbc:derby:memory:codejava/webdb;create=true";
                        connection = DriverManager.getConnection(dbUrl);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;

    }
}
