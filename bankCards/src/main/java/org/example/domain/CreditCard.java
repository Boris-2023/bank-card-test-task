package org.example.domain;

public class CreditCard extends BankCard {
    protected Double creditLine;
    protected Double creditLimit;

    public CreditCard(Double creditLine) {
        this.creditLine = creditLine;
        this.creditLimit = creditLine;
    }

    public Double getCreditLine() {
        return creditLine;
    }

    public Boolean setNewCreditLine(Double creditLine) {
        double newCreditBalance = creditLimit + creditLine - this.creditLine;
        double newDebitBalance = this.balance;

        if (newCreditBalance < 0) {
            // unable to decrease credit line so much
            if ((newCreditBalance + newDebitBalance) < 0) {
                System.out.println("Ошибка установки кредитной линии, мин. значение должно быть на сумму "
                        + (-newCreditBalance - newDebitBalance) + " выше!");
                return false;
            }

            newDebitBalance += newCreditBalance;
            newCreditBalance = 0;
            balance= newDebitBalance;
        }

        this.creditLine = creditLine;
        this.creditLimit = newCreditBalance;

        return true;
    }

    @Override
    public Double getBalance() {
        return balance;
    }

    @Override
    public Double getFundsAvailable() {
        return balance + creditLimit;
    }

    @Override
    public Boolean pay(double amount) {
        if (getFundsAvailable() < amount) {
            return false;
        }

        double newBalance = balance - amount;
        if (newBalance < 0) {
            creditLimit += newBalance;
            newBalance = 0D;
        }
        balance = newBalance;
        totalCharges += amount;

        return true;
    }

    @Override
    public void deposit(double amount) {
        creditLimit += amount;
        if (creditLimit > creditLine) {
            balance += (creditLimit - creditLine);
            creditLimit = creditLine;
        }
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "Card #" + id
                + ": {type=" + this.getClass().getSimpleName()
                + ", ownFunds=" + balance
                + ", creditLimit=" + creditLimit
                + ", totalFunds=" + getFundsAvailable()
                + ", creditLine=" + creditLine
                + '}';
    }

}