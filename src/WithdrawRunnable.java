public class WithdrawRunnable implements Runnable {


double amount;
Account account;
private static final int DELAY =10;
public WithdrawRunnable(double amountIn,Account accountIn){
	amount = amountIn;
	account = accountIn;
}


@Override
public void run() {
	try{

		account.withdraw(amount);
		
		System.out.println("Thread with id "+ Thread.currentThread().getId()+ ",Withdrawing from Account Name:"+account.getName());
        Thread.sleep(DELAY);
		}catch(InterruptedException exception){}
		
}



}



