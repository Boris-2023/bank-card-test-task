package org.example.repository;

import org.example.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CardRepository {

    private List<BankCard> cards = new ArrayList<>();

    public CardRepository() {
        setInitialStock();
    }

    private void setInitialStock() {

        addCard(new DebitCard());
        addCard(new DebitCardCashBack(.02));
        addCard(new CreditCard(15000D));
        addCard(new CreditCardBonus(20000D, 5000D));

        cards.forEach(x -> x.setBalance((double) ((ThreadLocalRandom.current().nextInt(5000, 10000)) / 1000) * 1000));

        // increase own funds to Debit cards - for better transaction performance in controller
        cards.stream()
                .filter(x -> x.getClass().getSimpleName().contains("DebitCard"))
                .forEach(x -> x.setBalance(x.getBalance() * 2));
    }

    public List<BankCard> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

    public BankCard getCardById(long id) {
        return cards.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addCard(BankCard card) {
        cards.add(card);
    }

}
