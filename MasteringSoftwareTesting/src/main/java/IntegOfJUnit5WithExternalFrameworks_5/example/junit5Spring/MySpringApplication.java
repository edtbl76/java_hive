package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class MySpringApplication {

    public static void main(String[] args) {

        /*
            We are creating the Spring application context.
         */
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(MySpringApplication.class)) {

            /*
                We use the ApplicationContext to get our bean/spring component (MessageComponent)
                - from the MessageComponent, we call the getMessage() method.

             */
            MessageComponent messageComponent = context.getBean(MessageComponent.class);
            System.out.println(messageComponent.getMessage());
        }
    }
}
