public class CheckBalanceRunnable implements Runnable {


    double amount;
    Account account;
    private static final int DELAY = 10;

    public CheckBalanceRunnable(Account accountIn) {
        account = accountIn;
    }

    @Override
    public void run() {
        try {

            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
          //  Thread.sleep(DELAY);
        //} catch (InterruptedException exception) {
        }finally{
        	
        }

    }
}

