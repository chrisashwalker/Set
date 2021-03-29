package com.chrisashwalker.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Deck {

    private ArrayList<Card> allPossibleCards;
    private ArrayDeque<Card> cards;
    private ArrayList<String> cardTypes = new ArrayList<>(
            Arrays.asList("Red", "Orange", "Yellow", "Green", "LightBlue", "Blue", "Navy", "Violet"));
    private HashMap<String, Integer> cardBackgrounds;
    private int capacity = 40;
    private String bonusType = "Bonus";
    private int bonusValue = 3;
    private int bonusCount = capacity % cardTypes.size();
    private int countOfEachType = (capacity - bonusCount) / cardTypes.size();

    public Deck() {
        initialiseBackgrounds();
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

    public void initialiseBackgrounds() {
        cardBackgrounds = new HashMap<>();
        cardBackgrounds.put("Red", R.color.colorRed);
        cardBackgrounds.put("Orange", R.color.colorOrange);
        cardBackgrounds.put("Yellow", R.color.colorYellow);
        cardBackgrounds.put("Green", R.color.colorGreen);
        cardBackgrounds.put("LightBlue", R.color.colorLightBlue);
        cardBackgrounds.put("Blue", R.color.colorBlue);
        cardBackgrounds.put("Navy", R.color.colorNavy);
        cardBackgrounds.put("Violet", R.color.colorViolet);
    }

    public Integer getCardBackgrounds(Card c) {
        return cardBackgrounds.get(c.getType());
    }

}
