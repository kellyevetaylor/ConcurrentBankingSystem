
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

		DepositRunnable deposit = new DepositRunnable(100000.00,account);
		WithdrawRunnable withdraw= new WithdrawRunnable(1000.00,account);
		CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);

		//Scenario 1 - 2 account holders checking balance simultaneously
		//Thread checkBalanceThread1 = new Thread(checkBalance);
		//Thread checkBalanceThread2 = new Thread(checkBalance);

		/*checkBalanceThread1.start();
		checkBalanceThread2.start();*/


		//Scenario 3 - 2 account holders simultaneously depositing/withdrawing and checking balance
		Thread depositThread = new Thread(deposit);
		Thread withdrawThread = new Thread(withdraw);
		Thread checkBalanceThread3 = new Thread(checkBalance);
		Thread checkBalanceThread4 = new Thread(checkBalance);


		depositThread.start();
		checkBalanceThread3.start();
		withdrawThread.start();
		checkBalanceThread4.start();


	}
}
