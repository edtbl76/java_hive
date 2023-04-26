package Wildcard;

import java.util.Arrays;
import java.util.List;

public class UpperBoundWildcardParameterizedType {
    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(1,2,3,4,5);
        System.out.println(sum(ints));

        List<Double> doubles = Arrays.asList(1.5d, 2d, 3d);
        System.out.println(sum(doubles));

    }

    private static Number sum(List<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }
}
