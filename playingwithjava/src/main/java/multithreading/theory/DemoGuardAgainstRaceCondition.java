package multithreading.theory;

import utils.Generated;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

@Generated
public class DemoGuardAgainstRaceCondition {

    int randomInt;
    Random random = new Random(currentTimeMillis());

    void printer() {
        int number = 1_000_000;
        while (number != 0) {
            synchronized (this) {
                if (randomInt % 5 == 0) {
                    if (randomInt % 5 != 0) {
                        System.out.println(randomInt);
                    }
                }
            }
            number--;
        }
    }

    void modifier() {
        int number = 1_000_000;
        while (number != 0) {
            synchronized (this) {
                randomInt = random.nextInt(1000);
                number--;
            }
        }
    }

    public static void runTest() throws InterruptedException {

        final DemoGuardAgainstRaceCondition raceCondition = new DemoGuardAgainstRaceCondition();

        Thread printerThread = new Thread(raceCondition::printer);
        Thread modifyThread = new Thread(raceCondition::modifier);

        printerThread.start();
        modifyThread.start();

        printerThread.join();
        modifyThread.join();
    }

    public static void main(String[] args) throws InterruptedException {
        DemoRaceCondition.runTest();
    }
}
