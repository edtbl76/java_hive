package GuideToJunit5.annotations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BeforeTest {

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
        Must be static in order to compile.
     */
    @BeforeAll
    public static void setup() {
        System.out.println("@BeforAll runs once, before all other tests are executed from this class.");
    }

    @BeforeEach
    public void init() {
        System.out.println("@BeforeEach runs once before each test method inside this class.");
    }
}
