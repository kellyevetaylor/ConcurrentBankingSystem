import java.util.concurrent.locks.Lock;

/**
 * Account type: Savings.
 * This will allow the user to enter an amount and gain interest on it.
 */
import java.util.concurrent.locks.*;
public class SavingsAccount implements Account {

    private double balance;
    private String accountName;
    private User accountHolder;
    private boolean isJoint;
    private double interest;
    Lock lock;
    SavingsAccount(double balance, boolean isJoint, User accountHolder, String accountName){
        this.balance = balance;
        this.isJoint = isJoint;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        lock = new ReentrantLock();
    }

    @Override
    public void deposit(double amount) {
      lock.lock();
      try{ balance += amount;
       }finally{
    	   lock.unlock();
       }
       }
       

    public void addInterest(double interest){
        balance += interest;
    }

    @Override
    public double getBalance() {
        lock.lock();try{ return balance;
        }finally{lock.unlock();}
        }

    @Override
    public String getName() {
        return accountName;
    }

    @Override
    public boolean transferFrom(double amount, Account accountIn) {
    	lock.lock();
    	try{if(balance<amount){return false;}else{
        	balance -= amount;
        	accountIn.deposit(amount);
        	System.out.println("Amount: £ "+amount+ " transferred to account with name: "+accountIn.getName());
        	return true;
        	}}finally{lock.unlock();}
    }

    public boolean withdraw(double amount){
    	System.out.println("Cannot withdraw from savings");
    	return false;
    }

}
