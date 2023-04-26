package StructuralDesignPatterns.Facade.JDBCWithFacade_2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcFacade {

    // Composition
    DbSingleton instance = null;

    public JdbcFacade() {
        instance = DbSingleton.getInstance();
    }

    public int createTable(String str) {
        int count = 0;

        Connection connection = instance.getConnection();
        try (Statement statement = connection.createStatement())
        {
            count = statement.executeUpdate("CREATE TABLE " + str);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int insertIntoTable(String str) {
        int count = 0;
        Connection connection = instance.getConnection();
        try (Statement statement = connection.createStatement()) {
            count = statement.executeUpdate("INSERT INTO " + str);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Address> getAddresses(String query) {
        int count = 0;
        List<Address> addresses = new ArrayList<>();

        Connection connection = instance.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {
            while(resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getString(1));
                address.setStreet(resultSet.getString(2));
                address.setCity(resultSet.getString(3));

                addresses.add(address);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
}
