package com.chrisashwalker.menu;

public class Card {
    static int nextId = 1;
    int id;
    String type;
    int value;

    public Card(String type, int value) {
        this.id = nextId++;
        this.type = type;
        this.value = value;
    }
}
