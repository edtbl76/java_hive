package com.example.batchprocessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.BatchStatus.COMPLETED;

@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String query = "SELECT first_name, last_name FROM people";

        if (jobExecution.getStatus() == COMPLETED) {
            log.info("::: Job Completed. Time to verify Results");

            jdbcTemplate.query(query, (resultSet, row) -> new Person(
                    resultSet.getString(1),
                    resultSet.getString(2))
            ).forEach(person -> log.info("Found <" + person + "> in the database"));
        }
    }
}
