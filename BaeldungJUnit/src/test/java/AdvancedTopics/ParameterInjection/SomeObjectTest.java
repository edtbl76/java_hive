package AdvancedTopics.ParameterInjection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyParameterResolver.class)
public class SomeObjectTest {

    @Test
    public void testSomething(SomeObject someObject) {
        assertTrue(someObject.getClass() == SomeObject.class);
    }
}
