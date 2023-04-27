package multithreading.threads.callables;

import utils.Generated;

import java.util.concurrent.Callable;

@Generated
@SuppressWarnings("all")
public class DemoSumTask2 {

    final int number = 10;

    /*
        Instead of creating an entire class, we can summarize our task into
        an anonymous class.
     */

    @SuppressWarnings("all")
    Callable<Integer> sumTask = new Callable<Integer>() {

        @Generated
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i <= number; i++) {
                sum += i;
            }
            return sum;
        }
    };

}
