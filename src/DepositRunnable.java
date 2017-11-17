import java.util.concurrent.locks.*;

public class DepositRunnable implements Runnable {

    Account account;
    double amount;
    private static final int DELAY = 1;
    private ReentrantLock lock = new ReentrantLock();

    public DepositRunnable(double amountIn, Account accountIn) {
        amount = amountIn;
        account = accountIn;
    }

    @Override
    public void run() {

        try {
            account.deposit(amount);

            System.out.println("Thread with id " + Thread.currentThread().getId() + ",Depositing to Account Name:" + account.getName());
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());

        } finally {
            System.out.println("Deposit finished");
        }
    }
}
