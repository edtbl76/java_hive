package javaeight.lambdaexpressions.basics;

import utils.Generated;

@Generated
@SuppressWarnings("java:S106")
public class Demo2AnonymousClass {

    public static void wish(Greeting greeting) {
        greeting.greet();
    }
    @SuppressWarnings({"Convert2Lambda", "java:S1604"})
    public static void main(String[] args) {
        wish(new Greeting() {
            @Override
            public void greet() {
                System.out.println("Sup!");
            }
        });
    }
}
