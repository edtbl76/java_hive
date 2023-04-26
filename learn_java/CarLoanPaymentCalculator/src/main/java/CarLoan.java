public class CarLoan {

    private static final int loanLength = 3;
    private static final int carLoan = 10_000;
    private static final int interestRate = 5;
    private static final int downPayment = 2000;

    public static void main(String[] args) {

        if (loanLength <= 0 || interestRate <= 0) {
            System.out.println("Invalid Loan Terms: ");
            System.out.println("\tInterest Rate: " + interestRate + "%");
            System.out.println("\tLoan Length  : " + loanLength + " years.");
        } else if (downPayment >= carLoan) {
            System.out.println("Car can be paid in full");
        } else {
            int remainingBalance = carLoan - downPayment;
            int months = loanLength * 12;
            int monthlyBalance = remainingBalance / months;
            int interest = (monthlyBalance * interestRate) / 100;
            int monthlyPayment = monthlyBalance + interest;
            System.out.println(monthlyPayment);
        }


    }
}
