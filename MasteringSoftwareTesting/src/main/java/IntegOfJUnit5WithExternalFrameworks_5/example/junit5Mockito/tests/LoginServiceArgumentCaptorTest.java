package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.tests;

import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginRepository;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginService;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.MockitoExtension;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoginServiceArgumentCaptorTest {

    @InjectMocks
    LoginService loginService;

    @Mock
    LoginRepository loginRepository;

    @Captor
    ArgumentCaptor<UserForm> argumentCaptor;

    UserForm userForm = new UserForm("root", "password");

    @Test
    void testArgumentCaptor() {
        loginService.login(userForm);
        verify(loginRepository).login(argumentCaptor.capture());
        assertEquals(userForm, argumentCaptor.getValue());
    }
}
