import java.util.concurrent.locks.*;

public class WithdrawRunnable implements Runnable {


double amount;
Account account;
private static final int DELAY =100;
private static final int DELAY2 =1000;
private  ReentrantLock lock =new ReentrantLock();
private Condition noFundsCondition;

public WithdrawRunnable(double amountIn,Account accountIn){
	amount = amountIn;
	account = accountIn;
}


@Override
public void run() {
	boolean noFunds=true;
	int counter=0;
	//lock.lock();
	try{
		noFundsCondition=lock.newCondition();
		while(noFunds){
		if (counter == 4){
			Thread.currentThread().interrupt();
		}
		if(account.withdraw(amount) == false){
			noFunds=true;
			System.out.println("Waiting for funds to be deposited.");
			Thread.sleep(DELAY2);
			counter++;
			
			
		}else{
		
			noFunds=false;
	
		System.out.println("Thread with id "+ Thread.currentThread().getId()+ ",Withdrawing from Account Name:"+account.getName());
		System.out.println("Thread with id "+ Thread.currentThread().getId()+ ", Account Balance:"+account.getBalance());	

		}
		}
		

		}
	catch(InterruptedException exception){
		System.out.println("Cannot wait any longer for funds");
	}finally{
		System.out.println("Withdraw finished");
		}
	
		
}



}



