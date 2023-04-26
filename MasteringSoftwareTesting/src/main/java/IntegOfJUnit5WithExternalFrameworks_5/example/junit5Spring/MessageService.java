package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Spring;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public String getMessage() {
        return "Hello world!";
    }
}
