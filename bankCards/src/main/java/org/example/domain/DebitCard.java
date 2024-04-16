package org.example.domain;

public class DebitCard extends BankCard {

     @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public Double getBalance() {
        return balance;
    }

    @Override
    public Double getFundsAvailable() {
        return balance;
    }

    @Override
    public Boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            totalCharges += amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Card #" + id
                + ": {type=" + this.getClass().getSimpleName()
                + ", totalFunds=" + getFundsAvailable()
                + '}';
    }

}
