public class WithdrawRunnable implements Runnable {

    double amount;
    Account account;

    public WithdrawRunnable(double amountIn, Account accountIn) {
        amount = amountIn;
        account = accountIn;
    }

    @Override
    public void run() {
        boolean noFunds = true;
        try {
            while (noFunds) {
                if (account instanceof SavingsAccount) {
                    System.out.println("You cannot withdraw from savings accounts.");
                    break;
                }
                if (account.withdraw(amount) == false) {
                    noFunds = true;
                    System.out.println("Waiting for funds to be deposited.");
                } else {
                    noFunds = false;
                    System.out.println("Thread with id " + Thread.currentThread().getId() + ",Withdrawing from Account Name:" + account.getName());
                    System.out.println("Thread with id " + Thread.currentThread().getId() + ", Account Balance:" + account.getBalance());
                }
            }
        } finally {
            System.out.println("Withdraw finished");
        }
    }
}