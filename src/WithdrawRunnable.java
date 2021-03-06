/**
 * Runnable for withdrawing money from an account
 */
public class WithdrawRunnable implements Runnable {

    double amount;
    Account account;

    public WithdrawRunnable(double amountIn, Account accountIn) {
        amount = amountIn;
        account = accountIn;
    }

    @Override
    public void run() {

        try {
            if (account instanceof SavingsAccount) {
                System.out.println("You cannot withdraw from savings accounts.");

            }
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Withdrawing from Account Name:" + account.getName());
            if (account.withdraw(amount) == false) {
                System.out.println("Thread with id " + Thread.currentThread().getId() + ", Failed withdraw.");
            } else {


                System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
            }

        } finally {
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Withdraw finished");
        }
    }
}