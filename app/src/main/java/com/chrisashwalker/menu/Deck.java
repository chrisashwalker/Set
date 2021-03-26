package com.chrisashwalker.menu;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Deck
{
    String[] Types = {"Blue", "Green", "Indigo", "Navy", "Orange", "Red", "Violet", "Yellow"};
    int size = 40;
    int bonuses = size % Types.length;
    int countOfEachType = (size - bonuses) / Types.length;
    ArrayDeque<Card> Cards;
    public Deck()
    {
        this.build();
        this.shuffle();
    }

    private void build()
    {
        this.Cards = new ArrayDeque<>();
        for (int i = 1; i <= this.countOfEachType; i++)
        {
            for (int j = 1; j <= this.Types.length - 1; j++)
            {
                Cards.add(new Card(this.Types[j], i));
            }
        }
        while (Cards.size() < this.size)
        {
            Cards.add(new Card("Bonus", 3));
        }
    }

    private void shuffle()
    {
        List<Card> OrderedCards = new ArrayList<>(Cards);
        Collections.shuffle(OrderedCards);
        Cards = new ArrayDeque<>(OrderedCards);
    }

    private static class Card
    {
        String type;
        int value;

        private Card(String type, int value)
        {
            this.type = type;
            this.value = value;
        }
    }
}
