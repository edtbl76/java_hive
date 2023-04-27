package multithreading.threads.executors.timervsexecutor;

import utils.Generated;

import java.util.Timer;
import java.util.TimerTask;

@Generated
@SuppressWarnings("all")
public class BadTimer {

    @Generated
    public static void main(String[] args) throws InterruptedException {

        Timer timer = new Timer();


        @SuppressWarnings("all")
        TimerTask badTask = new TimerTask() {
            @Generated
            @Override
            public void run() {
                while (true)
                    ;
            }
        };

        @SuppressWarnings("all")
        TimerTask goodTask = new TimerTask() {
            @Generated
            @Override
            public void run() {
                System.out.println("Working properly.");
            }
        };

        /*
            First Timer is going to get stuck due to infinite while loop.
            - Even if it weren't infinite, if it were a LONG task, the second task
            isn't going to get executed until the first is completed.
         */
        timer.schedule(badTask, 100);

        // Nothing else is going to execute.
        /*
            Even worse... the system remembers how many times
            this SHOULD have run, so when it does execute it will try to execute
            every missed turn w/o any delay.
         */
        timer.schedule(goodTask, 500);

        Thread.sleep(3000);
    }
}
