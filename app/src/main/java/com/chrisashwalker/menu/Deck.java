package com.chrisashwalker.menu;

import java.util.Collections;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Deck {
    String[] types = {"Blue", "Green", "Indigo", "Navy", "Orange", "Red", "Violet", "Yellow"};
    int size = 40;
    int bonuses = size % types.length;
    int bonusValue = 3;
    int countOfEachType = (size - bonuses) / types.length;
    static ArrayList<Card> allCards;
    ArrayDeque<Card> cards;

    public Deck() {
        build();
        shuffle();
    }

    private void build() {
        allCards = new ArrayList<>();
        for (int i = 1; i <= countOfEachType; i++) {
            for (int j = 1; j <= types.length - 1; j++) {
                allCards.add(new Card(types[j], i));
            }
        }
        while (allCards.size() < this.size) {
            allCards.add(new Card("Bonus", bonusValue));
        }
    }

    private void shuffle() {
        Collections.shuffle(allCards);
        cards = new ArrayDeque<>(allCards);
    }
}
