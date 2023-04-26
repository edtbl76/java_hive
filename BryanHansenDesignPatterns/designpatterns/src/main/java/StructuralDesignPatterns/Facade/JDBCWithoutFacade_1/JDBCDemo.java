package StructuralDesignPatterns.Facade.JDBCWithoutFacade_1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

    public static void main(String[] args) {

        String createTable = "CREATE TABLE Address (ID INTEGER, StreetName VARCHAR(20), City VARCHAR(20))";
        String insertValues = "INSERT INTO Address (ID, StreetName, City) values (1, '1234 Some street', 'Layton')";
        String query = "SELECT * FROM Address";

        DbSingleton instance = DbSingleton.getInstance();
        Statement statement;

        try {
            Connection connection = instance.getConnection();

            statement = connection.createStatement();
            int count = statement.executeUpdate(createTable);
            System.out.println("Table created");
            statement.close();

            statement = connection.createStatement();
            count = statement.executeUpdate(insertValues);
            System.out.println(count + " record(s) created");
            statement.close();

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println(
                        resultSet.getString(1) + " "
                                + resultSet.getString(2) + " "
                                + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
