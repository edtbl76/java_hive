package BeforeAfter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BeforeClassAndAfterClassAnnotationsUT {

    /*

      BeforeClass/AfterClass works like BeforeAll/AfterAll
      - They both need to be static as well.

     */
    private List<String> list;

    @BeforeClass
    public static void setup() {
        System.out.println("Starting Up");
    }

    @AfterClass
    public static void teardown() {
        System.out.println("Tearing Down");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

}
