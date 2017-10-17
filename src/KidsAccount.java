/**
 * Account type: Kids.
 * This is a savings account for kids but will be joint with a parent.
 * May have some interest and a maximum withdraw.
 */

public class KidsAccount implements Account {

    private double balance;
    private String accountName;
    private User accountHolder;
    private boolean isJoint;
    private double max;

    KidsAccount(double balance, boolean isJoint, User accountHolder, String accountName, double max) {
        this.balance = balance;
        this.accountName = accountName;
        this.accountHolder = accountHolder;
        this.max = max;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * "Parent" enters maximum amount that's allowed to be withdrawn from
     * kids account.    TODO anything over this should throw an error
     *
     * @return
     */
    public double getMax() {
        return max;
    }


    public void withdraw(double amount) {
        if (amount > max) {
            System.out.println("Cannot withdraw over the £" + max + " set.");
        } else {

            balance -= amount;
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

    @Override
    public void transfer() {

    }
}
