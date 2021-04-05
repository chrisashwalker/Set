package com.chrisashwalker.set;

import java.util.ArrayList;
import java.util.TreeMap;

public class Hand {

    private int capacity = 8;
    private ArrayList<Card> cards;
    private ArrayList<Card> bonuses;

    public Hand(Deck deck) {
        deal(deck);
    }

    public void deal(Deck deck) {
        cards = new ArrayList<>();
        bonuses = new ArrayList<>();
        while (cards.size() < this.capacity) {
            draw(deck);
        }
    }

    private void draw(Deck deck) {
        Card drawnCard = deck.getCards().pollFirst();
        if (drawnCard != null && drawnCard.getType().equals(deck.getBonusType())) {
            addBonus(drawnCard);
        } else {
            addCard(drawnCard);
        }
    }

    public void sort() {
        TreeMap<Integer, Card> sortIndexes = new TreeMap<>();
        for (Card c : cards) {
            sortIndexes.put(c.getId(), c);
        }
        cards = new ArrayList<>(sortIndexes.values());
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public boolean hasBonuses() {
        return !bonuses.isEmpty();
    }

    public ArrayList<Card> getBonuses() {
        return bonuses;
    }

    public void addBonus(Card bonus) {
        bonuses.add(bonus);
    }

    public void removeBonus(Card bonus) {
        bonuses.remove(bonus);
    }

    public int getBonusScore() {
        int bonusScore = 0;
        for (Card b : bonuses) {
            bonusScore += b.getValue();
        }
        return bonusScore;
    }

    public int getTotalScore() {
        int score = 0;
        ArrayList<Card> scoringCards = new ArrayList<>(getCards());
        while (findLowestDuplicateCard(scoringCards) != null) {
            scoringCards.remove(findLowestDuplicateCard(scoringCards));
        }
        for (Card c : scoringCards) {
            score += c.getValue();
        }
        return score + getBonusScore();
    }

    private ArrayList<String> findDuplicateCardTypes() {
        return findDuplicateCardTypes(getCards());
    }

    private static ArrayList<String> findDuplicateCardTypes(ArrayList<Card> cards) {
        ArrayList<String> foundTypes = new ArrayList<>();
        ArrayList<String> duplicateTypes = new ArrayList<>();
        for (Card c : cards) {
            if (!foundTypes.contains(c.getType())) {
                foundTypes.add(c.getType());
            } else {
                duplicateTypes.add(c.getType());
            }
        }
        return duplicateTypes;
    }

    public Card findLowestValueCard() {
        return findLowestValueCard(getCards());
    }

    private static Card findLowestValueCard(ArrayList<Card> cards) {
        Card lowestValueCard = null;
        ArrayList<String> duplicateTypes = findDuplicateCardTypes(cards);
        for (Card c : cards) {
            if (lowestValueCard == null || lowestValueCard.getValue() > c.getValue() && (duplicateTypes.contains(c.getType()) || duplicateTypes.isEmpty())) {
                lowestValueCard = c;
            }
        }
        return lowestValueCard;
    }

    private static Card findLowestDuplicateCard(ArrayList<Card> cards) {
        Card lowestValueCard = null;
        ArrayList<String> duplicateTypes = findDuplicateCardTypes(cards);
        for (Card c : cards) {
            if ((lowestValueCard == null || lowestValueCard.getValue() > c.getValue()) && (duplicateTypes.contains(c.getType()))) {
                lowestValueCard = c;
            }
        }
        return lowestValueCard;
    }

}