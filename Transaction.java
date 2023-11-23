package ex05;

import java.util.UUID;

public class Transaction {
    public enum Category {DEBIT, CREDIT};

    private UUID identifier;
    private final User recipient;
    private final User sender;
    private final Category transferCategory;
    private Integer transferAmount;

    public Transaction(User recipient, User sender, Category transferCategory, Integer transferAmount){
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        setTransferAmount(transferAmount);
    }

    private void setUserBalances(){
        if (this.transferCategory == Category.CREDIT){
            recipient.setBalance(recipient.getBalance() - this.transferAmount);
            sender.setBalance(sender.getBalance() + this.transferAmount);
        } else {
            recipient.setBalance(recipient.getBalance() + this.transferAmount);
            sender.setBalance(sender.getBalance() - this.transferAmount);
        }
    }

    private void setTransferAmount(Integer transferAmount){
            this.transferAmount = transferAmount;
        setUserBalances();
    }

    public int getTransferAmount(){
        return (this.transferCategory == Category.DEBIT) ? this.transferAmount : -1 * this.transferAmount;
    }


    public UUID getIdentifier(){
        return this.identifier;
    }

    protected void setIdentifier(UUID id){
        this.identifier = id;
    }

    public User getRecipient(){return this.recipient;}

    public User getSender(){return this.sender;}

    @Override
    public String toString(){
        return "identifier = " + identifier +
                ", recipient = " + recipient.getName() +
                ", sender = " + sender.getName() + ", transfer category = " + transferCategory + ", transfer amount = " + transferAmount;
    }
}

