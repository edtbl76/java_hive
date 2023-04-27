package javaeight.lambdaexpressions.capturing;

import utils.Generated;

import java.util.function.IntUnaryOperator;

@Generated
@SuppressWarnings("java:S106")
public class Demo {

    static int nonlocal = 0;

    public static void effectivelyFinal() {

        // local var initialized once and never changed = effectively final
        int i = 5;

        /*
            if I changed the value of i here, then this code won't
            compile because it will no longer be effectively final
        */
        // i = 7
        effectivelyFinalHelper(i);

    }

    public static void stillEffectivelyFinal() {

        // I've reset the value, but since the variable is non-local,
        // it will compile.
        nonlocal = 1;
        effectivelyFinalHelper(nonlocal);
    }


    private static void effectivelyFinalHelper(int i) {
        IntUnaryOperator operator = integer -> integer * i;
        System.out.println(operator.applyAsInt(i));
    }




    public static void main(String[] args) {
        effectivelyFinal();
        stillEffectivelyFinal();
    }
}
