
public class DepositRunnable implements Runnable {
private User user = new User("Andrew","1234");
Account account = new CurrentAccount(0.0,false,user,"Andrew",0.0);
double amount;

public DepositRunnable(double amountIn){
	amount = amountIn;
}


@Override
public void run() {
	user.addAccount(account);
	account.deposit(amount);

	System.out.println("Thread with id "+ Thread.currentThread().getId()+ ", Account Name:"+account.getName());
	System.out.println("Thread with id "+ Thread.currentThread().getId()+ ", Account Balance:"+account.getBalance());
}



}



