import java.util.ArrayList;
import java.util.List;

/**
 * Model the user and establish the relationship with the account class in here.
 * Also consider authentication in here
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

    /**
     * Adds an account to the users list of accounts
     * @param account the account to be added
     */
    void addAccount(Account account) {
        accountsList.add(account);
    }
    void removeAccount(Account account){
    	if(account.getBalance()!= 0.0){
    		System.out.println("Cannot remove account since it still has money in it.");
    	}else{accountsList.remove(account);
    }}
}
