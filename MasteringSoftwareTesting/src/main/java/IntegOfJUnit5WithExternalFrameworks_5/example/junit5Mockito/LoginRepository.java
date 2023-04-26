package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito;

import java.util.HashMap;
import java.util.Map;

public class LoginRepository {
    Map<String, String> users;

    public LoginRepository() {
        users = new HashMap<>();
        users.put("user1", "pass1");
        users.put("user2", "pass2");
        users.put("user3", "pass3");
    }

    public boolean login(UserForm userForm) {
        System.out.println("LoginRepository.login " + userForm);

        String username = userForm.getUsername();
        String password = userForm.getPassword();

        return users.containsKey(username) && users.get(username).equals(password);
    }
}
