public class TransferRunnable implements Runnable {
    double amount;
    Account account;
    Account transferAcc;

    public TransferRunnable(double amountin, Account accountIn, Account account2) {
        account = accountIn;
        transferAcc = account2;
        amount = amountin;
    }

    @Override
    public void run() {
        if (account.transferFrom(amount, transferAcc)) {

            System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
            System.out.println("Transfer successful");
        } else {
            System.out.println("Transfer Failed, insufficient funds.");
        }
    }
}