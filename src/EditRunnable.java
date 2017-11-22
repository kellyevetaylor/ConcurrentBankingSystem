public class EditRunnable implements Runnable {

    Account account;
    String newName;

    public EditRunnable(Account accountIn, String name) {
        account = accountIn;
        newName = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread with id " + Thread.currentThread().getId() + ",Editing the  Account Name:" + account.getName() + " changing to: " + newName);
            account.editAccount(newName);
        } finally {
            System.out.println("The account has been edited, the name of the account is now: " + account.getName());
        }
    }
}


