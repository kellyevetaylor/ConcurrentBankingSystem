/**
 * Account Type: Current.
 * This account is a standard account, it has no interest rates
 * for saving money. It will however have an overdraft. 
 */
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
public class CurrentAccount implements Account {

    private double balance;
    private User accountHolder;
    private String accountName;
    private Double overdraft;
    private final Lock lock;
    private Condition noFundsCondition;

    /**
     * Constructor
     *
     * @param balance the initial balance of the account
     */
    CurrentAccount(double balance, User accountHolder, String accountName, Double overdraft) {
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.accountName = accountName;
        this.overdraft = overdraft;
        this.lock = new ReentrantLock();
        this.noFundsCondition = lock.newCondition();
    }

    @Override
    public void deposit(double amount) {
    	lock.lock();
    	try{
        balance += amount;
        noFundsCondition.signalAll();
    }finally{
    	
    	lock.unlock();
    }
    	}

   @Override
    public boolean withdraw(double amount) {
	   boolean stillWaiting = true;
		
	   lock.lock();;
	   
	   try{
        while((balance - amount) < -(overdraft)){
        	//System.out.println("Waiting for Funds to increase");
        	if(!stillWaiting){
        		Thread.currentThread().interrupt();
        	
        	}
        	if(stillWaiting){
        		System.out.println("Waiting for account balance to increase");
        	}
        	
        	stillWaiting= noFundsCondition.await(5, TimeUnit.SECONDS);
        	System.out.println("Sorry but the amount you'd like to withdraw exceeds the overdraft you've set of £"+ overdraft);
        	
       }
      
            balance -= amount;
            return true;
       

	   }catch(InterruptedException exception){
		   System.out.println("Cannot wait any longer for funds. Terminating Withdraw");
		   return false;
	   }finally{lock.unlock();}

    }


    @Override
    public void editAccount(String name){
    	lock.lock();
    	try{

            this.accountName = name;
    	}finally{lock.unlock();}
    }


    @Override
    public double getBalance() {
       lock.lock();try{ return balance;
    }finally{lock.unlock();}}

    @Override
    public String getName() {
        return accountName;
    }

    public Double getOverdraft(){
        return overdraft;
    }

    @Override
    public boolean transferFrom(double amount, Account accountIn) {
    	lock.lock();
    	try{if(balance<amount){return false;}else{
        	balance -= amount;
        	accountIn.deposit(amount);
        	System.out.println("Amount: £ "+amount+ " transferred to account with name: "+accountIn.getName());
        	return true;
        	}
    	}
    	finally{
    	    lock.unlock();
    	}
    }
}
