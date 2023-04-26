package MockitoExamples.BasicStuff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentMatchers;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class VerifyingCallsAgainstMockedObjects {

    @Test
    public void testVerification() {

        // Set shit up
        ExampleClass test = mock(ExampleClass.class);
        when(test.getUniqueId()).thenReturn(43);

        // call some methods
        test.testing(12);
        test.getUniqueId();
        test.getUniqueId();

        // validate
        verify(test).testing(ArgumentMatchers.eq(12));
        verify(test, times(2)).getUniqueId();

        // Others
        verify(test, never()).someMethod("never called");
        verify(test, atLeastOnce()).someMethod("at least once");
        verify(test, atLeast(2)).someMethod("at least twice");
        verify(test, times(5)).someMethod("exactly 5 times");
        verify(test, atMost(3)).someMethod("no more than 3 times");
        verifyNoMoreInteractions(test);



    }
}
