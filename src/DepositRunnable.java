/**
 * Runnable for depositing into an account
 */
public class DepositRunnable implements Runnable {

    Account account;
    double amount;

    public DepositRunnable(double amountIn, Account accountIn) {
        amount = amountIn;
        account = accountIn;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(700);
            account.deposit(amount);
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Depositing to Account Name:" + account.getName());
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Deposit finished");
        } catch (InterruptedException e) {
        }
    }
}
