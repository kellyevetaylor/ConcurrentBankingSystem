import java.util.concurrent.locks.*;
public class DepositRunnable implements Runnable {


    Account account;
    double amount;
    private static final int DELAY = 1;
    private  ReentrantLock lock =new ReentrantLock();

    public DepositRunnable(double amountIn, Account accountIn) {
        amount = amountIn;
        account = accountIn;
    }

    @Override
    public void run() {
    	lock.lock();
    	try {
        	//lock.lock();
            account.deposit(amount);
          //  Thread.sleep(DELAY);
            System.out.println("Thread with id " + Thread.currentThread().getId() + ",Depositing to Account Name:" + account.getName());
            System.out.println("Thread with id "+ Thread.currentThread().getId()+ ", Account Balance:"+account.getBalance());	

      //  } catch (InterruptedException exception) {
        }finally{
        	System.out.println("Deposit finished");
        	lock.unlock();
        }

    }}
