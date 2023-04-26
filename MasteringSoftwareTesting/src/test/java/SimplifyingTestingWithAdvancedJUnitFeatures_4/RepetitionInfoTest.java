package SimplifyingTestingWithAdvancedJUnitFeatures_4;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class RepetitionInfoTest {

    @RepeatedTest(2)
    void test(RepetitionInfo repetitionInfo) {
        System.out.println("** Test " +
                repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getCurrentRepetition());
    }
}
