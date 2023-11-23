package ex05;

import java.util.*;
public class Menu {
    protected static TransactionsService service ;
    protected static Scanner in;
    private static Map<Integer, Command> commandMap;

    public Menu(){
        service = new TransactionsService();
        commandMap = new HashMap<>();
        in = new Scanner(System.in);
        commandMap.put(1, new AddUser());
        commandMap.put(2, new ViewBalance());
        commandMap.put(3, new MakeTransfer());
        commandMap.put(4, new ViewTransactions());
        commandMap.put(5, new RemoveTransfer());
        commandMap.put(6, new CheckTransfers());
    }

    private void incorrectAnswer(){
        System.out.println("Incorrect answer, please choose number between 1 and 7");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void notDevAnswer(){
        System.out.println("You`re not a developer!");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean work(boolean isDev){
        printMenu();
        String answerStr = in.nextLine().trim();
        int answerInt = 0;
        try {
            answerInt = Integer.parseInt(answerStr);
        } catch (RuntimeException ex) {
        }
        boolean isWork = true;
            if (answerInt <= 0 || answerInt > 7)
                incorrectAnswer();
            else if (answerInt == 7)
                isWork = false;
            else if (!isDev && (answerInt == 5 || answerInt == 6))
                notDevAnswer();
            else
                doCommand(answerInt);
        return isWork;
    }

    private void printMenu(){
        System.out.println("1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n" +
                "5. DEV – remove a transfer by ID\n" +
                "6. DEV – check transfer validity\n" +
                "7. Finish execution");
    }

    private void doCommand(int answer) {
        commandMap.get(answer).run();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------");
    }
}
