import java.util.concurrent.locks.Lock;

/**
 * Account type: Savings.
 * This will allow the user to enter an amount and gain interest on it.
 */
import java.util.concurrent.locks.*;

/**
 * Account type: Savings
 * This account allows the user to do everything a current account
 * can do except withdraw.
 */
public class SavingsAccount implements Account {

    private double balance;
    private String accountName;
    private User accountHolder;
    private double interest;
    private final Lock lock;

    SavingsAccount(double balance, User accountHolder, String accountName) {
        this.balance = balance;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.lock = new ReentrantLock();
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

    public void editAccount(String name) {
        lock.lock();
        try {

            this.accountName = name;
        } finally {
            lock.unlock();
        }
    }


    public void addInterest(double interest) {
        balance += interest;
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

    public boolean withdraw(double amount) {
        System.out.println("Cannot withdraw from savings");
        return false;
    }

}
