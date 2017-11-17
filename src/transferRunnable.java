public class transferRunnable implements Runnable {


    double amount;
    Account account;
    Account transferAcc;
    private static final int DELAY = 10;

    public transferRunnable(double amountin,Account accountIn,Account account2) {
        account = accountIn;
        transferAcc = account2;
        amount = amountin;
    }

    @Override
    public void run() {
        try {
        	account.transferFrom(amount, transferAcc);
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
          //  Thread.sleep(DELAY);
        //} catch (InterruptedException exception) {
        }finally{
        	
        }

    }
}

