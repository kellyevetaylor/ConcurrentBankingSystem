/**
 * Account type: Savings.
 * This will allow the user to enter an amount and gain interest on it.
 * Right now, it just mimics a current account.
 */

public class SavingsAccount implements Account {

    private double balance;
    private String accountName;
    private User accountHolder;
    private boolean isJoint;

    SavingsAccount(double balance, boolean isJoint, User accountHolder, String accountName){
        this.balance = balance;
        this.isJoint = isJoint;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void credit(double amount) { balance -= amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getName() {
        return accountName;
    }

    @Override
    public void transfer() {

    }
}
