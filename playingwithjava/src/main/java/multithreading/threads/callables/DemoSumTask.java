package multithreading.threads.callables;

import utils.Generated;

import java.util.concurrent.Callable;

/*
    Callables are like Runnables, but Runnables are void, so
    they just execute a command.

    Callables allows us to do two more interesting things
    1.) We can return a value
    2.) We can throw an exception

 */
@SuppressWarnings("all")
@Generated
public class DemoSumTask implements Callable<Integer> {

    int number;

    public DemoSumTask(int number) {
        this.number = number;
    }
    @Override
    public Integer call() {

        if (number <= 0) return 0;

        int sum = 0;
        for (int i = 0; i <= number; i++) {
            sum += i;
        }
        return sum;
    }
}
