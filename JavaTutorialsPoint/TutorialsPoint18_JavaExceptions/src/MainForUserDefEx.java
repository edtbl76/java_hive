public class MainForUserDefEx {

    public static void main(String[] args) {
        TestsUserDefEx t = new TestsUserDefEx(101);
        System.out.println("Putting in 500 smackeroonies...");
        t.deposit(500.00);

        try {
            System.out.println("\nGimme $100!... Daddy needs a new pair of shoes. ");
            t.withdraw(100.00);
            System.out.println("\nGimme 600, hookers don't come cheap no mo'!");
            t.withdraw(600.00);
        } catch (UserDefException e) {
            System.out.println("You're light by $" + e.getAmount());
            e.printStackTrace();
        }
    }
}
