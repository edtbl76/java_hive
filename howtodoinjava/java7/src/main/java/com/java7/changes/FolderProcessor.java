package com.java7.changes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {

    private static final long serailVersionUID = 1L;

    // attr to store full path of folder the task will process
    private final String path;

    // attr ot store name of extension of files to be searched for
    private final String extension;

    // Implement constructor to init attrs
    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    // Implement compute() method. (has to return the type of the object defined for RecursiveTask)
    protected List<String> compute() {

        /*
            Two kinds of elements to be stored when we process the content of a folder.
            - 'list' is a list of files in the immediate folder
            - 'tasks' is a list of subfolders that have to be forked out into a new process
         */
        List<String> list = new ArrayList<>();
        List<FolderProcessor> tasks = new ArrayList<>();

        /*
            Create a new directory object with the path provided to the constructor
            Then create a list of paths based on all of the content. (This is our working directory).
         */
        File file = new File(path);
        File content[] = file.listFiles();

        // If the dir is empty, we are done
        if (content != null) {
            // otherwise, iterate through the files
            for(int i = 0; i < content.length; i++) {
                // If it is a new directory, we are recursively calling the class to process this as a "subtask"
                // we fork that task so it can work asynchronously, and then we add it to the list of substasks to be processed
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);

                // If it's not a dir, then we think its a file, so call checkFile (which matches it against the extension parameter
                // and then we add the matches to out results store.
                } else {
                    if (checkFile(content[i].getName())) {
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }
        }

        // If FP subtasks is > 50, write msg
        if (tasks.size() > 50)
            System.out.printf("%s: %d tasks run.\n", file.getAbsoluteFile(), tasks.size());

        // add to list of files, the results returned by subtasks launched by this task.
        addResultsFromTasks(list, tasks);
        return list;
    }

    // For each task stored in list of tasks, call join().
    //  - waits for its finalization then returns result of task.
    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor item : tasks)
            list.addAll(item.join());
    }

    // compares if file of interest ends w/ the file extension we're looking for
    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }

}
