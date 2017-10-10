public class CurrentAccount implements Account{

    private double balance;
    private boolean isJoint;

    /**
     * Constructor
     * @param balance the initial balance of the account
     * @param isJoint boolean switch to decide if joint or not (UNSURE ABOUT THIS)
     */
    public CurrentAccount(double balance, boolean isJoint) {
        this.balance = balance;
        this.isJoint = isJoint;
    }

    @Override
    public void debit(double amount) {
        balance += amount;
    }

    @Override
    public void credit(double amount) {
        balance -= amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
