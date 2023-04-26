public class TernaryOperator {

    public static void main(String[] args) {
        int a, b;

        a = 10;

        // This is a Ternary Operator.

        // b is equal to 20 if 'a == 1' evaluates to true.
        // b is equal to 30 if 'a == 1' evaluates to false
        b = ( a == 1 ) ? 20: 30;
        System.out.println( "Value of b is : " + b );

        // b is equal to 20 if 'a == 10' evaluates to true.
        // b is equal to 30 if 'a == 10' evaluates to false
        b = ( a == 10 ) ? 20: 30;
        System.out.println( "Value of b is : " + b );



    }
}
