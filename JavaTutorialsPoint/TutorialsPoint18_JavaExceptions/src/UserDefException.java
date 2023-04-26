// deliberately set to suppress warnings. This is just an example of a user defined exception.
@SuppressWarnings("unused")
class UserDefException extends Exception {

    private final double amount;

    UserDefException(double amount) {
        this.amount = amount;
    }

    double getAmount() {
        return amount;
    }

}
