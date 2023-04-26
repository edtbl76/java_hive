package StructuralDesignPatterns.Facade.JDBCWithFacade_2;

public class JDBCDemo {

    public static void main(String[] args) {

        String createTable = "Address (ID INTEGER, StreetName VARCHAR(20), City VARCHAR(20))";
        String insertValues = "Address (ID, StreetName, City) values (1, '1234 Some street', 'Layton')";
        String query = "SELECT * FROM Address";

        JdbcFacade jdbcFacade = new JdbcFacade();

        jdbcFacade.createTable(createTable);
        System.out.println("Table created");

        jdbcFacade.insertIntoTable(insertValues);
        System.out.println("Record inserted");

        jdbcFacade.getAddresses(query).forEach(
                address -> System.out.println(address.getId() + " " +  address.getStreet() + " " + address.getCity()));


    }
}
