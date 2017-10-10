import java.util.ArrayList;
import java.util.List;

/**
 * Model the user and establish the relationship with the account class in here.
 * Also consider authentication in here/
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

    List<Account> getAccounts() {
        return accountsList;
    }
    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }

    void addAccount(Account account) {
        accountsList.add(account);
    }
}
