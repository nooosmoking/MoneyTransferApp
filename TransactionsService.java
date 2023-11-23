package ex05;

import java.util.UUID;

public class TransactionsService {
    private static final UsersArrayList userArrayList = new UsersArrayList();

    public int addUser(String name, Integer balance){
        User newUser = new User(name, balance);
        userArrayList.addUser(newUser);
        return newUser.getIdentifier();
    }

    public int addUser(User user){
        userArrayList.addUser(user);
        return user.getIdentifier();}


    public int retryBalance(int id){
        return userArrayList.getUserById(id).getBalance();
    }

    public int retryBalance(User user){
        return user.getBalance();
    }

    public void makeTransaction(int senderID, int  receiverID, int amount) {
        User sender = userArrayList.getUserById(senderID);
        int senderBalance = sender.getBalance();
        if(senderBalance < amount)
            throw new IllegalTransactionException("Sender is not able to send" + amount + "RUB");
        User receiver =userArrayList.getUserById(receiverID);
        int receiverBalance = receiver.getBalance();

        Transaction senderTransaction = new Transaction(sender, receiver, Transaction.Category.CREDIT, amount);
        Transaction recieverTransaction  = new Transaction(receiver, sender, Transaction.Category.DEBIT, amount);
        recieverTransaction.setIdentifier(senderTransaction.getIdentifier());

        sender.getTransactionsList().addTransaction(senderTransaction);
        receiver.getTransactionsList().addTransaction(recieverTransaction);

        sender.setBalance(senderBalance - amount);
        receiver.setBalance(receiverBalance + amount);
    }

    public Transaction[] getTransactionArrayOfUser(int userID){
        return userArrayList.getUserById(userID).getTransactionsList().toArray();
    }

    public TransactionsLinkedList getTransactionListOfUser(int userID){
        return userArrayList.getUserById(userID).getTransactionsList();
    }

    private Transaction[] getAllTransactions(){
        Transaction[] allTransArray = new Transaction[0];
        for (int i = 0; i < userArrayList.getUserCount(); i++) {
            int countUserTransactions = userArrayList.getUserByIndex(i).getTransactionsList().getSize();
            Transaction[] newArray = new Transaction[countUserTransactions + allTransArray.length];
            System.arraycopy(allTransArray, 0, newArray, 0, allTransArray.length);
            System.arraycopy(userArrayList.getUserByIndex(i).getTransactionsList().toArray(), 0, newArray, allTransArray.length, countUserTransactions);
            allTransArray = newArray;
        }
        return allTransArray;
    }

    private Transaction[] addTransInArray(Transaction[] array, Transaction transaction){
        Transaction[] newArray = new Transaction[(array.length)+1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = transaction;
        return newArray;
    }

    public void removeTransaction(String idTrans, int idUser ){
        userArrayList.getUserById(idUser).getTransactionsList().removeTransactionByID(idTrans);
    }

    public Transaction[] getUnpairTransactions(){
        Transaction[] unpairTransArray = new Transaction[0];
        Transaction[] allTransArray = getAllTransactions();

        for (int i = 0; i < allTransArray.length; i++) {
            boolean findCopy = false;
            for (int j = 0; j < allTransArray.length ; j++) {
                if(allTransArray[i].getIdentifier() == allTransArray[j].getIdentifier()  && i != j) {
                    findCopy = true;
                    break;
                }
            }
            if (!findCopy)
                unpairTransArray = addTransInArray(unpairTransArray, allTransArray[i]);
        }
        return unpairTransArray;
    }
}
