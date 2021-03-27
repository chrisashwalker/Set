package com.chrisashwalker.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Deck {
    final ArrayList<String> types = new ArrayList<>(Arrays.asList("Blue", "Green", "Indigo", "Navy", "Orange", "Red", "Violet", "Yellow"));
    int size = 40;
    int bonuses = size % types.size();
    int bonusValue = 3;
    int countOfEachType = (size - bonuses) / types.size();
    String bonusType = "Bonus";
    static ArrayList<Card> allCards;
    ArrayDeque<Card> cards;

    public Deck() {
        build();
        shuffle();
    }

    private void build() {
        allCards = new ArrayList<>();
        for (int i = 1; i <= countOfEachType; i++) {
            for (int j = 1; j <= types.size() - 1; j++) {
                allCards.add(new Card(types.get(j), i));
            }
        }
        while (allCards.size() < this.size) {
            allCards.add(new Card(bonusType, bonusValue));
        }
    }

    private void shuffle() {
        Collections.shuffle(allCards);
        cards = new ArrayDeque<>(allCards);
    }
}
