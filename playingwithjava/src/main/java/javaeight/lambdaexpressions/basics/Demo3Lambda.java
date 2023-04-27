package javaeight.lambdaexpressions.basics;

import utils.Generated;

@Generated
@SuppressWarnings("java:S106")
public class Demo3Lambda {

    public static void wish(Greeting greeting) {
        greeting.greet();
    }
    public static void main(String[] args) {
        wish(() -> System.out.println("Sup!"));
    }
}
