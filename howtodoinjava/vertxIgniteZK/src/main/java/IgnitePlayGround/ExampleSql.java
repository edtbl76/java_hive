package IgnitePlayGround;

import org.apache.ignite.Ignition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExampleSql {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        Ignition.start();

        Connection conn = DriverManager.getConnection("jdbc:ignite:thin://localhost/");

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("" +
                    "Create TABLE City (id LONG PRIMARY  KEY, name VARCHAR) WITH \"template=replicated\"");

            stmt.executeUpdate(
                    "CREATE TABLE Person(id LONG, name VARCHAR , city_id LONG, " +
                            "PRIMARY KEY (id, city_id)) WITH \"backups=1, affinityKey=city_id\"");

            stmt.executeUpdate("CREATE INDEX idx_city_name ON City (name )");
            stmt.executeUpdate("CREATE INDEX idx_person_name on Person (name)");

        }

    }

}
