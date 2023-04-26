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
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@ExtendWith(MockitoExtension.class)
public class LoginControllerBDDTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    LoginService loginService;

    UserForm userForm = new UserForm("root", "password");

    @Test
    void testLoginOk() {
        given(loginService.login(userForm)).willReturn(true);
        assertEquals("OK", loginController.login(userForm));
    }

    @Test
    void testLoginKo() {
        given(loginService.login(userForm)).willReturn(false);
        assertEquals("KO", loginController.login(userForm));
    }

    @Test
    void testLoginError() {
        assertEquals("ERROR", loginController.login(null));
    }

    @Test
    void testLoginException() {
        given(loginService.login(any(UserForm.class))).willThrow(IllegalArgumentException.class);
        assertEquals("ERROR", loginController.login(userForm));
    }
}
