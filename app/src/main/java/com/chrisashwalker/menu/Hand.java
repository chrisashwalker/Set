package com.chrisashwalker.menu;

import java.util.ArrayList;

public class Hand {

    public static int size = 8;
    int score;
    int bonusScore;
    int highScore;
    ArrayList<Card> cards;
    ArrayList<Card> bonuses;

    public Hand() {
        deal();
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
        if (score + bonusScore > highScore) {
            highScore = score + bonusScore;
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

    public void draw() {
        Card drawnCard = Game.deck.cards.pollFirst();
        if (drawnCard != null) {
            if (drawnCard.type.equals("BONUS")) {
                bonuses.add(drawnCard);
            } else {
                cards.add(drawnCard);
            }
        }
    }

    private void deal() {
        cards = new ArrayList<>();
        bonuses = new ArrayList<>();
        while (cards.size() < Hand.size) {
            draw();
        }
    }

}
