package ex05;

import java.util.Arrays;

public class ViewTransactions implements Command{
    @Override
    public void run(){
    System.out.println("Enter a user ID");
    String answerStr = Menu.in.nextLine().trim();
    String[] answers = answerStr.split(" ");
        if(answers.length == 1){
        try {
            int id = Integer.parseInt(answers[0]);
            Transaction[] userTransactions = Menu.service.getTransactionArrayOfUser(id);
            for (Transaction transaction: userTransactions
                 ) {
                User sender = transaction.getSender() ;
                String prefix = (transaction.getTransferAmount() > 0)? "From " : "To ";
                System.out.println(prefix + sender.getName() + "(id = " + sender.getIdentifier() + ") " + transaction.getTransferAmount() + " with id = " + transaction.getIdentifier());
            }
        } catch (RuntimeException ex) {
            System.out.println("Incorrect ID!");
        }
    } else {
        System.out.println("You should write a user ID!");
    }}
}
