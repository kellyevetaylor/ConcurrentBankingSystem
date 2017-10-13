
public interface Account {

    /**
     * Adds money to an account
     * @param amount the quantity of money to add
     */
    void deposit(double amount);

    /**
     * Removes money from an account
     * @param amount the quantity of money to remove
     */

    //This methodnot needed since not all accounts can withdraw
    //void credit(double amount);

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
    void transfer();


}
