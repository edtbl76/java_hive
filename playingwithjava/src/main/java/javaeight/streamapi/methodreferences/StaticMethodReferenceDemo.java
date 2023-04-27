package javaeight.streamapi.methodreferences;

import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
@SuppressWarnings({"java:S106", "java:S1612", "Convert2MethodRef"})
public class StaticMethodReferenceDemo {

    public static int getLength(String str) {
        return str.length();
    }

    public static void main(String[] args) {
        List<String>  list = new ArrayList<>(List.of("red", "orange", "yellow", "green", "blue", "indigo", "violet"));

        // Code w/o method reference
        list.stream()
                .mapToInt(value -> StaticMethodReferenceDemo.getLength(value))
                .forEach(System.out::println);
        System.out.println();

        // Code w/ method reference
        list.stream()
                .mapToInt(StaticMethodReferenceDemo::getLength)
                .forEach(System.out::println);
        System.out.println();

    }
}
