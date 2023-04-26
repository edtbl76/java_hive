package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.tests;

import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginException;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginRepository;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginService;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.MockitoExtension;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    LoginService loginService;

    @Mock
    LoginRepository loginRepository;

    UserForm userForm = new UserForm("root", "password");

    @Test
    void testLoginOk() {
        when(loginRepository.login(any(UserForm.class))).thenReturn(true);
        assertTrue(loginService.login(userForm));
        verify(loginRepository, atLeast(1)).login(userForm);
    }

    @Test
    void testLoginKo() {
        when(loginRepository.login(any(UserForm.class))).thenReturn(false);
        assertFalse(loginService.login(userForm));
        verify(loginRepository, times(1)).login(userForm);
    }

    @Test
    void testLoginTwice() {
        when(loginRepository.login(userForm)).thenReturn(true);
        assertThrows(LoginException.class, () -> {
           loginService.login(userForm);
           loginService.login(userForm);
        });
    }

}
