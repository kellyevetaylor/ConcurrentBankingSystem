
public class DepositRunnable implements Runnable {


    Account account;
    double amount;
    private static final int DELAY = 1;

    public DepositRunnable(double amountIn, Account accountIn) {
        amount = amountIn;
        account = accountIn;
    }

    @Override
    public void run() {
        try {
            account.deposit(amount);
            Thread.sleep(DELAY);
            System.out.println("Thread with id " + Thread.currentThread().getId() + ",Depositing to Account Name:" + account.getName());
        } catch (InterruptedException exception) {
        }

    }}
