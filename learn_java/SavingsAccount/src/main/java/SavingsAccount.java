public class SavingsAccount {

    int balance;

    public SavingsAccount(int initialBalance){
        balance = initialBalance;
    }

    public void checkBalance() {
        System.out.println("Hello!\nYour balance is " + balance);
    }

    public void deposit(int amountToDeposit) {
        System.out.println("You just deposited " + amountToDeposit);
        balance += amountToDeposit;
    }

    public int withdraw(int amountToWithdraw) {
        System.out.println("You just withdrew " + amountToWithdraw);
        balance -= amountToWithdraw;
        return amountToWithdraw;
    }

    public static void main(String[] args){
        SavingsAccount savings = new SavingsAccount(2000);

        //Check balance:
        System.out.println("Hello!");
        savings.checkBalance();

        //Withdrawing
        savings.withdraw(300);

        //Check balance:
        savings.checkBalance();

        //Deposit:
        savings.deposit(600);

        //Check balance:
        savings.checkBalance();



    }
}