package com.example.managingtransactions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    BookingService class
    - creates a JDBC-based service that books people into the system by name.

    JdbcTemplate
    - template that performs db interactions needed by the code we write.

 */
@Component
@Slf4j
@AllArgsConstructor
public class BookingService {

    private final JdbcTemplate jdbcTemplate;

    /*
        Loops through list of people
        - for each person, it inserts that person into the bookings table using the JdbcTemplate.

        @Transactional
        - this annotates the method as transactional. If there are ANY failure cases during the operation of
        the method, it rolls back to the state PRIOR to the transaction and throws the original exception.

            All or Nothing.
     */
    @Transactional
    public void book(String... persons) {
        String insertStatement = "INSERT INTO bookings(first_name) values (?)";
        for (String person : persons) {
            log.info("Booking " + person + " in a seat...");
            jdbcTemplate.update(insertStatement, person);
        }
    }

    /*
       Queries the database.
       - each row fetched from DB is converted into a String
       - all of the rows are added to a List and then returned.
     */
    public List<String> findAllBookings() {
        String queryStatement = "SELECT first_name FROM bookings";
        return jdbcTemplate.query(queryStatement,
                (resultSet, rowNumber) -> resultSet.getString("first_name"));
    }

}
