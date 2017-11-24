import java.util.concurrent.locks.Lock;

/**
 * Account type: Kids.
 * This is a savings account for kids but will be joint with a parent.
 * May have some interest and a maximum withdraw.
 */
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;

/**
 * Account type: Kids
 * This account is similar to a current account but
 * a maximum withdrawal amount is set during creation.
 */
public class KidsAccount implements Account {

    private double balance;
    private String accountName;
    private User accountHolder;
    private double max;
    private final Lock lock;
    private Condition noFundsCondition;

    /**
     * Constructor
     *
     * @param balance       of the account
     * @param accountHolder user of the account
     * @param accountName   name of the account
     * @param max           maximum withdrawal limit
     */
    KidsAccount(double balance, User accountHolder, String accountName, double max) {
        this.balance = balance;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.max = max;
        this.lock = new ReentrantLock();
        this.noFundsCondition = lock.newCondition();
    }

    @Override
    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the max withdrawal which is set during creation
     *
     * @return max
     */
    public double getMax() {
        return max;
    }

    public boolean withdraw(double amount) {
        boolean stillWaiting = true;

        lock.lock();
        try {
            if (amount >= max) {
                System.out.println("Cannot withdraw over the £" + max + " set.");
                return false;
            } else {
                while ((balance - amount) < 0) {
                    if (!stillWaiting) {
                        Thread.currentThread().interrupt();

                    }
                    System.out.println("Thread with id "+ Thread.currentThread().getId() +", Sorry but you dont have enough money in your account. Waiting for funds to increase.");
                    if (stillWaiting) {
                        System.out.println("Thread with id " + Thread.currentThread().getId() + ", Waiting for account balance to increase");
                    }
                    stillWaiting = noFundsCondition.await(10, TimeUnit.SECONDS);

                }

                balance -= amount;
                return true;
            }

        } catch (InterruptedException exception) {
            System.out.println("Cannot wait any longer for funds. Terminating Withdraw");
            return false;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void editAccount(String name) {
        lock.lock();
        try {

            this.accountName = name;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getName() {
        return accountName;
    }

    @Override
    public boolean transferFrom(double amount, Account accountIn) {
        lock.lock();
        try {
            if (balance < amount) {
                return false;
            } else {
                balance -= amount;
                accountIn.deposit(amount);
                System.out.println("Amount: £ " + amount + " transferred to account with name: " + accountIn.getName());
                return true;
            }
        } finally {
            lock.unlock();
        }
    }
}
