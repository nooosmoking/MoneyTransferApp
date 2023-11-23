package ex05;

import java.util.UUID;

public class User {
    private final Integer id;
    private String name;
    private Integer balance;
    protected TransactionsLinkedList transactionsList;

    public User(String name, Integer balance){
        this.name = name;
        if (balance > 0)
            this.balance = balance;
        else
            this.balance = 0;
        this.id = UserIdsGenerator.getInstance().generateId();
        this.transactionsList = new TransactionsLinkedList();
    }

    public void setBalance(Integer balance){
        if (balance > 0)
            this.balance = balance;
        else
            this.balance = 0;
    }

    public Integer getBalance(){
        return this.balance;
    }

    public String getName(){
        return this.name;
    }

    public Integer getIdentifier(){
        return this.id;
    }

    public TransactionsLinkedList getTransactionsList() {
        return transactionsList;
    }

    @Override
    public String toString(){
        return "name = " + name +
                ", identifier = " + id +
                ", balance = " + balance;
        }
    }
