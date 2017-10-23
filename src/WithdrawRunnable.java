public class WithdrawRunnable implements Runnable {


double amount;
Account account;
private static final int DELAY =10;
private static final int DELAY2 =10000;

public WithdrawRunnable(double amountIn,Account accountIn){
	amount = amountIn;
	account = accountIn;
}


@Override
public void run() {
	try{
		//Thread.sleep(DELAY);
		while(account.withdraw(amount) == false){
			System.out.println("Not enough funds, waiting for funds to be deposited.");
			Thread.sleep(DELAY2);
		}
		
		System.out.println("Thread with id "+ Thread.currentThread().getId()+ ",Withdrawing from Account Name:"+account.getName());
		System.out.println("Thread with id "+ Thread.currentThread().getId()+ ", Account Balance:"+account.getBalance());	
		}catch(InterruptedException exception){}
		
}



}



