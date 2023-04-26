package MockitoExamples.BasicStuff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class UsingMockitoSpy {

//    @Test
//    public void testLinkedListSpyWrong() {
//        List<String> list = new LinkedList<>();
//        List<String> spy = spy(list);
//
//        when(spy.get(0)).thenReturn("foo");    // <--- BAD
//        assertEquals("foo", spy.get(0));
//    }

    @Test
    public void testLinkedListSpyWorks() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        doReturn("foo").when(spy).get(0);
        assertEquals("foo", spy.get(0));
    }
}
