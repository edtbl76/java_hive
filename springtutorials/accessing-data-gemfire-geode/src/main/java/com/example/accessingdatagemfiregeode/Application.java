package com.example.accessingdatagemfiregeode;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;

/*
    EnableGemfireRepositories
        - (by default) scans current package for interfaces that extend one of Spring Data's repository interfaces
        (i.e. CrudRepository)

        - basePackageClasses is used to safely instruct Spring Data for Apache Geode to scan a different root package
        by type for app-specific Repository extensions.


    Cache
        - Apache Geode requires a cache for 1+ regions to store all of the data.
            - 3 Types of Annotations
                - ClientCacheApplication
                    - most common (and what we use)
                    -

                - PeerCacheApplication
                    - p2p, peer cache instance is embedded in the app
                    - the app would require the ability to participate in a cluster as a peer cache member
                    - *** this means the app is limited by all of the constraints of being a peer of the cluster ***

                - CacheServerApplication
 */
@SpringBootApplication
@ClientCacheApplication(name = "AccessingDataGemFireApplication")
@EnableEntityDefinedRegions(
        basePackageClasses = Person.class,
        clientRegionShortcut = ClientRegionShortcut.LOCAL
)
@EnableGemfireRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner run(PersonRepository personRepository) {

        return args -> {

            Person ed = new Person("Evil Ed", 45);
            Person vanessa = new Person("Va-va Vanessa", 40);
            Person mike = new Person("Monkey Mike", 21);
            Person connor = new Person("Cuckoo Connor", 10);

            System.out.println("Before accessing data in Apache Geode...");

            List<Person> persons = asList(ed, vanessa, mike, connor);

            persons.forEach(person -> System.out.println("\t" + person));

            System.out.println("Saving everyone to GemFire...");
            personRepository.saveAll(persons);

            System.out.println("Lookup everyone by name...");
            persons.forEach(name -> System.out.println("\t" + personRepository.findByName(name)));

            System.out.println("Find adults...");
            stream(personRepository.findByAgeGreaterThan(18).spliterator(), false)
                    .forEach(adult -> System.out.println("\t" + adult));

            System.out.println("Find Kids...");
            stream(personRepository.findByAgeLessThan(18).spliterator(), false)
                    .forEach(kid -> System.out.println("\t" + kid));

            System.out.println("Find the Middle...");
            stream(personRepository.findByAgeGreaterThanAndAgeLessThan(44, 11).spliterator(), false)
                    .forEach(middie -> System.out.println("\t" + middie));
        };

    }
}
