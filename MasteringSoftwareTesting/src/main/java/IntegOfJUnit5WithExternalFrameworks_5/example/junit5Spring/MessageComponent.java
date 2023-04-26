package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Spring;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;


/*
    Class level Component annotation defines this as a spring bean/component.
 */
@Component

/*
    Using Lombok's AllArgsConstructor + NonNull means that we are injecting MessageService as ANOTHER Spring
    bean/component using the class constructor.

 */
@AllArgsConstructor
public class MessageComponent {

    @NonNull
    private MessageService messageService;

    public String getMessage() {
        return messageService.getMessage();
    }
}
