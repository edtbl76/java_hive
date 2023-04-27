package recursion;

import utils.Generated;

// Exclude from Code Coverage
@Generated
@SuppressWarnings("java:S106")
public class IndirectRecursion {

    /*
        Indirect/Mutual Recursion occurs one method A calls Method B, and Method B which calls Method N,
        Method N calls the original Method.
     */

    static int n = 0;
    private static  void methodA() {

        // This is one way to get a class name.
        // Sonar gets pissed off because it's a waste of
        //  memory and cycles to create a class just to call getClass.
        @Generated
        @SuppressWarnings("java:S1116")
        class Inner {}

        if (n <= 10) {
            System.out.println(n + " " + Inner.class.getEnclosingMethod().getName());
            n++;
            methodB();
        }
    }


    private static void methodB() {

        // This is another way to get the name... from the current thread.
        String currentMethod = Thread.currentThread()
                .getStackTrace()[1]
                .getMethodName();
        if (n <= 10) {
            System.out.println(n + " " + currentMethod);
            n++;
            methodA();
        }
    }

    public static void main(String[] args) {
        methodA();
    }
}
