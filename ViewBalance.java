package ex05;

public class ViewBalance implements Command{
    @Override
    public void run() {
        System.out.println("Enter a user ID");

        String answerStr = Menu.in.nextLine().trim();
        int answerId;
        try {
            answerId = Integer.parseInt(answerStr);
            System.out.println(Menu.service.retryBalance(answerId));
        } catch (RuntimeException ex) {
            System.out.println("Incorrect ID!");
        }

    }
}
