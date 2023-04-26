package AdvancedTopics.JUnitTestOrder.J5;

import org.junit.jupiter.api.MethodDescriptor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;

public class CustomOrder implements MethodOrderer {
    @Override
    public void orderMethods(MethodOrdererContext methodOrdererContext) {
        methodOrdererContext.getMethodDescriptors().sort(
                (MethodDescriptor md1, MethodDescriptor md2) ->
                        md1.getMethod().getName().compareToIgnoreCase(md2.getMethod().getName()));
    }
}
