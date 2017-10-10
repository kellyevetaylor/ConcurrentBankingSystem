
public interface Account {

    /**
     * Adds money to an account
     * @param amount the quantity of money to add
     */
	void debit(double amount);

    /**
     * Removes money from an account
     * @param amount the quantity of money to remove
     */
    void credit(double amount);

    /**
     * Returns the balance of the account
     * @return the balance of the account
     */
	double getBalance();

}
