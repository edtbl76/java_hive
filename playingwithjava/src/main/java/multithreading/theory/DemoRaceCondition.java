package multithreading.theory;

import utils.Generated;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

@Generated
public class DemoRaceCondition {

    int randomInt;
    Random random = new Random(currentTimeMillis());

    void printer() {
        int number = 1_000_000;
        while (number != 0) {
            if (randomInt % 5 == 0) {
                if (randomInt % 5 != 0) {
                    System.out.println(randomInt);
                }
            }
            number--;
        }
    }

    void modifier() {

        int number = 1_000_000;
        while (number != 0) {
            randomInt = random.nextInt(1000);
            number--;
        }
    }

    public static void runTest() throws InterruptedException {
        final DemoRaceCondition raceCondition = new DemoRaceCondition();

        Thread printThread = new Thread(raceCondition::printer);
        Thread modifierThread = new Thread(raceCondition::modifier);

        printThread.start();
        modifierThread.start();

        printThread.join();
        modifierThread.join();;
    }

    public static void main(String[] args) throws InterruptedException{
        DemoRaceCondition.runTest();
    }
}
