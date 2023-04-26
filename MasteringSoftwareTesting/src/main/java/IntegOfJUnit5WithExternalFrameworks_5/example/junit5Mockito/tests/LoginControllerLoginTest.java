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
import static org.mockito.Mockito.*;


/*
    1. We are registering the MockitoExtension we created
 */
@ExtendWith(MockitoExtension.class)
public class LoginControllerLoginTest {

    // Mocking Objects
    /*
        2. This annotation declares the SUT
     */
    @InjectMocks
    LoginController loginController;

    /*
        3. This annotation declares the DOC
     */
    @Mock
    LoginService loginService;

    // Test Data
    UserForm userForm = new UserForm("spaceball1", "12345");

    /*
        Test Impl 1:
            - when we login, the mock of loginService should return true (stubbing method)
            - exercise the SUT (i.e. call the method to be tet)
            - verification steps
                - ensure that the response from the method is what we expected
                - interact w/ API again, and then verify that there are no further interactions.
     */
    @Test
    void testLoginOk() {

        // stubbing methods
        when(loginService.login(userForm)).thenReturn(true);

        // Exercise SUT
        String responseLogin = loginController.login(userForm);

        // Verification
        assertEquals("OK", responseLogin);
        verify(loginService).login(userForm);
        verifyNoMoreInteractions(loginService);
    }


    /*
        Test Impl 2:
            - when we login, the mock of loginService should return false (stubbing method)
            - exercise the SUT (i.e. call the method to be tet)
            - verification steps
                - ensure that the response from the method is what we expected
                - interact w/ API again, and then verify that there were no interactions
     */
    @Test
    void testLoginKo() {

        // stubbing methods
        when(loginService.login(userForm)).thenReturn(false);

        // Exercise SUT
        String responseLogin = loginController.login(userForm);

        // Verification
        assertEquals("KO", responseLogin);
        verify(loginService).login(userForm);
        verifyZeroInteractions(loginService);
    }
}
