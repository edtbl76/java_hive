package com.example.asyncmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    private final GitHubLookupService gitHubLookupService;

    public ApplicationRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start clock
        long start = System.currentTimeMillis();

        // Kick off multiple, async lookups
        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");

        /*
            allOf()
                - factory method to create an array of CompletableFuture objects

            join()
                - waits for the completion of all of the CompletableFuture objects (that are established in the
                calling factory/collection)
         */
        CompletableFuture.allOf(page1, page2, page3).join();

        // Print results (including the time to complete)
        /*
            We capture elapsed time because as we perform work, we'll note that different pages might be
            performed on separate threads (up to 2 based on our configuration).

            - the elapsed time will increase considerably if we remove the Async annotation from our method, because
            each page will need to be performed sequentially.

            Remember...

                the longer a task takes and the more tasks are invoked simultaneously, the more benefit we'll see
                from making things async.

                (Trade off is handling CompletableFuture interface in your code, which is a layer of indirection ,
                because you are no longer dealing directly w/ the results)
         */
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }
}
