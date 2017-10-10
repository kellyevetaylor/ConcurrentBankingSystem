import java.util.ArrayList;
import java.util.Scanner;

class UserInterface {
    private Authenticator authenticator = new Authenticator();

    /**
     * Displays the main menu and allows the user to
     * make a selection of what they would like to do
     */
    void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Please select an option:");
        System.out.println();
        System.out.println("    1. Register");
        System.out.println("    2. Log in");
        System.out.println("    3. Quit");
        System.out.println();
        String choice = scanner.next();
        switch (choice) {
            case "1":
                displayUserRegistration();
                break;
            case "2":
                displayAccountLogin();
                break;
            case "3":
                System.out.println("Exiting system");
                System.exit(0);
            default:
                System.out.println("Error, please enter 1, 2, or 3 to make your selection");
                displayMainMenu();
        }
    }

    /**
     * Where the user creates an account.
     * If the username and password pass validation, they are
     * added to a list of authenticated users from Authenticator
     */
    private void displayUserRegistration() {
        String username;
        String password;
        while (true) {
            System.out.println("Please choose a username: ");
            Scanner scanner = new Scanner(System.in);
            username = scanner.next();
            System.out.println("Please choose a password: ");
            String firstPassword = scanner.next();
            System.out.println("Type your password again to confirm");
            String secondPassword = scanner.next();
            if (!firstPassword.equals(secondPassword)) {
                System.out.println("Error, passwords do not match.");
            } else {
                password = secondPassword;
                break;
            }
        }
        User user = new User(username, password);
        authenticator.setAuthenticated(user);
        System.out.println("Account created! Now you can login");
        displayAccountLogin();
    }

    /**
     * Called when user wishes to create a new account.
     * Allows user to choose the account type and will then call
     * some account builder functionality to create said accounts.
     */
    private void displayAccountCreation(User user) {
        System.out.println("Please select the type of account you wish to create");
        System.out.println("1. Current Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Children's Account");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice) {
            case "1":
                System.out.println("Enter the name for the current account: ");
                String accountName = scanner.next();
                Account account = new CurrentAccount(0, false, user, accountName);
                user.addAccount(account);
                displayAccountView(user);
                break;
            case "2":
                System.out.println("Create Savings Account"); // TODO implement
                break;
            case "3":
                System.out.println("Create Children's Account"); // TODO implement
                break;
            default:
                System.out.println("Error, please enter 1, 2 or 3 to choose your account type");
        }
    }


    /**
     * Called when user wishes to log in to their account.
     * Takes in the username and password and will call some
     * authentication class in future to validate the username
     * and password.
     */
    private void displayAccountLogin() {
        System.out.println("Account Login");
        System.out.println("------------");
        System.out.println("Username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();

        User user = new User(username, password);
        if (authenticator.authenticateUser(user)) {
            displayUserHome(user);
        } else {
            System.out.println("Error, there is no account matching those details");
        }
    }

    /**
     * This is the home screen of a logged in user.
     * They can view all of their accounts or they can quit the system
     * @param user the logged in user
     */
    private void displayUserHome(User user) {
        System.out.println("Hello, " + user.getUsername() + "!");
        System.out.println("------------");
        System.out.println("Select an option: ");
        System.out.println("    1. View Accounts");
        System.out.println("    2. Quit");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        switch (choice) {
            case "1":
                displayAccountView(user);
                break;
            case "2":
                System.exit(0);
            default:
                System.out.println("Error, enter 1 or 2 to make your choice");
        }

    }

    /**
     * This lists all the accounts belonging to the logged in user
     * @param user the logged in user
     */
    private void displayAccountView(User user) {
        System.out.println(user.getUsername() + "'s " + "accounts:");
        ArrayList<Account> accountsList = (ArrayList<Account>) user.getAccounts();
        if (accountsList == null || accountsList.isEmpty()) {
            System.out.println("You don't seem to have any accounts, would you like to create an account? ");
            System.out.println("Enter y or n:");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "y":
                    displayAccountCreation(user);
                    break;
                case "n":
                    displayUserHome(user);
                    break;
                default:
                    System.out.println("Error, please enter y or n to make your choice");
            }
        } else {
            for (Account account : user.getAccounts()) {
                System.out.println("Account Name: " + account.getName() + " | " + "Account Balance: " + account.getBalance());
            }
        }

    }
}
