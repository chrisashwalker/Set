package com.chrisashwalker.menu;

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

    public void removeCard(int index) {
        cards.remove(index);
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
        for (Card c : getCards()) {
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
