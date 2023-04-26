package AdvancedTopics.CustomTestRunnersJ4;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.Method;

public class TestRunner extends Runner {
    private Class clazz;
    public TestRunner(Class clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public Description getDescription() {
        return Description.createTestDescription(clazz, "My runner description");
    }

    @Override
    public void run(RunNotifier runNotifier) {
        System.out.println("running the tests from MyRunner: " + clazz);
        try {
            Object object = clazz.getDeclaredConstructor().newInstance();
            for (Method method : clazz.getMethods()) {
               if (method.isAnnotationPresent(Test.class)) {
                   runNotifier.fireTestStarted(Description.createTestDescription(clazz, method.getName()));
                   method.invoke(object);
                   runNotifier.fireTestFinished(Description.createTestDescription(clazz, method.getName()));
               }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
