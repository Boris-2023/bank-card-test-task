package org.example.domain;

public class DebitCardCashBack extends DebitCard {

    private Double cashBackRate;
    private Double cashBackBalance;

    public DebitCardCashBack(Double cashBackRate) {
        super();
        this.cashBackRate = cashBackRate;
        this.cashBackBalance = 0D;
    }

    @Override
    public Boolean pay(double amount) {
        boolean result = super.pay(amount);
        if (result) {
            cashBackBalance = (Math.round((cashBackBalance + amount * cashBackRate) * 100)) / 100D;
        }

        return result;
    }

    public void transferCashBackToCard() {
        balance += cashBackBalance;
        cashBackBalance = 0D;
    }

    public Double getCashBackRate() {
        return cashBackRate;
    }

    public void setCashBackRate(Double cashBackRate) {
        this.cashBackRate = cashBackRate;
    }

    @Override
    public String toString() {
        return "Card #" + id
                + ": {type=" + this.getClass().getSimpleName()
                + ", totalFunds=" + getFundsAvailable()
                + ", cashBackBalance=" + cashBackBalance
                + '}';
    }

}
