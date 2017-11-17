import java.util.concurrent.locks.Lock;

/**
 * Account type: Kids.
 * This is a savings account for kids but will be joint with a parent.
 * May have some interest and a maximum withdraw.
 */
import java.util.concurrent.locks.*;

public class KidsAccount implements Account {

    private double balance;
    private String accountName;
    private User accountHolder;
    private static double max;
    Lock lock;

    KidsAccount(double balance, User accountHolder, String accountName, double max) {
        this.balance = balance;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.max = max;
        lock = new ReentrantLock();
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
     * @return
     */
    public double getMax() {
        return max;
    }

    /**
     * "Parent" enters maximum amount that's allowed to be withdrawn from
     * kids account.
     *
     * @return
     */
    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (amount >= max) {
                System.out.println("Cannot withdraw over the £" + max + " set.");
                return false;
            } else {
                if (balance - amount < 0) {
                    System.out.println("Your account doesnt have an overdraft amount set");
                    return false;
                } else {
                    balance -= amount;
                    return true;
                }
            }
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
    public void EditAccount(String name){
    	lock.lock();
    	try{

            this.accountName = name;
    	}finally{lock.unlock();}
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
