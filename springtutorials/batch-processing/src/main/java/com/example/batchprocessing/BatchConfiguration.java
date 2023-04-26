package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;


/*
    Configuration annotation makes life easier
    Enable Batch Processing adds
 */

// tag::setup[]
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }
    // end::setup[]

    // tag::readerwriterprocessor[]
    /*
        The reader() is an impl of ItemReader
        - we set it up to read the CSV file we created w/ the Business Data.
        - each line in the CSV will be read in and converted to a Person domain object.
     */
    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Person.class);
                }})
                .build();
    }

    /*
        Creates an instance of our PersonItemProcessor class to perform the transformation of
        Person
     */
    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }


    /*
        Creates an impl of ItemWriter()
        - this is aimed at a Jdbc destination
        - the DataSource is injected via EnableBatchProcessing
        - includes SQL statement required to insert a single person into the db.
     */
    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {

        String insertStatement = "INSERT INTO PEOPLE (first_name, last_name) VALUES (:firstName, :lastName)";

        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(insertStatement)
                .dataSource(dataSource)
                .build();

    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    /*
        Actual Job config.
        - Import User Job defines the actual job
            - provides an incrementer because jobs use a DB to maintain exec state.
            - we list each step, the job ends and the Java API provides a configured (perfectly?) job.

        - Step defines the step (the building block of the job)
            - can involve a reader, processor and/or writer (based on the Beans we've defined and injected above)
            - define how much data to write at a time.
     */
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Person> writer) {
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
    // end::jobstep[]
}
