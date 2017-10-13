import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.Scanner;

class UserInterface {
    private Authenticator authenticator = new Authenticator();

    /**
     * Displays the main menu and allows the user to
     * make a selection of what they would like to do
     */
    void displayMainMenu() {
        sectionDivider();
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
        sectionDivider();
        System.out.println("    User Registration");
        System.out.println();
        String password;
        System.out.println("Please choose a username: ");
        String username = getUserInput();
        while (true) {
            System.out.println("Please choose a password: ");
            String firstPassword = getUserInput();
            System.out.println("Type your password again to confirm");
            String secondPassword = getUserInput();
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
        sectionDivider();
        System.out.println("Please select the type of account you wish to create");
        System.out.println("1. Current Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Children's Account");
        String choice = getUserInput();
        String accountName;
        switch (choice) {
            case "1":
                System.out.println("Enter the name for the current account: ");
                accountName = getUserInput();
                Account currentAcc = new CurrentAccount(0, false, user, accountName);
                user.addAccount(currentAcc);
                displayAccountView(user);
                break;

            case "2":
                System.out.println("Enter the name for the savings account");
                accountName = getUserInput();
                Account savingsAcc = new SavingsAccount(0,false, user, accountName);
                user.addAccount(savingsAcc);
                displayAccountView(user);
                break;

            case "3":
                System.out.println("Enter the name for the kids account");
                accountName = getUserInput();
                System.out.println("Please enter the maximum withdrawal amount for " + accountName);
                Scanner scanner = new Scanner(System.in);
                Double max = scanner.nextDouble();
                Account kidsAcc = new KidsAccount(0, true, user, accountName, max);
                user.addAccount(kidsAcc);
                displayAccountView(user);
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
        sectionDivider();
        System.out.println("    Account Login");
        System.out.println();
        System.out.println("Type \"quit\" to exit");
        System.out.println("Type \"return\" to go back to the main menu");
        System.out.println();
        System.out.println("Username: ");
        String username = getUserInput();
        if (username.equals("quit")) System.exit(0);
        if (username.equals("return")) displayMainMenu();
        System.out.println("Password: ");
        String password = getUserInput();

        User user = new User(username, password);
        if (authenticator.authenticateUser(user)) {
            displayUserHome(user);
        } else {
            System.out.println("Error, there is no account matching those details");
            displayAccountLogin();
        }
    }


    /**
     * This is the home screen of a logged in user.
     * They can view all of their accounts or they can quit the system
     *
     * @param user the logged in user
     */
    private void displayUserHome(User user) {
        sectionDivider();
        System.out.println("Hello, " + user.getUsername() + "!");
        System.out.println("------------");
        System.out.println("Select an option: ");
        System.out.println("    1. View Accounts");
        System.out.println("    2. Quit");

        String choice = getUserInput();
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
        sectionDivider();
        System.out.println(user.getUsername() + "'s " + "accounts:");
        ArrayList<Account> accountsList = (ArrayList<Account>) user.getAccounts();
        if (accountsList == null || accountsList.isEmpty()) { // user has no accounts
            System.out.println("You don't seem to have any accounts, would you like to create an account? ");
            System.out.println("Enter y or n:");
            String choice = getUserInput();
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
        } else { // user does have accounts
            for (Account account : user.getAccounts()) {
                System.out.println("Account Name: " + account.getName() + " | " + "Account Balance: " + "£" + account.getBalance());
                System.out.println("Would you like to add money to you're account?"); // TODO remove from for loop
                System.out.println("Enter y or n:");
                Scanner scanner = new Scanner(System.in);
                String choice = getUserInput();
                switch (choice) {
                    case "y":
                        System.out.println("Please enter an amount to add:");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                        System.out.println("Account Name: " + account.getName() + " | " + "Account Balance: " + "£" + account.getBalance());
                        displayAccountView(user);
                        break;
                    case "n":
                        displayUserHome(user);
                        break;
                    default:
                        System.out.println("Error, please enter y or n to make your choice");
                }
            }
        }
    }

    /**
     * Helper method so we don't have to write the scanner code every time
     * @return the return value of scanner.next()
     */
    private String getUserInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    /**
     * Helper method so we can have consistent dividers between UI sections
     */
    private void sectionDivider() {
        System.out.println();
        System.out.println("------------------------");
        System.out.println();
    }
}
