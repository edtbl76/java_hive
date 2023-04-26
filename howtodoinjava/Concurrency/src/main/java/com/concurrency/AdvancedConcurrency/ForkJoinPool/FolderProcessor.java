package com.concurrency.AdvancedConcurrency.ForkJoinPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class FolderProcessor  extends RecursiveTask<List<String>> {

    // stores path of folder being processed
    private final String path;

    // stores the extension of the files being filtered.
    private final String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    /*
        Compute that returns the type we set for the RecursiveTask<>
     */
    @Override
    protected List<String> compute() {

        // Stores names of files in a folder, and subtasks for forked processes respectively
        List<String> list = new ArrayList<>();
        List<FolderProcessor> tasks = new ArrayList<>();

        // set file object for the path, and then create array  of the sub-objects
        File file = new File(path);
        File content[] = file.listFiles();

        // if we get a result proceed.
        if(content != null) {
            // forEach iter through the array of contents.
            IntStream.range(0, content.length).forEach(i -> {

                // If its a directory, build a new task, fork it and then add it to our list of tasks.
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);
                // other wise pass the filename to another function that returns true if the extension is present.
                } else {
                    if (checkFile(content[i].getName())) {
                        // if we have a winner, add that bad boy to our list of hits.
                        list.add(content[i].getAbsolutePath());
                    }
                }
            });
        }
        // if the task list exceeds 50, print out how many tasks we added from the original folder.
        if (tasks.size() > 50)
            System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());

        addResultsFromTasks(list, tasks);
        return list;
    }

    // For eeach task stored, call join(), that waits for its finalization and returns the result fo the task.
    // we add that result ot the list using addAll().
    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor task : tasks) {
            list.addAll(task.join());
        }
    }

    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
