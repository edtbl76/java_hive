package com.example.batchprocessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.Locale;

import static java.util.Locale.ROOT;


/*
    Batch Processor
    - Ingest Data
    - Transform It
    - Pipe It Somewhere

    This class does the middle step

    This processor implements Spring's ItemProcessor (interface of SpringBatch)
    - this simplifies plumbing the code into batch jobs.
    - The types it takes are an input type (person in this case) and an output type (the transformed person in ths
    case)


 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(final Person person) {
        final String firstName = person.getFirstName().toUpperCase(ROOT);
        final String lastName = person.getLastName().toUpperCase(ROOT);

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
