package BeforeAfter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BeforeAndAfterAnnotationsUT {

    /*

        In JUNIT4 Before/After ran before and after every test
        - That allows us to change state inside each individual test, knowing we'll clean it up
        - Works like BeforeEach/AfterEach in JUNIT 5

     */
    private List<String> list;

    @Before
    public void init() {
        System.out.println("Starting Up");
        list = new ArrayList<>(Arrays.asList("test1", "test2"));
    }

    @After
    public void cleanup() {
        System.out.println("Cleaning Up");
        list.clear();
    }

    @Test
    public void whenCheckingListSize_equalsInit() {
        System.out.println("executing test1");
        assertEquals(2, list.size());
        list.add("more tests!");
    }

    @Test
    public void whenCheckingAgain_equalsInit() {
        System.out.println("executing test2");
        assertEquals(2, list.size());
    }
}
