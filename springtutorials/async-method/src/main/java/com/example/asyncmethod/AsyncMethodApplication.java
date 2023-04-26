package com.example.asyncmethod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/*
    EnableAsync
        - tells Spring to run methods annotation w/ @Async in a background thread pool.


 */
@SpringBootApplication
@EnableAsync
public class AsyncMethodApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncMethodApplication.class, args);
    }

    /*
        We customize the Executor for the thread pool w/ this Bean

        NOTE: Spring specifically searches for the method name 'taskExecutor' <-- USE THIS
        - we change some of the tunings to fit the nature of the demo.

        NOTE: If we don't define an Executor in Async apps, Spring creates a 'SimpleAsyncTaskExecutor'.

     */
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }
}
