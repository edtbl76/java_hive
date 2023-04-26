@SuppressWarnings("unused") //
public class TestsUserDefEx {

    private double balance;
    private final int number;

    TestsUserDefEx(int number) {
        this.number = number;
    }

    void deposit(double amount) {
        balance += amount;
    }

    void withdraw(double amount)
            throws UserDefException
    {
        if (amount <= balance) {
            balance -= amount;
        } else {
            double needs = amount - balance;
            throw new UserDefException(needs);
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getNumber() {
        return number;
    }
}
