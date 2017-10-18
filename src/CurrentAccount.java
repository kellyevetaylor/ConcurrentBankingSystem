/**
 * Account Type: Current.
 * This account is a standard account, it has no interest rates
 * for saving money. It will however have an overdraft. TODO think about overdraft implementation
 */
public class CurrentAccount implements Account {

    private static double balance;
    private boolean isJoint;
    private User accountHolder;
    private String accountName;
    private Integer overdraft;

    /**
     * Constructor
     *
     * @param balance the initial balance of the account
     * @param isJoint boolean switch to decide if joint or not (UNSURE ABOUT THIS)
     */
    CurrentAccount(double balance, boolean isJoint, User accountHolder, String accountName) {
        this.balance = balance;
        this.isJoint = isJoint;
        this.accountHolder = accountHolder;
        this.accountName = accountName;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

   
    public static void withdraw(double amount) {
        balance -= amount;
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
