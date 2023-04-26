package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito;

import java.util.ArrayList;
import java.util.List;

public class LoginService {

    private LoginRepository loginRepository = new LoginRepository();
    private List<String> usersLogged = new ArrayList<>();

    public boolean login(UserForm userForm) {
        System.out.println("LoginService.login " + userForm);

        // Preconditions
        checkForm(userForm);

        // Same user can't be logged in 2x
        String username = userForm.getUsername();
        if (usersLogged.contains(username)) {
            throw new LoginException(username + " already logged in.");
        }

        // Call to repo to make logic
        boolean loginState = loginRepository.login(userForm);

        if (loginState) {
            usersLogged.add(username);
        }
        return loginState;
    }


    public void logout(UserForm userForm) {
        System.out.println("LoginService.logout " + userForm);

        // Preconditions
        checkForm(userForm);

        // User should be logged in
        String username = userForm.getUsername();
        if (!usersLogged.contains(username)) {
            throw new LoginException(username + " not logged in.");
        }

        usersLogged.remove(username);
    }

    public int getUserLoggedCount() {
        return usersLogged.size();
    }

    private void checkForm(UserForm userForm) {
        assert userForm != null;
        assert userForm.getUsername() != null;
        assert userForm.getPassword() != null;
    }
}
