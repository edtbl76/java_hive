package com.java7.changes;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkJoinFrameworkExample  {


    public static void main(String[] args) {

        // create FJP
        ForkJoinPool pool = new ForkJoinPool();

        // create 3 new Folder Processor tasks.
        FolderProcessor here = new FolderProcessor("./", "xml");
        FolderProcessor java7 = new FolderProcessor("./src/main/java/com/java7/changes", "md");
        FolderProcessor idea = new FolderProcessor("./idea", "xml");

        // execute tasks in pool
        pool.execute(here);
        pool.execute(java7);
        pool.execute(idea);

        // Print out results
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        } while ((!here.isDone()) || (!java7.isDone()) || (!idea.isDone()));

        // kill pool
        pool.shutdown();

        // Write # of results gen'd by each task
        List<String> results;
        results = here.join();
        System.out.printf("Here: %d files found.\n", results.size());

        results = java7.join();
        System.out.printf("Java7: %d files found.\n", results.size());

        results = idea.join();
        System.out.printf("Idea: %d files found.\n", results.size());

    }
}
