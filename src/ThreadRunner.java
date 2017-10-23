
public class ThreadRunner {

	/**
	 * Runs the threads
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User("Andrew","1234");
		Account account = new CurrentAccount(100.0,false,user,"Andrew",0.0);
		user.addAccount(account);

		//TODO either just change runnables to launch user interface or keep with hard coding tests

		//Scenario 1 - 2 account holders checking balance simultaneously
		CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);
		Thread checkBalanceThread = new Thread(checkBalance);
		Thread checkBalanceThread2 = new Thread(checkBalance);

		checkBalanceThread.start();
		checkBalanceThread2.start();


		/*DepositRunnable deposit = new DepositRunnable(100000.00,account);
		Thread depositThread = new Thread(deposit);

		WithdrawRunnable withdraw= new WithdrawRunnable(1000.00,account);
		Thread withdrawThread = new Thread(withdraw);*/

		//depositThread.start();
		//withdrawThread.start();

	}
}
