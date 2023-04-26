package com.example.asyncmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/*
    This service uses RestTemplate to invoke a remote REST point (the url variable)
    - converts the response into a User.
    - Spring provides the RestTemplateBuilder to customize defaults w/ auto-configuration bits
    - (In this case MessageConverter)

    Service annotation marks the class so that it will be detected by the ComponentScan (in
    SprintBootApplication) so that it will be added to the application context.


 */
@Service
@Slf4j
public class GitHubLookupService {

    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /*
        This tells Spring that the method should run in a separate thread
        - a requirement for async services is to provide a return type that wraps the domain object in
        a CompletableFuture.
     */
    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        log.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);

        // Delay for demo
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}
