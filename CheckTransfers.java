package ex05;

import java.util.UUID;

public class CheckTransfers implements Command{
    @Override
    public void run(){
        System.out.println("Check results:");
        Menu.service.getUnpairTransactions();
        Transaction[] unpairTransactions = Menu.service.getUnpairTransactions();
        if (unpairTransactions.length != 0){
        for (Transaction transaction: unpairTransactions
        ) {
            User sender = (transaction.getTransferAmount() > 0)?transaction.getSender():transaction.getRecipient();
            User recipient = (transaction.getTransferAmount() > 0)?transaction.getRecipient() : transaction.getSender();
            String prefix = (transaction.getTransferAmount() > 0)? "From " : "To ";
            System.out.println(recipient.getName() + "(id = " + recipient.getIdentifier() + ") has an unacknowledged transfer id = " + transaction.getIdentifier() +" " + prefix + sender.getName() + "(id = " + sender.getIdentifier() +") for " + transaction.getTransferAmount());
        }}
        else {
            System.out.println("No unacknowledged transfers");
        }
    }
}
