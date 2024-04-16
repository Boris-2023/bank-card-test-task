package org.example.controller;

import org.example.repository.CardRepository;
import org.example.service.CardService;

import java.util.Random;

public class CardController {
    CardRepository repository;

    Random rnd = new Random();

    public CardController() {
        this.repository = new CardRepository();
    }

    public void run() {

        // список всех карт
        CardService.printAllCards(repository.getAllCards());

        // оплата по всем картам из репозитория на случайную сумму
        repository.getAllCards().forEach(x -> CardService.cardTransaction(x, rnd.nextInt(5000, 15000)));

        // внесние ден. средств на все карты репозитория на случайную сумму
        repository.getAllCards().forEach(x -> CardService.cardDeposit(x, rnd.nextInt(10000, 20000)));

        // зачисление cash-back на карты с опцией CashBack
        repository.getAllCards().stream()
                .filter(x -> x.getClass().getSimpleName().contains("CashBack"))
                .forEach(CardService::transferCashBackToCardBalance);

        // зачисление бонусов на карты с опцией Bonus
        repository.getAllCards().stream()
                .filter(x -> x.getClass().getSimpleName().contains("Bonus"))
                .forEach(CardService::transferBonusesToCardBalance);
    }

}
