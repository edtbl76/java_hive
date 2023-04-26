package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Cucumber;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class Calculator {
    private final Deque<Number> stack = new LinkedList<>();
    private static final List<String> OPERATIONS = asList("-", "+", "*", "/");

    public void push(Object argument) {
        if (OPERATIONS.contains(argument)) {
            Number y = stack.removeLast();
            Number x = stack.isEmpty() ?  0 : stack.removeLast();
            Double value = null;
            if (argument.equals("-")) {
                value = x.doubleValue() - y.doubleValue();
            } else if (argument.equals("+")) {
                value = x.doubleValue() + y.doubleValue();
            } else if (argument.equals("*")) {
                value = x.doubleValue() * y.doubleValue();
            } else if (argument.equals("/")) {
                value = x.doubleValue() / y.doubleValue();
            }
            push(value);
        } else {
            stack.add((Number) argument);
        }
    }

    public Number value() {
        return stack.getLast();
    }
}
