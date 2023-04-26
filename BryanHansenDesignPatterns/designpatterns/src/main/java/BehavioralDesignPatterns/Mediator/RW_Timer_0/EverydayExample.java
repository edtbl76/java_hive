package BehavioralDesignPatterns.Mediator.RW_Timer_0;

import java.util.Timer;
import java.util.TimerTask;

public class EverydayExample {

    private Timer timer;

    public EverydayExample(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
        timer.schedule(new RemindTaskWithoutBeep(), seconds * 2 * 1000);
    }

    public static void main(String[] args) {
        System.out.println("Scheduling task");
        new EverydayExample(3);
        System.out.println("Task scheduled");
    }

    static class RemindTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("Time's up");
        }
    }

    class RemindTaskWithoutBeep extends TimerTask {

        @Override
        public void run() {
            System.out.println("Now is REALLY up!");
            timer.cancel();
        }
    }
}

