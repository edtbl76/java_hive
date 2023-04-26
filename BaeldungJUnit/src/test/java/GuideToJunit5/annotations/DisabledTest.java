package GuideToJunit5.annotations;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledTest {

    @Test
    public void test1() {
        System.out.println("I'm implemented!");
    }

    @Disabled("Not Implemented")
    @Test
    public void test2() {
        System.out.println("I'm not!");
    }

    @Disabled
    @Test
    public void test3() {
        System.out.println("I prefer the default message!");
    }
}
