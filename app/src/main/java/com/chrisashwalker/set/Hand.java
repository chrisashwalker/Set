package com.chrisashwalker.set;

import java.util.ArrayList;

public class Hand {

    private int capacity = 8;
    private ArrayList<Card> cards;
    private ArrayList<Card> bonuses;

    public Hand(Deck deck) {
        deal(deck);
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

    public static ArrayList<String> findDuplicateCardTypes(ArrayList<Card> cards) {
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

    public ArrayList<String> findDuplicateCardTypes() {
        return findDuplicateCardTypes(getCards());
    }

    public static Card findLowestValueCard(ArrayList<Card> cards) {
        Card lowestValueCard = null;
        ArrayList<String> duplicateTypes = findDuplicateCardTypes(cards);
        for (Card c : cards) {
            if (lowestValueCard == null || lowestValueCard.getValue() > c.getValue() && (duplicateTypes.contains(c.getType()) || duplicateTypes.isEmpty())) {
                lowestValueCard = c;
            }
        }
        return lowestValueCard;
    }

    public Card findLowestValueCard() {
        return findLowestValueCard(getCards());
    }

    public static Card findLowestDuplicateCard(ArrayList<Card> cards) {
        Card lowestValueCard = null;
        ArrayList<String> duplicateTypes = findDuplicateCardTypes(cards);
        for (Card c : cards) {
            if ((lowestValueCard == null || lowestValueCard.getValue() > c.getValue()) && (duplicateTypes.contains(c.getType()))) {
                lowestValueCard = c;
            }
        }
        return lowestValueCard;
    }

    public boolean hasBonuses() {
        return !bonuses.isEmpty();
    }

    public void addBonus(Card bonus) {
        bonuses.add(bonus);
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

    private void deal(Deck deck) {
        cards = new ArrayList<>();
        bonuses = new ArrayList<>();
        while (cards.size() < this.capacity) {
            draw(deck);
        }
    }

    public void draw(Deck deck) {
        if (deck.getCards().peekFirst() != null) {
            Card drawnCard = deck.getCards().pollFirst();
            assert drawnCard != null;
            if (drawnCard.getType().equals(deck.getBonusType())) {
                addBonus(drawnCard);
            } else {
                addCard(drawnCard);
            }
        }
    }

}
