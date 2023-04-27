package multithreading.threads.executors.timervsexecutor;

import utils.Generated;

import java.util.Timer;
import java.util.TimerTask;

@Generated
@SuppressWarnings("all")
public class AnotherBadTimer {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();

        @SuppressWarnings("all")
        TimerTask badTask = new TimerTask() {
            @Generated
            @Override
            public void run() {
                throw new RuntimeException("Awww Sheeyut!");
            }
        };

        @SuppressWarnings("all")
        TimerTask goodTask = new TimerTask() {
            @Generated
            @Override
            public void run() {
                System.out.println("I'm a good task! Gimme a treat!");
            }
        };

        /*
            This bad task will result in an exception that kills the only
            running timer worker thread... so again.. none of the remaining code
            gets executed.
         */
        timer.schedule(badTask, 10);
        Thread.sleep(500);
        timer.schedule(goodTask, 10);
    }

}
