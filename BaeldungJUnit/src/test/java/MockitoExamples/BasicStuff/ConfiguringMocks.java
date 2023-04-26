package MockitoExamples.BasicStuff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ConfiguringMocks {

    @Test
    public void test1() {
        // create mock
        ExampleClass test = mock(ExampleClass.class);

        // define return value for method getUniqueId()
        when(test.getUniqueId()).thenReturn(43);

        // use mock in test
        assertEquals(test.getUniqueId(), 43);
    }


    @Test
    public void testReturnValueDependentOnMethodParameter() {
        Comparable<String> comparable = mock(Comparable.class);
        when(comparable.compareTo("MockitoExamples")).thenReturn(1);
        when(comparable.compareTo("JUnit")).thenReturn(2);
        assertEquals(1, comparable.compareTo("MockitoExamples"));
    }

    @Test
    public void testReturnValueIndependentOnMethodParameter() {
        Comparable<Integer> comparable = mock(Comparable.class);
        when(comparable.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, comparable.compareTo(100));
    }

    @Test
    public void testReturnValueIndependentOnMethodParameter2() {
        Comparable<ExampleClass> comparable = mock(Comparable.class);
        when(comparable.compareTo(isA(ExampleClass.class))).thenReturn(0);
        assertEquals(-1, comparable.compareTo(new ExampleClass(1)));
    }

    @Test
    public void testWhenThen() {
        Properties properties = mock(Properties.class);

        when(properties.get("FART")).thenThrow(new IllegalArgumentException());

        try {
            properties.get("FART");
            fail("No such property as FART");
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }

    @Test
    public void doReturnWhenTest() {
        Properties properties = new Properties();
        Properties spyProperties = spy(properties);

        doReturn("42").when(spyProperties).get("shoeSize");
        String value = spyProperties.get("shoeSize").toString();

        assertEquals("42", value);
    }
}


