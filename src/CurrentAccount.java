import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Account Type: Current.
 * This account is a standard account, it has no interest rates
 * for saving money. It will however have an overdraft.
 */
public class CurrentAccount implements Account {
    private double balance;
    User accountHolder;
    private String accountName;
    private Double overdraft;
    private final Lock lock;
    private Condition noFundsCondition;

    /**
     * Constructor
     *
     * @param balance       of the account
     * @param accountHolder user of the account
     * @param accountName   name of the account
     * @param overdraft     overdraft limit
     */
    CurrentAccount(double balance, User accountHolder, String accountName, Double overdraft) {
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.accountName = accountName;
        this.overdraft = overdraft;
        lock = new ReentrantLock();
        noFundsCondition = lock.newCondition();
    }

    @Override
    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            noFundsCondition.signalAll();
        } finally {

            lock.unlock();
        }
    }

    @Override
    public boolean withdraw(double amount) {
        boolean stillWaiting = true;

        lock.lock();

        try {
            while ((balance - amount) < -(overdraft)) {
                
                if (!stillWaiting) {
                    Thread.currentThread().interrupt();

                }
                if (stillWaiting) {
                    System.out.println("Thread with id " + Thread.currentThread().getId() + ", Waiting for account balance to increase");
                }
                System.out.println("Thread with id " + Thread.currentThread().getId() + ", Sorry but the amount you'd like to withdraw exceeds the overdraft you've set of £" + overdraft);
                stillWaiting = noFundsCondition.await(5, TimeUnit.SECONDS);


            }

            balance -= amount;
            return true;


        } catch (InterruptedException exception) {
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Cannot wait any longer for funds. Terminating Withdraw");
            return false;
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
    public double getBalance() {
        lock.lock();
        try {
            return balance;
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
