package ex05;

import java.util.UUID;

public class RemoveTransfer implements Command{
    @Override
    public void run(){
        System.out.println("Enter a user ID and a transfer ID");
        String answerStr = Menu.in.nextLine().trim();
        String[] answers = answerStr.split(" ");
        if(answers.length == 2){
            try {
                int userID = Integer.parseInt(answers[0]);
                String transactionID = answers[1];
                TransactionsLinkedList list =  Menu.service.getTransactionListOfUser(userID);
                Transaction transaction = list.findTransactionByID(transactionID);
                User recipient = (transaction.getTransferAmount() > 0)?transaction.getRecipient() : transaction.getSender();
                String prefix = (transaction.getTransferAmount() > 0)? "From " : "To ";
                Menu.service.removeTransaction(transactionID, userID);
                System.out.println("Transfer " + prefix + recipient.getName() + "(id = " + recipient.getIdentifier() + ") " + transaction.getTransferAmount() + " removed");
            } catch (RuntimeException ex) {
                System.out.println("Incorrect parameters!");
            }
        } else {
            System.out.println("You should write user ID and a transfer ID!");
        }
    }
}
