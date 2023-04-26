package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    - First Annotation wires up the spring-test module to the JUnit 5 Jupiter Platform
    - Second Annotation initializes the MySpringApplication ApplicationContext and all of it's components
        - MessageService and MessageComponent
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MySpringApplication.class})
public class SimpleSpringTest {

    // Autowired performs our injection (This is Field Injection)
    @Autowired
    public MessageComponent messageComponent;

    // Test execution, which calls getMessage() and verifies the result
    @Test
    public void test() {
        assertEquals("Hello world!", messageComponent.getMessage());
    }
}
