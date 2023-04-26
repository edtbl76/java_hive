package GuideToJunit5.annotations;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class AfterTest {

    @Test
    public void test1() {
        System.out.println("I'm a test!");
    }

    @Test
    public void test2() {
        System.out.println("I'm another test");
    }

    /*
        Junit 5 only

     */

    /*
        Has to be static to compile
     */
    @AfterAll
    public static void complete() {
        System.out.println("@AfterAll runs once, after ll other tests are executed from this class.");
    }

    @AfterEach
    public void teardown() {
        System.out.println("@AfterEach runs once After each test method inside this class.");
    }
}
