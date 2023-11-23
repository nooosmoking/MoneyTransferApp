package ex05;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction curr);
    void removeTransactionByID(String id);
    Transaction[] toArray();
}
