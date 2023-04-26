package com.example.accessingdataneo4j;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
@Slf4j
public class AccessingDataNeo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataNeo4jApplication.class, args);
        System.exit(0);
    }

    @Bean
    CommandLineRunner demo(PersonRepository personRepository) {
        return args -> {

            personRepository.deleteAll();

            Person ed = new Person("Ed");
            Person sean = new Person("Sean");
            Person paul = new Person("Paul");

            List<Person> team = Arrays.asList(ed, sean, paul);

            log.info("Before linking up with Neo4j...");

            team.forEach(person -> log.info("\t" + person.toString()));

            personRepository.save(ed);
            personRepository.save(sean);
            personRepository.save(paul);

            ed = personRepository.findByName(ed.getName());
            ed.worksWith(sean);
            ed.worksWith(paul);
            personRepository.save(ed);

            sean = personRepository.findByName(sean.getName());
            sean.worksWith(paul);
            sean.worksWith(ed);
            personRepository.save(sean);

            paul = personRepository.findByName(paul.getName());
            paul.worksWith(sean);
            paul.worksWith(ed);
            personRepository.save(paul);

            log.info("Lookup each person by name...");
            team.forEach(person -> log.info("\t" + personRepository.findByName(person.getName()).toString()));

            List<Person> teammates = personRepository.findByTeammatesName(ed.getName());
            log.info("The following are teammates of Ed...");
            teammates.forEach(person -> log.info("\t" + person.getName()));
        };
    }

}
