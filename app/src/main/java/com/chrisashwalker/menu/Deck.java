package com.chrisashwalker.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Deck {

    private ArrayList<Card> allPossibleCards;
    private ArrayDeque<Card> cards;
    private ArrayList<String> cardTypes = new ArrayList<>(
            Arrays.asList("Blue", "Green", "Indigo", "Navy", "Orange", "Red", "Violet", "Yellow"));
    private int capacity = 40;
    private String bonusType = "Bonus";
    private int bonusValue = 3;
    private int bonusCount = capacity % cardTypes.size();
    private int countOfEachType = (capacity - bonusCount) / cardTypes.size();

    public Deck() {
        build();
        shuffle();
    }

    private void build() {
        allPossibleCards = new ArrayList<>();
        for (int i = 1; i <= countOfEachType; i++) {
            for (int j = 0; j <= cardTypes.size() - 1; j++) {
                allPossibleCards.add(new Card(cardTypes.get(j), i));
            }
        }
        while (allPossibleCards.size() < this.capacity) {
            allPossibleCards.add(new Card(bonusType, bonusValue));
        }
    }

    private void shuffle() {
        Collections.shuffle(allPossibleCards);
        cards = new ArrayDeque<>(allPossibleCards);
    }

    public ArrayDeque<Card> getCards() {
        return cards;
    }

    public void addCard(Card c) {
        cards.offerLast(c);
    }

    public ArrayList<String> getCardTypes() {
        return cardTypes;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public int getCountOfEachType() {
        return countOfEachType;
    }

    public String getBonusType() {
        return bonusType;
    }

    public int getHighestPossibleScore() {
        return getCardTypes().size() * getCountOfEachType() + (getBonusCount() * getBonusValue());
    }

    public int getBonusValue() {
        return bonusValue;
    }

}
