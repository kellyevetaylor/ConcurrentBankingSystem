
public interface Account {

    /**
     * Adds money to an account
     * @param amount the quantity of money to add
     */
    void deposit(double amount);

    /**
     * @return the balance of the account
     */
    double getBalance();

    /**
     * @return the name of the account
     */
    String getName();

    /**
     * Transfer money from account to another
     */
    void transferFrom(double amount);

    boolean withdraw(double amount);
//TODO editName()

}
