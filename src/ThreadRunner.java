
public class ThreadRunner {


    /**
     * Runs the threads
     *
     * @param args
     */
    public static void main(String[] args) {

        User user1 = new User("Andrew", "password1");
        User user2 = new User("Kelly", "password2");


        Account account = new CurrentAccount(10.0, user1, "Account1", 0.0);        //Initial value 10
        Account account1 = new CurrentAccount(10.0,user2,"Account2",0.0);

        user1.addAccount(account);
        user2.addAccount(account1);

        //Runs scenarios

        //scenario1(account);
        //scenario2(account);
        //scenario3(account);
        //scenario4(account, account2);
        //scenario5(account);
        //scenario6(account);
    }

    public static void scenario1(Account account) {
        CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);
        Thread checkBalanceThread1 = new Thread(checkBalance);
        Thread checkBalanceThread2 = new Thread(checkBalance);

        checkBalanceThread1.start();
        checkBalanceThread2.start();
    }


    public static void scenario2(Account account) {
        CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);
        Thread checkBalanceThread = new Thread(checkBalance);

        DepositRunnable deposit = new DepositRunnable(100000.00, account);
        Thread depositThread = new Thread(deposit);

        checkBalanceThread.start();
        depositThread.start();


    }

    public static void scenario3(Account account) {
        //Scenario 3 - 2 account holders simultaneously depositing/withdrawing and checking balance
        CheckBalanceRunnable checkBalance = new CheckBalanceRunnable(account);
        WithdrawRunnable withdraw = new WithdrawRunnable(1000.00, account);
        DepositRunnable deposit = new DepositRunnable(100000.00, account);
        Thread withdrawThread = new Thread(withdraw);

        Thread depositThread = new Thread(deposit);

        depositThread.start();

        withdrawThread.start();


    }

    public static void scenario4(Account account, Account account2) {
        transferRunnable transfer = new transferRunnable(1000.0, account, account2);
        Thread transferThread = new Thread(transfer);
        scenario3(account);
        transferThread.start();
    }

    public static void scenario5(Account account) {

    }

    public static void scenario6(Account account1, Account account2){

    }

}

