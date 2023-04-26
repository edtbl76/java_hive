package LivenessPerformanceTesting_3.AvoidingLivenessHazards_10.Examples;

import java.util.Random;

public class DemonstrateDeadlock {

    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1_000_000;

    /*
        Basic Driver Loop that will Induce a deadlock under typical conditions.

     */

    public static void main(String[] args) {
        final Random random = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAccount = random.nextInt(NUM_ACCOUNTS);
                    int toAccount = random.nextInt(NUM_ACCOUNTS);
                    Integer amount = random.nextInt(1000);
                    transferMoney(accounts[fromAccount], accounts[toAccount], amount);
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }

    private static final Object tieLock = new Object();

    private static void transferMoney(Account fromAccount, Account toAccount, Integer amount) {

    }

}
