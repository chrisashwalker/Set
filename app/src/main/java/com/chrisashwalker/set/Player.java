package com.chrisashwalker.set;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private static int nextId = 1;
    private int id;
    private Hand hand;
    private boolean isHuman;
    private int goal;
    private static int highScore;

    public Player(Deck deck) {
        this.id = nextId++;
        setHand(new Hand(deck));
    }

    public static void resetNextId() {
        nextId = 1;
    }

    public int getId() {
        return id;
    }

    public Hand getHand() {
        return hand;
    }

    private void setHand(Hand hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getCards() {
        return getHand().getCards();
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

    private void setGoal(Deck deck) {
        if (checkIsHuman()) {
            setGoal(getHighScore() + 1 <= deck.getHighestPossibleScore() ? getHighScore() + 1 : getHighScore());
        } else {
            setGoal(new Random().nextInt(deck.getHighestPossibleScore()));
        }
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int score) {
        highScore = score;
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
