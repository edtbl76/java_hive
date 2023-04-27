package oop.encapsulation;


import utils.Generated;

@Generated
public class GoodEncapsulationExample {

    public static final String EMANGINI = "emangini";

    public static void main(String[] args) {
        GoodUser user = new GoodUser(EMANGINI, "12345");

        user.login(EMANGINI, "12345");
        user.login(EMANGINI, "9999");
        
        /* Private field inaccessible.
             This is GOOD ENCAPSULATION.
            user.password = "9999"; <- This won't compile.
         */
    }
}

@Generated
class GoodUser {

    // private fields
    private final String username;
    private final String password;

    // Parameterized login
    public GoodUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SuppressWarnings("java:S106")
    public void login(String username, String password) {
        // Check if they match
        if (this.username.toLowerCase().equals(username) && this.password.equals(password)) {
            System.out.println(this.username + " successfully logged in.");
        } else {
            System.out.println("Access Denied");
        }
    }
}
