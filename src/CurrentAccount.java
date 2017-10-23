/**
 * Account Type: Current.
 * This account is a standard account, it has no interest rates
 * for saving money. It will however have an overdraft. 
 */
public class CurrentAccount implements Account {

    private double balance;
    private boolean isJoint;
    private User accountHolder;
    private String accountName;
    private Double overdraft;

    /**
     * Constructor
     *
     * @param balance the initial balance of the account
     * @param isJoint boolean switch to decide if joint or not (UNSURE ABOUT THIS)
     */
    CurrentAccount(double balance, boolean isJoint, User accountHolder, String accountName, Double overdraft) {
        this.balance = balance;
        this.isJoint = isJoint;
        this.accountHolder = accountHolder;
        this.accountName = accountName;
        this.overdraft = overdraft;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

   @Override
    public boolean withdraw(double amount) {
        if((balance - amount) < -(overdraft)){
            System.out.println("Sorry but the amount you'd like to withdraw exceeds the overdraft you've set of £"+ overdraft);
            return false;
       }
       else{
            balance -= amount;
            return true;

        }

    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getName() {
        return accountName;
    }

    public Double getOverdraft(){
        return overdraft;
    }

    @Override
    public void transferFrom(double amount) {
    		balance -= amount;
    }
}
