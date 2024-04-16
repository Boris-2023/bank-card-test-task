package org.example.domain;

public abstract class BankCard {

    private static long idCounter;
    protected final long id;
    protected Double balance = 0D;
    protected Double totalCharges = 0D;

    public BankCard() {
        id = ++idCounter;
    }

    public abstract void deposit(double amount);
    public abstract Boolean pay(double amount);
    public abstract Double getBalance();
    public abstract Double getFundsAvailable();


    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

     public Double getTotalCharges() {
        return totalCharges;
    }

}
