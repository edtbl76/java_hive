package com.java8.MethodReferences;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MethodReference_ClassInstance_instanceMethodName {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1,3,5,7,9);
        Optional<Integer> max = integers.stream().reduce(Math::max);        // Class::staticMethodName
        max.ifPresent(System.out::println);                                 // ClassInstance::instanceMethodName

    }
}
