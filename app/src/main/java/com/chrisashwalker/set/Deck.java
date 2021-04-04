package com.chrisashwalker.set;

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
    private HashMap<String, Integer> cardDrawables;
    private int capacity;
    private String bonusType = "Bonus";
    private int bonusValue = 3;
    private int bonusCount;
    private int countOfEachType;

    public Deck(int capacity) {
        setCapacity(capacity);
        initialiseBackgrounds();
        initialiseDrawables();
        build();
        shuffle();
    }

    private void build() {
        allPossibleCards = new ArrayList<>();
        for (int i = 0; i <= cardTypes.size() - 1; i++) {
            for (int j = 1; j <= countOfEachType; j++) {
                allPossibleCards.add(new Card(cardTypes.get(i), j));
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

    public void absorbHand (Hand hand) {
        for (Card c : hand.getCards()) {
            addCard(c);
        }
        for (Card c : hand.getBonuses()) {
            addCard(c);
        }
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

    public void initialiseDrawables() {
        cardDrawables = new HashMap<>();
        cardDrawables.put("Red", R.drawable.card_border_red);
        cardDrawables.put("Orange", R.drawable.card_border_orange);
        cardDrawables.put("Yellow", R.drawable.card_border_yellow);
        cardDrawables.put("Green", R.drawable.card_border_green);
        cardDrawables.put("LightBlue", R.drawable.card_border_light_blue);
        cardDrawables.put("Blue", R.drawable.card_border_blue);
        cardDrawables.put("Navy", R.drawable.card_border_navy);
        cardDrawables.put("Violet", R.drawable.card_border_violet);
    }

    public Integer getCardBackgrounds(Card c) {
        return cardBackgrounds.get(c.getType());
    }

    public Integer getCardDrawables(Card c) {
        return cardDrawables.get(c.getType());
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        bonusCount = capacity % cardTypes.size();
        countOfEachType = (capacity - bonusCount) / cardTypes.size();
    }
    
}
