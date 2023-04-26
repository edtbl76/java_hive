package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserForm {

    @NonNull
    private String username;

    @NonNull
    private String password;

}
