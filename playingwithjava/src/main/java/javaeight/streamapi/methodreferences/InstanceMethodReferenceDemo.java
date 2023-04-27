package javaeight.streamapi.methodreferences;

import utils.Generated;

import java.util.ArrayList;
import java.util.List;


@Generated
@SuppressWarnings("java:S106")
public class InstanceMethodReferenceDemo {

    public String addSurname(String str) {
        return str + " Mangini";
    }
    @SuppressWarnings({"Convert2MethodRef", "java:S1612"})
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Edward", "Vanessa", "Michael", "Connor", "Gravy"));
        InstanceMethodReferenceDemo demo = new InstanceMethodReferenceDemo();

        // Code without instance method reference
        names
                .stream()
                .map(s -> demo.addSurname(s))
                .forEach(System.out::println);
        System.out.println();

        // Code w/ instance method reference
        names
                .stream()
                .map(demo::addSurname)
                .forEach(System.out::println);
    }
}
