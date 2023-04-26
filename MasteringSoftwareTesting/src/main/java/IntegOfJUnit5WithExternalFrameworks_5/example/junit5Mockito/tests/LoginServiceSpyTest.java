package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.tests;

import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginRepository;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.LoginService;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.MockitoExtension;
import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.UserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class LoginServiceSpyTest {

    @InjectMocks
    LoginService loginService;

    /*
        In a Spy type test, there is no mocked object, because
        we want to use the real/partial impl
     */
    @Spy
    LoginRepository loginRepository;

    UserForm userOk = new UserForm("user1","pass1");
    UserForm userKo = new UserForm("LL", "CoolJ");

    @Test
    void testLoginOk() {
        assertTrue(loginService.login(userOk));
    }

    @Test
    void testLoginKo() {
        assertFalse(loginService.login(userKo));
    }

}
