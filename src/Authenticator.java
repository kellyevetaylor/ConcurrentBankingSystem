import java.util.ArrayList;
import java.util.List;

/**
 * Handles user authentication
 */
class Authenticator {
    private List<User> userList;

    Authenticator() {
        userList = new ArrayList<>();
    }

    /**
     * Returns true if the user is successfully authenticated.
     * A successful authentication occurs when a user enters a matching pair of
     * username and password.
     * @param check the user to authenticate
     * @return true if authenticated, false otherwise
     */
    boolean authenticateUser(User check) {
        try {
            for (User user : userList) {
                if (user.getUsername().equals(check.getUsername()) && user.getPassword().equals(check.getPassword())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    void setAuthenticated(User user) {
        userList.add(user);
    }
}
