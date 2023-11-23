package ex05;
import java.util.*;

public class TransactionsLinkedList implements TransactionsList{
    private Node first = null, last = null;
    private int size = 0;
    private class Node {
        Transaction transaction;
        Node prev;
        Node next;
        public Node(Transaction curr, Node prev, Node next){
            this.transaction = curr;
            this.prev = prev;
            this.next = next;
        }
    }
    @Override
    public void addTransaction(Transaction curr){
        Node transactionNode;
        transactionNode  = new Node(curr, last, null);
        if(this.size == 0)
            this.first = transactionNode;
        else
            last.next = transactionNode;
        this.last = transactionNode;
        size++;
    }

    public Transaction findTransactionByID(String id){
        Node currentNode = this.first;
        for (int i = 0; i < this.size; i++) {
            if (currentNode.transaction.getIdentifier().toString().equals( id)) {
                break;
            }
            if(currentNode.next == null)
                throw new TransactionNotFoundException("Transaction not found");
            currentNode = currentNode.next;
        }
        return currentNode.transaction;
    }

    @Override
    public void removeTransactionByID(String id){
        Node currentNode = this.first;
        for (int i = 0; i < this.size; i++) {
            if (currentNode.transaction.getIdentifier().toString().equals( id)) {
                removeTransaction(currentNode);
                break;
            }
            if(currentNode.next == null)
                throw new TransactionNotFoundException("Transaction not found");
            currentNode = currentNode.next;
        }
        size--;
    }

    private void removeTransaction(Node deleteNode){
        if(size == 1) {
            first = null;
            last = null;
        }else if (first ==deleteNode){
            first = deleteNode.next;
            first.prev = null;
        } else if (last ==deleteNode){
            last = deleteNode.prev;
            last.next = null;
        } else {
            deleteNode.prev.next = deleteNode.next;
            deleteNode.next.prev = deleteNode.prev;
        }
    }
    @Override
    public Transaction[] toArray(){
        Transaction[] transactionsArray = new Transaction[size];
        Node curr = first;
        for (int i = 0; i < size; i++) {
            transactionsArray[i] = curr.transaction;
            curr = curr.next;
        }
        return transactionsArray;
    }

    @Override
    public String toString(){
        if (size == 0)
            return "List is empty!";
        StringBuilder usersString = new StringBuilder();
        Node currentNode = this.first;
        for (int i = 0; i < this.size; i++) {
            usersString.append(currentNode.transaction.toString());
            usersString.append("\n");
            currentNode = currentNode.next;
        }
        return usersString.toString();
    }

    public UUID[] getIDArray(){
        Node curr = first;
        UUID[] arrayID = new UUID[4];
        for (int i = 0; i < 4; i++) {
            arrayID[i] = curr.transaction.getIdentifier();
            curr = curr.next;
        }
        return arrayID;
    }

    public int getSize(){
        return size;
    }
}

