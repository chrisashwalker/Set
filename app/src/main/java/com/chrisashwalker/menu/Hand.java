package com.chrisashwalker.menu;

import java.util.ArrayList;

public class Hand {

    public static int size = 8;
    int score;
    int bonusScore;
    ArrayList<Card> cards;
    ArrayList<Card> bonuses;

    public Hand(Deck deck) {
        deal(deck);
    }

    public int getScore() {
        score = 0;
        bonusScore = 0;
        for (Card c : cards) {
            score += c.value;
        }
        if (!bonuses.isEmpty()) {
            for (Card b : bonuses) {
                bonusScore += b.value;
            }
        }
        return score + bonusScore;
    }

    public boolean hasBonuses() {
        return !bonuses.isEmpty();
    }

    public Card getCard(int id) {
        for (Card c : cards) {
            if (c.id == id){
                return c;
            }
        }
        return null;
    }

    public void removeCard(int id) {
        cards.remove(getCard(id));
    }

    public void addCard(int id) {
        for (Card c : Deck.allCards) {
            if (c.id == id) {
                cards.add(c);
            }
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void draw(Deck deck) {
        Card drawnCard = deck.cards.pollFirst();
        if (drawnCard != null) {
            if (drawnCard.type.equals(deck.bonusType)) {
                bonuses.add(drawnCard);
            } else {
                cards.add(drawnCard);
            }
        }
    }

    private void deal(Deck deck) {
        cards = new ArrayList<>();
        bonuses = new ArrayList<>();
        while (cards.size() < Hand.size) {
            draw(deck);
        }
    }

}
