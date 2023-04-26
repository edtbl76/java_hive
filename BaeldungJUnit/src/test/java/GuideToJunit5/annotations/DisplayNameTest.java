package GuideToJunit5.annotations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DisplayNameTest {

    /*
        JUNIT 5 only.
     */
    @DisplayName("Here I Am !")  // <-- This is what displays instead of the method name. Look to your left in the run window
    @Test
    public void test1() {
        System.out.println("I said look to your left");
    }

    @Test
    public void test2() {
        System.out.println("I still show the method name.... ");
    }

}

