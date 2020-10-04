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
    ArrayList<Card> waiters = new ArrayList<>();

    Boolean piletaken = false;
    Boolean deckpicked = false;

    int waitercount = 0;
    int opwaitercount = 0;
    String waitercounttext = "0 waiters";
    String opwaitercounttext = "0 waiters";

    Card dummy = new Card("00", "DUMMY", 0);
    Card deckcard = dummy;
    Card pilecard = dummy;
    Card card1 = new Card("01", "Drink", 1);
    Card card2 = new Card("02", "Drink", 4);
    Card card3 = new Card("03", "Drink", 5);
    Card card4 = new Card("04", "Drink", 6);
    Card card5 = new Card("05", "Drink", 7);
    Card card6 = new Card("06", "Meat", 1);
    Card card7 = new Card("07", "Meat", 2);
    Card card8 = new Card("08", "Meat", 4);
    Card card9 = new Card("09", "Meat", 7);
    Card card10 = new Card("10", "Meat", 9);
    Card card11 = new Card("11", "Fish", 2);
    Card card12 = new Card("12", "Fish", 4);
    Card card13 = new Card("13", "Fish", 6);
    Card card14 = new Card("14", "Fish", 8);
    Card card15 = new Card("15", "Fish", 10);
    Card card16 = new Card("16", "Roll", 5);
    Card card17 = new Card("17", "Roll", 5);
    Card card18 = new Card("18", "Roll", 5);
    Card card19 = new Card("19", "Roll", 5);
    Card card20 = new Card("20", "Soup", 2);
    Card card21 = new Card("21", "Soup", 3);
    Card card22 = new Card("22", "Soup", 4);
    Card card23 = new Card("23", "Soup", 5);
    Card card24 = new Card("24", "Soup", 8);
    Card card25 = new Card("25", "Sweet", 3);
    Card card26 = new Card("26", "Sweet", 4);
    Card card27 = new Card("27", "Sweet", 4);
    Card card28 = new Card("28", "Sweet", 6);
    Card card29 = new Card("29", "Sweet", 8);
    Card card30 = new Card("30", "Potato", 2);
    Card card31 = new Card("31", "Potato", 3);
    Card card32 = new Card("32", "Potato", 5);
    Card card33 = new Card("33", "Potato", 6);
    Card card34 = new Card("34", "Veg", 1);
    Card card35 = new Card("35", "Veg", 3);
    Card card36 = new Card("36", "Veg", 4);
    Card card37 = new Card("37", "Veg", 5);
    Card card38 = new Card("38", "Waiter", 5);
    Card card39 = new Card("39", "Waiter", 5);
    Card card40 = new Card("40", "Waiter", 5);

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
        deck.add(card25);
        deck.add(card26);
        deck.add(card27);
        deck.add(card28);
        deck.add(card29);
        deck.add(card30);
        deck.add(card31);
        deck.add(card32);
        deck.add(card33);
        deck.add(card34);
        deck.add(card35);
        deck.add(card36);
        deck.add(card37);

        waiters.add(card38);
        waiters.add(card39);
        waiters.add(card40);
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
        String c1 = hand.get(0).type + "\n" + hand.get(0).value;
        vcard1.setText(c1);
        TextView vcard2 = findViewById(R.id.card2);
        String c2 = hand.get(1).type + "\n" + hand.get(1).value;
        vcard2.setText(c2);
        TextView vcard3 = findViewById(R.id.card3);
        String c3 = hand.get(2).type + "\n" + hand.get(2).value;
        vcard3.setText(c3);
        TextView vcard4 = findViewById(R.id.card4);
        String c4 = hand.get(3).type + "\n" + hand.get(3).value;
        vcard4.setText(c4);
        TextView vcard5 = findViewById(R.id.card5);
        String c5 = hand.get(4).type + "\n" + hand.get(4).value;
        vcard5.setText(c5);
        TextView vcard6 = findViewById(R.id.card6);
        String c6 = hand.get(5).type + "\n" + hand.get(5).value;
        vcard6.setText(c6);
        TextView vcard7 = findViewById(R.id.card7);
        String c7 = hand.get(6).type + "\n" + hand.get(6).value;
        vcard7.setText(c7);
        TextView vcard8 = findViewById(R.id.card8);
        String c8 = hand.get(7).type + "\n" + hand.get(7).value;
        vcard8.setText(c8);
    }

    public void confirmtest(){
        ArrayList<String> goalset = new ArrayList<>();
        goalset.add("Drink");
        goalset.add("Meat");
        goalset.add("Fish");
        goalset.add("Roll");
        goalset.add("Soup");
        goalset.add("Sweet");
        goalset.add("Potato");
        goalset.add("Veg");
        ArrayList<String> currentset = new ArrayList<>();
        for (Card c : hand) {
            currentset.add(c.type);
        }
        ArrayList<String> missing = new ArrayList<>(goalset);
        missing.removeAll(currentset);
        missing.remove("Waiter");
        missing.remove("Waiter");
        missing.remove("Waiter");
        if (missing.isEmpty()) {
            TextView confirmview = findViewById(R.id.finishView);
            confirmview.setVisibility(View.VISIBLE);
        }
    }

    public void playGame(View view) {
        deck.clear();
        hand.clear();
        ohand.clear();
        deckcard = dummy;
        pilecard = dummy;
        waitercount = 0;
        opwaitercount = 0;
        setContentView(R.layout.activity_play);
        addCards();
        drawCards();
        deck.add(waiters.get(0));
        deck.add(waiters.get(1));
        deck.add(waiters.get(2));
        confirmtest();
    }

    public void takeDeck(View view) {
        if (!piletaken && !deckpicked) {
            deckpicked = true;
            Random rnd = new Random();
            int rdint = rnd.nextInt(deck.size());
            deckcard = deck.get(rdint);
            deck.remove(rdint);
            if (deckcard.type.equals("Waiter")) {
                hand.add(deckcard);
                waitercount += 1;
                TextView waitercountview = findViewById(R.id.waiterView);
                waitercountview.setVisibility(View.VISIBLE);
                waitercounttext = waitercount + " waiter";
                waitercountview.setText(waitercounttext);
                rdint = rnd.nextInt(deck.size());
                deckcard = deck.get(rdint);
                deck.remove(rdint);
                if (deckcard.type.equals("Waiter")) {
                    hand.add(deckcard);
                    waitercount += 1;
                    waitercounttext = waitercount + " waiters";
                    waitercountview.setText(waitercounttext);
                    rdint = rnd.nextInt(deck.size());
                    deckcard = deck.get(rdint);
                    deck.remove(rdint);
                    if (deckcard.type.equals("Waiter")) {
                        hand.add(deckcard);
                        waitercount += 1;
                        waitercounttext = waitercount + " waiters";
                        waitercountview.setText(waitercounttext);
                        rdint = rnd.nextInt(deck.size());
                        deckcard = deck.get(rdint);
                        deck.remove(rdint);
                    }
                }
            }
            TextView deckview = findViewById(R.id.deckView);
            deckview.setBackgroundColor(0xFF4CAF50);
            String decktext = deckcard.type + "\n" + deckcard.value;
            deckview.setText(decktext);
        } else if (deckpicked){
            if (pilecard.value > 0) {
                deck.add(pilecard);
            }
            pilecard = deckcard;
            deckcard = dummy;
            TextView deckview = findViewById(R.id.deckView);
            deckview.setBackgroundColor(0x0003A9F4);
            String decktext = "Deck\n";
            deckview.setText(decktext);
            deckpicked = false;
            oturn();
        }
    }

    public void takePile(View view) {
        if (deckcard.value == 0 && pilecard.value > 0) {
            TextView pileview = findViewById(R.id.discardView);
            pileview.setBackgroundColor(0xFF4CAF50);
            piletaken = true;
        }
    }

    public void oturn() {
        ArrayList<String> goalset = new ArrayList<>();
        goalset.add("Drink");
        goalset.add("Meat");
        goalset.add("Fish");
        goalset.add("Roll");
        goalset.add("Soup");
        goalset.add("Sweet");
        goalset.add("Potato");
        goalset.add("Veg");
        ArrayList<String> currentset = new ArrayList<>();
        for (Card oc : ohand) {
            currentset.add(oc.type);
        }
        ArrayList<String> missing = new ArrayList<>(goalset);
        missing.removeAll(currentset);
        missing.remove("Waiter");
        missing.remove("Waiter");
        missing.remove("Waiter");
        if (missing.isEmpty()) {
            endgame();
        } else {
            if (missing.contains(pilecard.type)) {
                ohand.add(pilecard);
            } else {
                Random rd = new Random();
                int rint = rd.nextInt(deck.size());
                deckcard = deck.get(rint);
                deck.remove(rint);
                ohand.add(deckcard);
                if (deckcard.type.equals("Waiter")) {
                    opwaitercount += 1;
                    rint = rd.nextInt(deck.size());
                    deckcard = deck.get(rint);
                    deck.remove(rint);
                    ohand.add(deckcard);
                    if (deckcard.type.equals("Waiter")) {
                        opwaitercount += 1;
                        rint = rd.nextInt(deck.size());
                        deckcard = deck.get(rint);
                        deck.remove(rint);
                        ohand.add(deckcard);
                        if (deckcard.type.equals("Waiter")) {
                            opwaitercount += 1;
                            rint = rd.nextInt(deck.size());
                            deckcard = deck.get(rint);
                            deck.remove(rint);
                            ohand.add(deckcard);
                        }
                    }
                }
                deckcard = dummy;
            }
            for (String ty : goalset) {
                currentset.remove(ty);
            }
            currentset.remove("Waiter");
            currentset.remove("Waiter");
            currentset.remove("Waiter");
            String pickonetype = currentset.get(0);
            ArrayList<Card> duplicates = new ArrayList<>();
            for (Card ocd : ohand) {
                if (pickonetype.equals(ocd.type)) {
                    duplicates.add(ocd);
                }
            }
            if (duplicates.get(0).value < duplicates.get(1).value) {
                pilecard = duplicates.get(0);
                ohand.remove(duplicates.get(0));
            } else {
                pilecard = duplicates.get(1);
                ohand.remove(duplicates.get(1));
            }
            TextView pileview = findViewById(R.id.discardView);
            String piletext = pilecard.type + "\n" + pilecard.value;
            pileview.setText(piletext);
        }
    }

    public void replaceCard(View view) {
        String newcard;
        int oldcardviewid = view.getId();
        TextView oldcardview = findViewById(oldcardviewid);
        String oldcard = oldcardview.getText().toString();
        Card oldcardobject = dummy;
        for (Card c : hand) {
            if ((c.type + "\n" + c.value).equals(oldcard)) {
                oldcardobject = c;
            }
        }
        if (oldcardobject.value > 0) {
            if (deckcard.value > 0) {
                newcard = deckcard.type + "\n" + deckcard.value;
                hand.add(deckcard);
                deckpicked = false;
                oldcardview.setText(newcard);
                hand.remove(oldcardobject);
                if (pilecard.value > 0) {
                    deck.add(pilecard);
                }
                pilecard = oldcardobject;
                deckcard = dummy;
                TextView deckview = findViewById(R.id.deckView);
                deckview.setBackgroundColor(0x004CAF50);
                String decktext = "Deck\n";
                deckview.setText(decktext);
                TextView pileview = findViewById(R.id.discardView);
                String piletext = pilecard.type + "\n" + pilecard.value;
                pileview.setText(piletext);
                oturn();
            } else if (piletaken) {
                newcard = pilecard.type + "\n" + pilecard.value;
                oldcardview.setText(newcard);
                hand.add(pilecard);
                hand.remove(oldcardobject);
                pilecard = oldcardobject;
                TextView pileview = findViewById(R.id.discardView);
                pileview.setBackgroundColor(0x004CAF50);
                String piletext = pilecard.type + "\n" + pilecard.value;
                pileview.setText(piletext);
                piletaken = false;
                oturn();
            }
        }
        confirmtest();
    }

    public void confirmCards(View view) {
        ArrayList<String> goalset = new ArrayList<>();
        goalset.add("Drink");
        goalset.add("Meat");
        goalset.add("Fish");
        goalset.add("Roll");
        goalset.add("Soup");
        goalset.add("Sweet");
        goalset.add("Potato");
        goalset.add("Veg");
        ArrayList<String> currentset = new ArrayList<>();
        for (Card c : hand) {
            currentset.add(c.type);
        }
        ArrayList<String> missing = new ArrayList<>(goalset);
        missing.removeAll(currentset);
        missing.remove("Waiter");
        missing.remove("Waiter");
        missing.remove("Waiter");
        if (missing.isEmpty()) {
            endgame();
        }
    }

    public void endgame() {
        setContentView(R.layout.activity_result);
        TextView waitercountview = findViewById(R.id.waiterView);
        TextView opwaitercountview = findViewById(R.id.opwaiterView);
        waitercounttext = waitercount + " waiter(s)";
        opwaitercounttext = opwaitercount + " waiter(s)";
        waitercountview.setText(waitercounttext);
        opwaitercountview.setText(opwaitercounttext);
        TextView resultview = findViewById(R.id.resultView);
        String resultstring = "";
        int playerscore = 0, opscore = 0;
        ArrayList<Card> scorebuilder = new ArrayList<>(hand);
        ArrayList<Card> opscorebuilder = new ArrayList<>(ohand);
        hand.remove(waiters.get(0));
        hand.remove(waiters.get(1));
        hand.remove(waiters.get(2));
        ohand.remove(waiters.get(0));
        ohand.remove(waiters.get(1));
        ohand.remove(waiters.get(2));
        TextView mcard1 = findViewById(R.id.card1);
        TextView mcard2 = findViewById(R.id.card2);
        TextView mcard3 = findViewById(R.id.card3);
        TextView mcard4 = findViewById(R.id.card4);
        TextView mcard5 = findViewById(R.id.card5);
        TextView mcard6 = findViewById(R.id.card6);
        TextView mcard7 = findViewById(R.id.card7);
        TextView mcard8 = findViewById(R.id.card8);
        TextView xcard1 = findViewById(R.id.opcard1);
        String m1 = hand.get(0).type + "\n" + hand.get(0).value;
        mcard1.setText(m1);
        String m2 = hand.get(1).type + "\n" + hand.get(1).value;
        mcard2.setText(m2);
        String m3 = hand.get(2).type + "\n" + hand.get(2).value;
        mcard3.setText(m3);
        String m4 = hand.get(3).type + "\n" + hand.get(3).value;
        mcard4.setText(m4);
        String m5 = hand.get(4).type + "\n" + hand.get(4).value;
        mcard5.setText(m5);
        String m6 = hand.get(5).type + "\n" + hand.get(5).value;
        mcard6.setText(m6);
        String m7 = hand.get(6).type + "\n" + hand.get(6).value;
        mcard7.setText(m7);
        String m8 = hand.get(7).type + "\n" + hand.get(7).value;
        mcard8.setText(m8);
        String oc1 = ohand.get(0).type + "\n" + ohand.get(0).value;
        xcard1.setText(oc1);
        xcard1.setVisibility(View.VISIBLE);
        TextView xcard2 = findViewById(R.id.opcard2);
        String oc2 = ohand.get(1).type + "\n" + ohand.get(1).value;
        xcard2.setText(oc2);
        xcard2.setVisibility(View.VISIBLE);
        TextView xcard3 = findViewById(R.id.opcard3);
        String oc3 = ohand.get(2).type + "\n" + ohand.get(2).value;
        xcard3.setText(oc3);
        xcard3.setVisibility(View.VISIBLE);
        TextView xcard4 = findViewById(R.id.opcard4);
        String oc4 = ohand.get(3).type + "\n" + ohand.get(3).value;
        xcard4.setText(oc4);
        xcard4.setVisibility(View.VISIBLE);
        TextView xcard5 = findViewById(R.id.opcard5);
        String oc5 = ohand.get(4).type + "\n" + ohand.get(4).value;
        xcard5.setText(oc5);
        xcard5.setVisibility(View.VISIBLE);
        TextView xcard6 = findViewById(R.id.opcard6);
        String oc6 = ohand.get(5).type + "\n" + ohand.get(5).value;
        xcard6.setText(oc6);
        xcard6.setVisibility(View.VISIBLE);
        TextView xcard7 = findViewById(R.id.opcard7);
        String oc7 = ohand.get(6).type + "\n" + ohand.get(6).value;
        xcard7.setText(oc7);
        xcard7.setVisibility(View.VISIBLE);
        TextView xcard8 = findViewById(R.id.opcard8);
        String oc8 = ohand.get(7).type + "\n" + ohand.get(7).value;
        xcard8.setText(oc8);
        xcard8.setVisibility(View.VISIBLE);
        int pdeduct = 0, opdeduct = 0;
        for (Card pc : scorebuilder) {
            for (Card pc2 : scorebuilder) {
                if (!pc2.id.equals(pc.id) && pc2.type.equals(pc.type) && !pc.type.equals("Waiter")) {
                    if (pc.value < pc2.value) {
                        pdeduct += pc.value;
                        break;
                    }
                }
            }
        }
        for (Card pc3 : scorebuilder) {
            playerscore += pc3.value;
        }
        playerscore -= pdeduct;
        for (Card pc4 : opscorebuilder) {
            for (Card pc5 : opscorebuilder) {
                if (!pc5.id.equals(pc4.id) && pc5.type.equals(pc4.type) && !pc4.type.equals("Waiter")) {
                    if (pc4.value < pc5.value) {
                        opdeduct += pc4.value;
                        break;
                    }
                }
            }
        }
        for (Card pc6 : opscorebuilder) {
            opscore += pc6.value;
        }
        opscore -= opdeduct;
        if (playerscore > opscore) {
            resultstring = "You win! Your score: " + playerscore + "; Opponent score: " + opscore;
        } else if (playerscore == opscore) {
            resultstring = "It's a draw. Your score: " + playerscore + "; Opponent score: " + opscore;
        }
        if (playerscore < opscore) {
            resultstring = "You lose. Your score: " + playerscore + "; Opponent score: " + opscore;
        }
        resultview.setText(resultstring);
    }
}