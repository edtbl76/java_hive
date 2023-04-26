package com.concurrency.AdvancedConcurrency.ThreadFactoryExamples;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    private int             counter;
    private String          name;
    private List<String>    stats;

    public CustomThreadFactory(String name) {
        counter = 1;
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
       Thread t = new Thread(r, name + "-Thread_" + counter );
       counter++;
       stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), LocalDateTime.now()));
       return t;
    }

    public String getStats() {
        StringBuffer buffer = new StringBuffer();   // StringBuffer = threadsafe!

        /*
            ED NOTE:
            1.) The long way of doing this is to create an iterator from buffer and then use hasNext() and next() to
            populate the buffer.
                - BEEN THERE DONE THAT!
            2.) I could have used an enhanced for loop, but I prefer the forEach() method.
            3.) I could have used a lambda, but I also prefer to use method expressions.

            I'm a HUGE proponent of anonymous functions.
         */
        stats.forEach(buffer::append);
        return buffer.toString();
    }
}
