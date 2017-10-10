import java.util.Scanner;

class UserInterface {
    /**
     * Displays the main menu and allows the user to
     * make a selection of what they would like to do
     */
    void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome! Please select an option:");
            System.out.println();
            System.out.println("    1. Create a new account");
            System.out.println("    2. Log in to manage your account");
            System.out.println("    3. Quit");
            System.out.println();
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    displayAccountCreation();
                    break;
                case "2":
                    displayAccountLogin();
                    break;
                case "3":
                    System.out.println("Exiting system");
                    System.exit(0);
                default:
                    System.out.println("Error, please enter 1, 2, or 3 to make your selection");
            }
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
        System.out.println("Username is: " + username);
        System.out.println("Password: ");
        String password = scanner.next();
        System.out.println("Password is: " + password);
    }

    /**
     * Called when user wishes to create a new account.
     * Allows user to choose the account type and will then call
     * some account builder functionality to create said accounts.
     */
    private void displayAccountCreation() {
        System.out.println("Create New Account");
        System.out.println("Please select the type of account you wish to create");
        System.out.println("1. Current Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Children's Account");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice) {
            case "1":
                System.out.println("Create Current Account");
                break;
            case "2":
                System.out.println("Create Savings Account");
                break;
            case "3":
                System.out.println("Create Children's Account");
                break;
            default:
                System.out.println("Error, please enter 1, 2 or 3 to choose your account type");
        }
    }
}
