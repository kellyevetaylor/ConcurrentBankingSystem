
public class ThreadRunner {

	public static void main(String[] args) {
		DepositRunnable deposit = new DepositRunnable(100000.00);
		Thread depositThread = new Thread(deposit);
		
		depositThread.start();
	}

}
