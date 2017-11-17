
public class ThreadRunner {

	
	/**
	 * Runs the threads
	 * @param args
	 */
	public static void main(String[] args) {
		
		User user = new User("Andrew","1234");

		Account account = new CurrentAccount(10.0,false,user,"Andrew",0.0);		//Initial value 10
		user.addAccount(account);
		//TODO either just change runnables to launch user interface or keep with hard coding tests

		//DepositRunnable deposit = new DepositRunnable(100000.00,account);
		//WithdrawRunnable withdraw= new WithdrawRunnable(1000.00,account);
		

	
	//	scenario3(account);


	scenario4(account);
	//scenario1(account);

		//scenario2(account);
		
	}
	public static void scenario1(Account account){
		CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);
		Thread checkBalanceThread1 = new Thread(checkBalance);
				Thread checkBalanceThread2 = new Thread(checkBalance);

				checkBalanceThread1.start();
				checkBalanceThread2.start();
	}

	public static void scenario4(Account account){
		DepositRunnable deposit = new DepositRunnable(100000.00,account);

		


		WithdrawRunnable withdraw= new WithdrawRunnable(1000.00,account);
		Thread withdrawThread = new Thread(withdraw);
		Thread depositThread = new Thread(deposit);
		


		

		withdrawThread.start();
		depositThread.start();
		
	}
	public static void scenario2(Account account){
		CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);
		Thread checkBalanceThread = new Thread(checkBalance);
		
		DepositRunnable deposit = new DepositRunnable(100000.00,account);
		Thread depositThread = new Thread(deposit);
		
		checkBalanceThread.start();
		depositThread.start();
		
		
	}

	public static void scenario3(Account account){
		//Scenario 3 - 2 account holders simultaneously depositing/withdrawing and checking balance
		CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);
			WithdrawRunnable withdraw= new WithdrawRunnable(1000.00,account);
			DepositRunnable deposit = new DepositRunnable(100000.00,account);
				Thread withdrawThread = new Thread(withdraw);
				Thread checkBalanceThread3 = new Thread(checkBalance);
				Thread checkBalanceThread4 = new Thread(checkBalance);
				
				Thread depositThread = new Thread(deposit);
				
				depositThread.start();
				checkBalanceThread3.start();
				withdrawThread.start();
				checkBalanceThread4.start();

	}

}

