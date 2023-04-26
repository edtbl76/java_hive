package com.example.relationaldataaccess;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class RelationalDataAccessApplication implements CommandLineRunner {

    final JdbcTemplate jdbcTemplate;

    public RelationalDataAccessApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RelationalDataAccessApplication.class, args);
    }




    @Override
    public void run(String... args) {


        log.info("Creating Tables: ");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split full names into components
        List<Object[]> splitNames = Stream.of("Ed Mangini", "Tom Adams", "Kumar Vora", "Jeremy Jusak")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // print tuple using Streams
        splitNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));


        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitNames);
        log.info("Querying for customer records where first_name = 'Tom':");

        String statement = "SELECT id, first_name, last_name FROM customers WHERE first_name = ?";
        RowMapper<Customer> customerRowMapper =  (resultSet, rowNumber) -> new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );

        jdbcTemplate.query(statement, customerRowMapper, new Object[] { "Tom" })
                .forEach(customer -> log.info(customer.toString()));
    }
}
