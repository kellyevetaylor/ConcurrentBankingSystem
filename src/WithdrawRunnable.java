import java.util.concurrent.locks.*;

public class WithdrawRunnable implements Runnable {


double amount;
Account account;
private static final int DELAY =100;
private static final int DELAY2 =10000;
private  ReentrantLock lock =new ReentrantLock();

public WithdrawRunnable(double amountIn,Account accountIn){
	amount = amountIn;
	account = accountIn;
}


@Override
public void run() {
	boolean noFunds=true;
	//lock.lock();
	try{
		while(noFunds){
		//Thread.sleep(DELAY);
		if(account.withdraw(amount) == false){
			noFunds=true;
			System.out.println("Waiting for funds to be deposited.");
			Thread.sleep(DELAY);
		}else{
		
			noFunds=false;
	
		System.out.println("Thread with id "+ Thread.currentThread().getId()+ ",Withdrawing from Account Name:"+account.getName());
		System.out.println("Thread with id "+ Thread.currentThread().getId()+ ", Account Balance:"+account.getBalance());	

		}
		}
		

		}
	catch(InterruptedException exception){
	}finally{
		System.out.println("Withdraw finished");
		}
	
		
}



}



