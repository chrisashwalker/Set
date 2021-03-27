package com.chrisashwalker.menu;

public class Player {
    static int nextId = 1;
    int id;
    boolean human;
    Hand hand;
    int goal;

    public Player() {
        this.id = nextId++;
        this.hand = new Hand();
    }

}
