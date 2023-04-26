package AdvancedTopics_4.TheJavaMemoryModel_16.Examples;


/*
    A program with insufficient programs can have surprising results

    DONT DO THIS
 */
public class PossibleReordering {
    static int x = 0;
    static int y = 0;

    static int a = 0;
    static int b = 0;

    public static void main(String[] args) throws InterruptedException{
        Thread threadOne = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread threadOther = new Thread(() -> {
            b = 1;
            y = a;
        });

        threadOne.start();
        threadOther.start();

        threadOne.join();
        threadOther.join();

        System.out.println("( " + x + "," + y + " )");
    }
}
