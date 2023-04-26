package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.tests;

import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginController;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginService;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.MockitoExtension;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerErrorTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    LoginService loginService;

    UserForm userForm = new UserForm("root", "password");

    @Test
    void testLoginError() {

        // Jazzercise!
        String response = loginController.login(null);

        // Verify
        assertEquals("ERROR", response);
    }

    @Test
    void testLoginException() {
        // Expectation
        when(loginService.login(any(UserForm.class))).thenThrow(IllegalArgumentException.class);

        // Exercise
        String response = loginController.login(userForm);

        // Verify
        assertEquals("ERROR", response);
    }
}
