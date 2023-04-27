package recursion;

import utils.Generated;

// Hide from Jacoco
@Generated
public class RecursionIncreasingNumbers {

    @SuppressWarnings("java:S106")
    private static void printNumber(int number) {
        // Base
        if (number == 0) {
            return;
        }
        // Recursive
        printNumber(number - 1);
        System.out.println(number);
    }

    public static void main(String[] args) {
        printNumber(10);
    }
}
/*
    This is deceptive because of the way it is called.
    call printNumber(10)
     call printNumber(9)
      call printNumber(8)
       call printNumber(7)
       ... etc...
       print 7
      print 8
     print 9
    print 10
 */
