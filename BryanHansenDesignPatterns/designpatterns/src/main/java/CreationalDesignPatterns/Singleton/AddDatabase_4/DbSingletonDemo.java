package CreationalDesignPatterns.Singleton.AddDatabase_4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSingletonDemo {

    public static void main(String[] args) {
        // create our single (lazily and thread safe via double check locking)
        DbSingleton instance = DbSingleton.getInstance();

        long timeBefore = 0;
        long timeAfter = 0;
        System.out.println(instance);

        timeBefore = System.currentTimeMillis();
        Connection connection = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);
        System.out.println(connection);

        try (Statement statement = connection.createStatement()){
            int count = statement.executeUpdate("CREATE TABLE Address (ID INT, StreetName VARCHAR(20),"
                    + "City VARCHAR(20))");
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
            Running this again will show that it took 0 ms to return the second connection , so we are actually
            getting the same connection instance back.
         */
        timeBefore = System.currentTimeMillis();
        connection = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);
        System.out.println(connection);
    }

}
