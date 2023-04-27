package oop.encapsulation;


import utils.Generated;

@Generated
public class BadEncapsulationExample {

    public static final String USERNAME = "emangini";

    public static void main(String[] args) {
        BadUser user = new BadUser(USERNAME, "12345");

        user.login(USERNAME, "12345");
        user.login(USERNAME, "9999");

        // Insecure!
        user.password = "9999";
        user.login(USERNAME, "9999");
    }
}

@Generated
// Suppressing warnings about public fields
@SuppressWarnings("java:S1104")
class BadUser {

    // public fields
    public final String username;
    public String password;


    // Parameterized login
    public BadUser(String username, String password) {
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
