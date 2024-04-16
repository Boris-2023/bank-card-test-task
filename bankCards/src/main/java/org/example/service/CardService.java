package org.example.service;

import org.example.domain.BankCard;
import org.example.domain.CreditCardBonus;
import org.example.domain.DebitCardCashBack;

import java.util.List;

public class CardService {

    /**
     * prints all cards into console
     * @param cards List of cards to print
     * @see CardService#printAllCards(List) 
     */
    public static void printAllCards(List<BankCard> cards) {
        System.out.println("\nСписок карт:");
        cards.forEach(System.out::println);
    }

    /**
     * performs a card transaction with simple logging into console  
     * @param card card to pay with
     * @param amount amount to pay
     * @return result whether the transaction was successful (true/false)
     * @see CardService#cardTransaction(BankCard, double) 
     */
    public static boolean cardTransaction(BankCard card, double amount) {
        System.out.println("\nОперация по карте #" + card.getId() + " на сумму " + amount + ": ");
        System.out.println(card);
        boolean result = card.pay(amount);
        System.out.println("Подтверждение: Оплата " + (result ? "прошла успешно" : "отклонена") + "!");
        System.out.println(card);

        return result;
    }

    /**
     * performs a deposit to card account with simple logging into console  
     * @param card card to deposit to
     * @param amount amount to deposit
     * @see CardService#cardDeposit(BankCard, double)
     */    
    public static void cardDeposit(BankCard card, double amount) {
        System.out.println("\n" + card);
        System.out.println("Внесение средств на карту #" + card.getId() + " на сумму " + amount + ": ");
        card.deposit(amount);
        System.out.println(card);
    }


    /**
     * transfers bonuses to eligible card account with simple logging into console  
     * @param card card to operate
     * @see CardService#transferBonusesToCardBalance(BankCard)
     */
    public static void transferBonusesToCardBalance(BankCard card) {
        if (!card.getClass().getSimpleName().equals("CreditCardBonus")) {
            System.out.println("\nКарта #" + card.getId() + " не предусматривает бонусную программу!");
            return;
        }
        System.out.println("\n" + card);
        System.out.println("Зачисление бонусов на баланс карты #" + card.getId()
                + " по курсу " + ((CreditCardBonus) card).getBonusRedeemRate()
                + ": ");
        ((CreditCardBonus) card).redeemBonusesToCard();
        System.out.println(card);
    }

    /**
     * transfers cashback to eligible card account with simple logging into console  
     * @param card card to operate
     * @see CardService#transferCashBackToCardBalance(BankCard) 
     */
    public static void transferCashBackToCardBalance(BankCard card) {
        if (!card.getClass().getSimpleName().equals("DebitCardCashBack")) {
            System.out.println("\nКарта #" + card.getId() + " не предусматривает Cash Back!");
            return;
        }
        System.out.println("\n" + card);
        System.out.println("Зачисление CashBack на баланс карты #" + card.getId() + ": ");
        ((DebitCardCashBack) card).transferCashBackToCard();
        System.out.println(card);
    }

}
