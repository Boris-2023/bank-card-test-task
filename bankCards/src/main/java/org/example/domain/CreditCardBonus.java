package org.example.domain;

public class CreditCardBonus extends CreditCard {

    private final double BASIC_BONUS_ACCRUE_RATE = .02;
    private final double EXTRA_BONUS_ACCRUE_RATE = .05;
    private final double BONUS_REDEEM_RATE = .8;
    private Double bonusAccrueRate;
    private Double extraBonusChargesLevel;
    private Double bonusBalance;


    public CreditCardBonus(Double creditLine, Double extraBonusChargesLevel) {
        super(creditLine);
        this.bonusAccrueRate = BASIC_BONUS_ACCRUE_RATE;
        this.extraBonusChargesLevel = extraBonusChargesLevel;
        this.bonusBalance = 0D;
    }

    @Override
    public Boolean pay(double amount) {
        boolean result = super.pay(amount);
        if (result) {
            bonusBalance = (Math.round((bonusBalance + amount * bonusAccrueRate) * 100)) / 100D;
            if (totalCharges >= extraBonusChargesLevel) {
                bonusAccrueRate = EXTRA_BONUS_ACCRUE_RATE;
            }
        }
        return result;
    }

    public void redeemBonusesToCard() {
        double amountToRedeem = (Math.round(bonusBalance * BONUS_REDEEM_RATE * 100)) / 100D;
        deposit(amountToRedeem);
        bonusBalance = 0D;
    }

    public Double getBonusRedeemRate() {
        return BONUS_REDEEM_RATE;
    }

    public Double getBonusAccrueRate() {
        return bonusAccrueRate;
    }

    public Double getExtraBonusChargesLevel() {
        return extraBonusChargesLevel;
    }

    public Double getBonusBalance() {
        return bonusBalance;
    }

    public void setExtraBonusChargesLevel(Double extraBonusChargesLevel) {
        this.extraBonusChargesLevel = extraBonusChargesLevel;
    }

    @Override
    public String toString() {
        return "Card #" + id
                + ": {type=" + this.getClass().getSimpleName()
                + ", ownFunds=" + balance
                + ", creditLimit=" + creditLimit
                + ", totalFunds=" + getFundsAvailable()
                + ", creditLine=" + creditLine
                + ", bonuses=" + bonusBalance
                + '}';
    }
}
