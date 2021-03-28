package com.chrisashwalker.menu;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private static int nextId = 1;
    private int id;
    private boolean isHuman;
    private int goal;
    private int highScore;
    private Hand hand;

    public Player(Deck deck) {
        this.id = nextId++;
        setHand(new Hand(deck));
    }

    public int getId() {
        return id;
    }

    public boolean checkIsHuman() {
        return isHuman;
    }

    public void setAsHuman() {
        isHuman = true;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void setGoal(Deck deck) {
        if (checkIsHuman()) {
            setGoal(getHighScore() + 1 <= deck.getHighestPossibleScore() ? getHighScore() + 1 : getHighScore());
        } else {
            setGoal(new Random().nextInt(deck.getHighestPossibleScore()));
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getCards() {
        return getHand().getCards();
    }

    public static ArrayList<Player> assemble(Deck deck, int humanPlayerCount, int cpuPlayerCount) {
        ArrayList<Player> players = new ArrayList<>();
        while (players.size() < humanPlayerCount) {
            Player newPlayer = new Player(deck);
            players.add(newPlayer);
            newPlayer.setAsHuman();
            newPlayer.setGoal(deck);
        }
        while (players.size() < humanPlayerCount + cpuPlayerCount) {
            Player newPlayer = new Player(deck);
            players.add(newPlayer);
            newPlayer.setGoal(deck);
        }
        return players;
    }

}
