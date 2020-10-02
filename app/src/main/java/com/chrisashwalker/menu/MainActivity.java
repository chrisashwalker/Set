package com.chrisashwalker.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class Card {
        String id;
        String type;
        int value;

        public Card(String i, String t, int v) {
            id = i;
            type = t;
            value = v;
        }
    }

    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Card> ohand = new ArrayList<>();

    Card dummy = new Card("00", "Z", 0);
    Card card1 = new Card("01", "A", 1);
    Card card2 = new Card("02", "B", 1);
    Card card3 = new Card("03", "C", 1);
    Card card4 = new Card("04", "D", 1);
    Card card5 = new Card("05", "E", 1);
    Card card6 = new Card("06", "F", 1);
    Card card7 = new Card("07", "G", 1);
    Card card8 = new Card("08", "H", 1);
    Card card9 = new Card("09", "A", 2);
    Card card10 = new Card("10", "B", 2);
    Card card11 = new Card("11", "C", 2);
    Card card12 = new Card("12", "D", 2);
    Card card13 = new Card("13", "E", 2);
    Card card14 = new Card("14", "F", 2);
    Card card15 = new Card("15", "G", 2);
    Card card16 = new Card("16", "H", 2);
    Card card17 = new Card("17", "A", 3);
    Card card18 = new Card("18", "B", 3);
    Card card19 = new Card("19", "C", 3);
    Card card20 = new Card("20", "D", 3);
    Card card21 = new Card("21", "E", 3);
    Card card22 = new Card("22", "F", 3);
    Card card23 = new Card("23", "G", 3);
    Card card24 = new Card("24", "H", 3);

    public void addCards() {
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);
        deck.add(card7);
        deck.add(card8);
        deck.add(card9);
        deck.add(card10);
        deck.add(card11);
        deck.add(card12);
        deck.add(card13);
        deck.add(card14);
        deck.add(card15);
        deck.add(card16);
        deck.add(card17);
        deck.add(card18);
        deck.add(card19);
        deck.add(card20);
        deck.add(card21);
        deck.add(card22);
        deck.add(card23);
        deck.add(card24);
    }

    public void drawCards() {
        for (int j = 0; j < 8; j++) {
            Random rdm = new Random();
            int rdmint = rdm.nextInt(deck.size());
            hand.add(deck.get(rdmint));
            deck.remove(rdmint);
        }
        for (int k = 0; k < 8; k++) {
            Random rdm2 = new Random();
            int rdmint2 = rdm2.nextInt(deck.size());
            ohand.add(deck.get(rdmint2));
            deck.remove(rdmint2);
        }
        TextView vcard1 = findViewById(R.id.card1);
        String c1 = hand.get(0).id + " : " + hand.get(0).type + " : " + hand.get(0).value;
        vcard1.setText(c1);
        TextView vcard2 = findViewById(R.id.card2);
        String c2 = hand.get(1).id + " : " + hand.get(1).type + " : " + hand.get(1).value;
        vcard2.setText(c2);
        TextView vcard3 = findViewById(R.id.card3);
        String c3 = hand.get(2).id + " : " + hand.get(2).type + " : " + hand.get(2).value;
        vcard3.setText(c3);
        TextView vcard4 = findViewById(R.id.card4);
        String c4 = hand.get(3).id + " : " + hand.get(3).type + " : " + hand.get(3).value;
        vcard4.setText(c4);
        TextView vcard5 = findViewById(R.id.card5);
        String c5 = hand.get(4).id + " : " + hand.get(4).type + " : " + hand.get(4).value;
        vcard5.setText(c5);
        TextView vcard6 = findViewById(R.id.card6);
        String c6 = hand.get(5).id + " : " + hand.get(5).type + " : " + hand.get(5).value;
        vcard6.setText(c6);
        TextView vcard7 = findViewById(R.id.card7);
        String c7 = hand.get(6).id + " : " + hand.get(6).type + " : " + hand.get(6).value;
        vcard7.setText(c7);
        TextView vcard8 = findViewById(R.id.card8);
        String c8 = hand.get(7).id + " : " + hand.get(7).type + " : " + hand.get(7).value;
        vcard8.setText(c8);
    }

    public void playGame(View view) {
        setContentView(R.layout.activity_play);
        addCards();
        drawCards();
    }

    public void chooseValue(View view) {
        Random rnd = new Random();
        int rndint = rnd.nextInt(deck.size());
        String returning = deck.get(rndint).id + " : " + deck.get(rndint).type + " : " + deck.get(rndint).value;
        ArrayList<TextView> cardviews = new ArrayList<>();
        TextView cardv1 = findViewById(R.id.card1);
        TextView cardv2 = findViewById(R.id.card2);
        TextView cardv3 = findViewById(R.id.card3);
        TextView cardv4 = findViewById(R.id.card4);
        TextView cardv5 = findViewById(R.id.card5);
        TextView cardv6 = findViewById(R.id.card6);
        TextView cardv7 = findViewById(R.id.card7);
        TextView cardv8 = findViewById(R.id.card8);
        cardviews.add(cardv1);
        cardviews.add(cardv2);
        cardviews.add(cardv3);
        cardviews.add(cardv4);
        cardviews.add(cardv5);
        cardviews.add(cardv6);
        cardviews.add(cardv7);
        cardviews.add(cardv8);

        for (int x = 0; x<8; x++){
            if (hand.get(x) == dummy){
                hand.set(x, deck.get(rndint));
                cardviews.get(x).setText(returning);
            }
        }
        deck.remove(rndint);
        deck.add(ohand.get(0));
        ohand.remove(0);
        int rndint2 = rnd.nextInt(deck.size());
        ohand.add(deck.get(rndint2));
        deck.remove(rndint2);
    }

    public void refreshCard1(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard1 = findViewById(R.id.card1);
            String old = wcard1.getText().toString().substring(0, 2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(0, dummy);
                    break;
                }
            }
        }
    }

    public void refreshCard2(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard2 = findViewById(R.id.card2);
            String old = wcard2.getText().toString().substring(0,2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(1, dummy);
                    break;
                }
            }
        }
    }

    public void refreshCard3(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard3 = findViewById(R.id.card3);
            String old = wcard3.getText().toString().substring(0, 2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(2, dummy);
                    break;
                }
            }
        }
    }

    public void refreshCard4(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard4 = findViewById(R.id.card4);
            String old = wcard4.getText().toString().substring(0, 2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(3, dummy);
                    break;
                }
            }
        }
    }

    public void refreshCard5(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard5 = findViewById(R.id.card5);
            String old = wcard5.getText().toString().substring(0, 2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(4, dummy);
                    break;
                }
            }
        }
    }

    public void refreshCard6(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard6 = findViewById(R.id.card6);
            String old = wcard6.getText().toString().substring(0, 2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(5, dummy);
                    break;
                }
            }
        }
    }

    public void refreshCard7(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard7 = findViewById(R.id.card7);
            String old = wcard7.getText().toString().substring(0, 2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(6, dummy);
                    break;
                }
            }
        }
    }

    public void refreshCard8(View view) {
        if (!hand.contains(dummy)) {
            TextView wcard8 = findViewById(R.id.card8);
            String old = wcard8.getText().toString().substring(0, 2);
            for (Card c : hand) {
                if (c.id.equals(old)) {
                    deck.add(c);
                    hand.set(7, dummy);
                    break;
                }
            }
        }
    }

    public void confirmCards(View view) {
        setContentView(R.layout.activity_main);
        TextView xcard1 = findViewById(R.id.opcard1);
        String oc1 = ohand.get(0).id + " : " + ohand.get(0).type + " : " + ohand.get(0).value;
        xcard1.setText(oc1);
        xcard1.setVisibility(View.VISIBLE);
        TextView xcard2 = findViewById(R.id.opcard2);
        String oc2 = ohand.get(1).id + " : " + ohand.get(1).type + " : " + ohand.get(1).value;
        xcard2.setText(oc2);
        xcard2.setVisibility(View.VISIBLE);
        TextView xcard3 = findViewById(R.id.opcard3);
        String oc3 = ohand.get(2).id + " : " + ohand.get(2).type + " : " + ohand.get(2).value;
        xcard3.setText(oc3);
        xcard3.setVisibility(View.VISIBLE);
        TextView xcard4 = findViewById(R.id.opcard4);
        String oc4 = ohand.get(3).id + " : " + ohand.get(3).type + " : " + ohand.get(3).value;
        xcard4.setText(oc4);
        xcard4.setVisibility(View.VISIBLE);
        TextView xcard5 = findViewById(R.id.opcard5);
        String oc5 = ohand.get(4).id + " : " + ohand.get(4).type + " : " + ohand.get(4).value;
        xcard5.setText(oc5);
        xcard5.setVisibility(View.VISIBLE);
        TextView xcard6 = findViewById(R.id.opcard6);
        String oc6 = ohand.get(5).id + " : " + ohand.get(5).type + " : " + ohand.get(5).value;
        xcard6.setText(oc6);
        xcard6.setVisibility(View.VISIBLE);
        TextView xcard7 = findViewById(R.id.opcard7);
        String oc7 = ohand.get(6).id + " : " + ohand.get(6).type + " : " + ohand.get(6).value;
        xcard7.setText(oc7);
        xcard7.setVisibility(View.VISIBLE);
        TextView xcard8 = findViewById(R.id.opcard8);
        String oc8 = ohand.get(7).id + " : " + ohand.get(7).type + " : " + ohand.get(7).value;
        xcard8.setText(oc8);
        xcard8.setVisibility(View.VISIBLE);
        TextView resultview = findViewById(R.id.result);
        String resultstring = "";
        int playerscore = 0, opscore = 0;
        for (Card pc : hand){
            playerscore += pc.value;
        }
        for (Card oc : ohand){
            opscore += oc.value;
        }
        if (playerscore > opscore){
            resultstring = "You win! Your score: " + playerscore + "; Opponent score: " + opscore;
        }
        else if (playerscore == opscore){
            resultstring = "It's a draw. Your score: " + playerscore + "; Opponent score: " + opscore;
        }
        if (playerscore < opscore){
            resultstring = "You lose. Your score: " + playerscore + "; Opponent score: " + opscore;
        }
        resultview.setText(resultstring);
    }
}