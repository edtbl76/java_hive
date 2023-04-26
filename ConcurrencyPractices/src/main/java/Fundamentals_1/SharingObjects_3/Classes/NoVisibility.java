package Fundamentals_1.SharingObjects_3.Classes;

public class NoVisibility {

    /*
        2 shared variables
            - ready
            - number
     */
    private static boolean ready;
    private static int number;

    /*
        We have TWO threads that access the shared variables
        - ReaderThread
        - main
     */
    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }


    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}

/*
    Sequence:
    - MainThread starts
    - MainThread executes readerThread
        - ReaderThread starts going through while loop
    - MainThread sets number to 42
    - MainThread sets ready to true.
        - ReaderThread is set to stop when it sees ready is true.

 */
