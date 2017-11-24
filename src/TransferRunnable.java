/**
 * Runnable for transferring money from one account into
 * another. This is how an employee completes standing orders.
 */

public class TransferRunnable implements Runnable {
    double amount;
    Account account;
    Account transferAcc;

    public TransferRunnable(double amountIn, Account accountIn, Account account2) {
        account = accountIn;
        transferAcc = account2;
        amount = amountIn;
    }

    @Override
    public void run() {
        if (account.transferFrom(amount, transferAcc)) {

            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Transfer successful");
        } else {
        	 System.out.println("Thread with id " + Thread.currentThread().getId() + ", Transfer Failed, insufficient funds.");
        }
    }
}