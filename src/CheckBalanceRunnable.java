public class CheckBalanceRunnable implements Runnable {
    Account account;

    public CheckBalanceRunnable(Account accountIn) {
        account = accountIn;
    }

    @Override
    public void run() {
        System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
    }
}


