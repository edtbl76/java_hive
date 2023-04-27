package javaeight.lambdaexpressions.basics;

import utils.Generated;

import java.util.List;

@Generated
@SuppressWarnings("java:S106")
public class Demo1OOP {

    public static void wish(Greeting greeting) {
        greeting.greet();
    }
    public static void main(String[] args) {
        Greeting english = new EnglishGreeting();
        Greeting german = new GermanGreeting();
        Greeting danish = new DanishGreeting();

        List<Greeting> greetings = List.of(english, german, danish);

        greetings.forEach(Demo1OOP::wish);
    }
}
