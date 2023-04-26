import javafx.util.Pair;

public class ReturningValues {

    public static void main(String[] args) {

        // Example 1:
        int value1Ex1 = 20;
        int value1Ex2 = 10;
        int[] example1 = ReturnByArray.returnArray(value1Ex1, value1Ex2);
        System.out.println(value1Ex1 + " + " + value1Ex2 + " = " + example1[0]);
        System.out.println(value1Ex1 + " - " + value1Ex2 + " = " + example1[1]);

        // Example 2:
        Pair<Integer, String> pair = ReturnByPair.returnPair();
        System.out.println("Pair: { key: '" + pair.getKey() + "', value: '" + pair.getValue() + "'}");

        // Example 3:
        KeepItSimple doSomeMath = getMyObject(10, 20);
        System.out.println(doSomeMath);

    }

    static KeepItSimple getMyObject(int a, int b) {
        return new KeepItSimple(a * b, (double) b / a, a + b);
    }
}

/*
    1.) Return By An Array
    - this works if all of the return values are of the same type

 */
class ReturnByArray {

    static int[] returnArray(int a, int b) {
        return new int[]{(a + b), (a - b)};
    }
}


/*
    2.) Pair (javafx.util.Pair)
    - javafx.util.Pair is a good way to return two variables that are of different types.
 */
class ReturnByPair {

    static Pair<Integer, String> returnPair(){
        return new Pair<>(10, "Ten");
    }


}


/*
    3.) Object
    - if you have more than 3 data points of different types, then just stuff them in an Object and return
    the object
 */
class KeepItSimple {
    int product;
    double quotient;
    int sum;

    KeepItSimple(int product, double quotient, int sum) {
        this.product = product;
        this.quotient = quotient;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "KeepItSimple{" +
                "product=" + product +
                ", quotient=" + quotient +
                ", sum=" + sum +
                '}';
    }
}