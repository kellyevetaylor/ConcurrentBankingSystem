/**
 * Account type: Savings.
 * This will allow the user to enter an amount and gain interest on it.
 */

public class SavingsAccount implements Account {

    private double balance;
    private String accountName;
    private User accountHolder;
    private boolean isJoint;
    private double interest;

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

    public void addInterest(double interest){
        balance += interest;
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
    public void transferFrom(double amount) {
    	balance -= amount;
    }

    public void withdraw(double amount){
    	System.out.println("Cannot withdraw from savings");
    }

}
