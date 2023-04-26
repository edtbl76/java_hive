package MockitoExamples.BasicStuff;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class APIDemo {

    @Mock
    MyDatabase dbMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testQuery() {
        ClassToTest t = new ClassToTest(dbMock);
        boolean check = t.query("string");
        assertTrue(check);
        verify(dbMock).query("string");
    }
}



class ClassToTest {

    MyDatabase database;

    public ClassToTest(MyDatabase database) {
        this.database = database;
    }

    public boolean query(String query) {
        System.out.println(query);
        boolean result = database.query(query);
        System.out.println(result);
        return result;
    }
}

class MyDatabase {
    public boolean query(String query) {
        System.out.println(query);
        return query != null;
    }
}