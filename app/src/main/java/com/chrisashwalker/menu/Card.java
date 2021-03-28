package com.chrisashwalker.menu;

public class Card {

    private static int nextId = 1;
    private int id;
    private String type;
    private int value;

    public Card(String type, int value) {
        this.id = nextId++;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

}
