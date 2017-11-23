
public interface Account {

    /**
     * Adds money to an account
     *
     * @param amount the quantity of money to add
     */
    void deposit(double amount);

    /**
     * Gets the balance of the account
     *
     * @return the balance of the account
     */
    double getBalance();

    /**
     * Gets the name of the account
     *
     * @return the name of the account
     */
    String getName();

    /**
     * Transfer money from account to another
     */
    boolean transferFrom(double amount, Account account);

    /**
     * Withdraw money from an account
     *
     * @param amount the amount to be withdrew
     * @return
     */
    boolean withdraw(double amount);

    /**
     * Edits the name of an account
     *
     * @param name the name to be changed
     */
    void editAccount(String name);
}