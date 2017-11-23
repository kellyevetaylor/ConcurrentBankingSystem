import java.util.ArrayList;
import java.util.List;

/**
 * Models a user. Stores the user's username and password
 * and adds these to a list of registered accounts.
 */
class User {
    private String username;
    private String password;
    private List<Account> accountsList;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        accountsList = new ArrayList<>();
    }

    /**
     * @return the lists of accounts currently registered
     */
    List<Account> getAccounts() {
        return accountsList;
    }

    /**
     * @return user's username
     */
    String getUsername() {
        return username;
    }

    /**
     * @return user's password
     */
    String getPassword() {
        return password;
    }

    /**
     * Adds an account to the users list of accounts
     *
     * @param account the account to be added
     */
    void addAccount(Account account) {
        accountsList.add(account);
    }

    /**
     * Removes an account from the users list of account
     *
     * @param account the account to be removed
     */
    void removeAccount(Account account) {
        if (account.getBalance() != 0.0) {
            System.out.println("Cannot remove account since it still has money in it.");
        } else {
            accountsList.remove(account);
        }
    }
}
