import java.util.concurrent.locks.ReentrantLock;

public class EditRunnable implements Runnable {

    Account account;
    String newName;
    private static final int DELAY = 1;
    private ReentrantLock lock =new ReentrantLock();

    public EditRunnable(Account accountIn,String name){
        account = accountIn;
        newName = name;

    }

    @Override
    public void run() {
        lock.lock();
        try {
            account.EditAccount(newName);
            System.out.println("Thread with id " + Thread.currentThread().getId() + ",Editing the  Account Name:" + account.getName());
        } finally {

            System.out.println("The account has been edited, the name of the account is now:" + newName);
            lock.unlock();
        }
    }


    }


