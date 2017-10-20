
public class ThreadRunner {

	public static void main(String[] args) {
		User user = new User("Andrew","1234");
		Account account = new CurrentAccount(0.0,false,user,"Andrew",0.0);
		user.addAccount(account);
		
		//TODO either just change runnables to launch user interface or keep with hard coding tests
		//TODO fix timing
		
		DepositRunnable deposit = new DepositRunnable(100000.00,account);
		Thread depositThread = new Thread(deposit);
		
		WithdrawRunnable withdraw= new WithdrawRunnable(1000.00,account);
		Thread withdrawThread = new Thread(withdraw);
		
		depositThread.start();
		withdrawThread.start();
	}

}
