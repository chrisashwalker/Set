package com.chrisashwalker.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class Card {
        String type;
        String name;
        int value;

        public Card(String typeString, String nameString, int valueInt) {
            type = typeString;
            name = nameString;
            value = valueInt;
        }
    }

    ArrayList<Card> Deck = new ArrayList<>();
    ArrayList<Card> Hand = new ArrayList<>();
    ArrayList<Card> OpponentHand = new ArrayList<>();
    ArrayList<Card> BonusHand = new ArrayList<>();
    ArrayList<Card> OpponentBonusHand = new ArrayList<>();
    ArrayList<Card> Bonuses = new ArrayList<>();
    ArrayList<TextView> HandViews = new ArrayList<>();
    ArrayList<TextView> OpponentHandViews = new ArrayList<>();
    ArrayList<String> RequiredSet = new ArrayList<>();
    ArrayList<String> TestSet = new ArrayList<>();
    ArrayList<String> RequiredMissing = new ArrayList<>();

    Boolean discardTaken = false;
    Boolean deckPicked = false;

    int bonusCount = BonusHand.size();
    int opponentBonusCount = OpponentBonusHand.size();
    int colorPrimary = getResources().getColor(R.color.colorPrimary);
    int colorAccent = getResources().getColor(R.color.colorAccent);
    int colorFocused = getResources().getColor(R.color.colorFocused);
    
    String bonusType = "Waiter";
    String bonusCountText = bonusCount + bonusType + "(s)";
    String opponentBonusCountText = opponentBonusCount + bonusType + "(s)";
    String cardType1 = "Drink";
    String cardType2 = "Meat";
    String cardType3 = "Fish";
    String cardType4 = "Roll";
    String cardType5 = "Soup";
    String cardType6 = "Sweet";
    String cardType7 = "Potato";
    String cardType8 = "Veg";

    Card BlankCard = new Card("Blank", "", 0);
    Card TopOfDeck = BlankCard;
    Card Discarded = BlankCard;
    Card Card1 = new Card(cardType1, cardType1, 1);
    Card Card2 = new Card(cardType1, cardType1, 4);
    Card Card3 = new Card(cardType1, cardType1, 5);
    Card Card4 = new Card(cardType1, cardType1, 6);
    Card Card5 = new Card(cardType1, cardType1, 7);
    Card Card6 = new Card(cardType2, cardType2, 1);
    Card Card7 = new Card(cardType2, cardType2, 2);
    Card Card8 = new Card(cardType2, cardType2, 4);
    Card Card9 = new Card(cardType2, cardType2, 7);
    Card Card10 = new Card(cardType2, cardType2, 9);
    Card Card11 = new Card(cardType3, cardType3, 2);
    Card Card12 = new Card(cardType3, cardType3, 4);
    Card Card13 = new Card(cardType3, cardType3, 6);
    Card Card14 = new Card(cardType3, cardType3, 8);
    Card Card15 = new Card(cardType3, cardType3, 10);
    Card Card16 = new Card(cardType4, cardType4, 5);
    Card Card17 = new Card(cardType4, cardType4, 5);
    Card Card18 = new Card(cardType4, cardType4, 5);
    Card Card19 = new Card(cardType4, cardType4, 5);
    Card Card20 = new Card(cardType5, cardType5, 2);
    Card Card21 = new Card(cardType5, cardType5, 3);
    Card Card22 = new Card(cardType5, cardType5, 4);
    Card Card23 = new Card(cardType5, cardType5, 5);
    Card Card24 = new Card(cardType5, cardType5, 8);
    Card Card25 = new Card(cardType6, cardType6, 3);
    Card Card26 = new Card(cardType6, cardType6, 4);
    Card Card27 = new Card(cardType6, cardType6, 4);
    Card Card28 = new Card(cardType6, cardType6, 6);
    Card Card29 = new Card(cardType6, cardType6, 8);
    Card Card30 = new Card(cardType7, cardType7, 2);
    Card Card31 = new Card(cardType7, cardType7, 3);
    Card Card32 = new Card(cardType7, cardType7, 5);
    Card Card33 = new Card(cardType7, cardType7, 6);
    Card Card34 = new Card(cardType8, cardType8, 1);
    Card Card35 = new Card(cardType8, cardType8, 3);
    Card Card36 = new Card(cardType8, cardType8, 4);
    Card Card37 = new Card(cardType8, cardType8, 5);
    Card BonusCard1 = new Card(bonusType, bonusType, 5);
    Card BonusCard2 = new Card(bonusType, bonusType, 5);
    Card BonusCard3 = new Card(bonusType, bonusType, 5);
    
    TextView discardView = findViewById(R.id.discardView);
    TextView deckView = findViewById(R.id.deckView);
    TextView bonusView = findViewById(R.id.bonusView);
    TextView opponentBonusView = findViewById(R.id.opponentBonusView);
    TextView finishView = findViewById(R.id.finishView);
    TextView resultView = findViewById(R.id.resultView);
    TextView cardView1 = findViewById(R.id.card1);
    TextView cardView2 = findViewById(R.id.card2);
    TextView cardView3 = findViewById(R.id.card3);
    TextView cardView4 = findViewById(R.id.card4);
    TextView cardView5 = findViewById(R.id.card5);
    TextView cardView6 = findViewById(R.id.card6);
    TextView cardView7 = findViewById(R.id.card7);
    TextView cardView8 = findViewById(R.id.card8);
    TextView opponentCardView1 = findViewById(R.id.opponentCard1);
    TextView opponentCardView2 = findViewById(R.id.opponentCard2);
    TextView opponentCardView3 = findViewById(R.id.opponentCard3);
    TextView opponentCardView4 = findViewById(R.id.opponentCard4);
    TextView opponentCardView5 = findViewById(R.id.opponentCard5);
    TextView opponentCardView6 = findViewById(R.id.opponentCard6);
    TextView opponentCardView7 = findViewById(R.id.opponentCard7);
    TextView opponentCardView8 = findViewById(R.id.opponentCard8);

    Random random = new Random();
    int randomInt;

    String TopOfDeckText = TopOfDeck.type + "\n" + TopOfDeck.value;

    public void buildDeck() {
        Deck.addAll(Arrays.asList(Card1,Card2,Card3,Card4,Card5,Card6,Card7,Card8,Card9,Card10,
                Card11,Card12,Card13,Card14,Card15,Card16,Card17,Card18,Card19,Card20,
                Card21,Card22,Card23,Card24,Card25,Card26,Card27,Card28,Card29,Card30,
                Card31,Card32,Card33,Card34,Card35,Card36,Card37));
        
        Bonuses.add(BonusCard1);
        Bonuses.add(BonusCard2);
        Bonuses.add(BonusCard3);
        
        HandViews.addAll(Arrays.asList(
                cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8));

        OpponentHandViews.addAll(Arrays.asList(
                opponentCardView1,opponentCardView2,opponentCardView3,opponentCardView4,
                opponentCardView5,opponentCardView6,opponentCardView7,opponentCardView8));
    }

    public void dealCards() {
        Card dealtCard;
        String cardText;
        for (int i = 0; i < 8; i++) {
            randomInt = random.nextInt(Deck.size());
            dealtCard = Deck.get(randomInt);
            Hand.add(dealtCard);
            Deck.remove(dealtCard);
            cardText = dealtCard.type + "\n" + dealtCard.value;
            HandViews.get(i).setText(cardText);
            randomInt = random.nextInt(Deck.size());
            dealtCard = Deck.get(randomInt);
            OpponentHand.add(dealtCard);
            Deck.remove(dealtCard);
            cardText = dealtCard.type + "\n" + dealtCard.value;
            OpponentHandViews.get(i).setText(cardText);
        }
        RequiredSet.addAll(Arrays.asList(cardType1,cardType2,cardType3,cardType4,
                cardType5,cardType6,cardType7,cardType8));
    }

    public void testFinished(){
        for (Card card : Hand) {
            TestSet.add(card.type);
        }
        RequiredMissing = new ArrayList<>(RequiredSet);
        RequiredMissing.removeAll(TestSet);
        for (Card card : Bonuses) {
            RequiredMissing.remove(card.type);
        }
        if (RequiredMissing.isEmpty()) {
            finishView = findViewById(R.id.finishView);
            finishView.setVisibility(View.VISIBLE);
        }
    }

    public void playGame(View view) {
        Hand.clear();
        OpponentHand.clear();
        Deck.clear();
        TopOfDeck = BlankCard;
        Discarded = BlankCard;
        bonusCount = 0;
        opponentBonusCount = 0;
        setContentView(R.layout.activity_play);
        buildDeck();
        dealCards();
        Deck.addAll(Bonuses);
        testFinished();
    }

    public void takeDeck(View view) {
        if (!discardTaken && !deckPicked) {
            deckPicked = true;
            int randomInt = random.nextInt(Deck.size());
            TopOfDeck = Deck.get(randomInt);
            Deck.remove(TopOfDeck);
            if (TopOfDeck.type.equals(bonusType)) {
                BonusHand.add(TopOfDeck);
                deckPicked = false;
                takeDeck(deckView);
            }
            bonusView.setText(bonusCountText);
            deckView.setBackgroundColor(colorFocused);
            deckView.setText(TopOfDeckText);
        } else if (deckPicked){
            if (Discarded.value > 0) {
                Deck.add(Discarded);
            }
            Discarded = BlankCard;
            TopOfDeck = BlankCard;
            deckView.setBackgroundColor(colorPrimary);
            deckView.setText(R.string.deck);
            deckPicked = false;
            OpponentPlay();
        }
    }

    public void takePile(View view) {
        if (TopOfDeck.value == 0 && Discarded.value > 0) {
            discardView.setBackgroundColor(colorFocused);
            discardTaken = true;
        }
    }

    public void OpponentPlay() {
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
        for (Card oc : OpponentHand) {
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
            if (missing.contains(Discarded.type)) {
                OpponentHand.add(Discarded);
            } else {
                Random rd = new Random();
                int rint = rd.nextInt(Deck.size());
                TopOfDeck = Deck.get(rint);
                Deck.remove(rint);
                OpponentHand.add(TopOfDeck);
                if (TopOfDeck.type.equals("Waiter")) {
                    opponentBonusCount += 1;
                    rint = rd.nextInt(Deck.size());
                    TopOfDeck = Deck.get(rint);
                    Deck.remove(rint);
                    OpponentHand.add(TopOfDeck);
                    if (TopOfDeck.type.equals("Waiter")) {
                        opponentBonusCount += 1;
                        rint = rd.nextInt(Deck.size());
                        TopOfDeck = Deck.get(rint);
                        Deck.remove(rint);
                        OpponentHand.add(TopOfDeck);
                        if (TopOfDeck.type.equals("Waiter")) {
                            opponentBonusCount += 1;
                            rint = rd.nextInt(Deck.size());
                            TopOfDeck = Deck.get(rint);
                            Deck.remove(rint);
                            OpponentHand.add(TopOfDeck);
                        }
                    }
                }
                TopOfDeck = BlankCard;
            }
            for (String ty : goalset) {
                currentset.remove(ty);
            }
            currentset.remove("Waiter");
            currentset.remove("Waiter");
            currentset.remove("Waiter");
            String pickonetype = currentset.get(0);
            ArrayList<Card> duplicates = new ArrayList<>();
            for (Card ocd : OpponentHand) {
                if (pickonetype.equals(ocd.type)) {
                    duplicates.add(ocd);
                }
            }
            if (duplicates.get(0).value < duplicates.get(1).value) {
                Discarded = duplicates.get(0);
                OpponentHand.remove(duplicates.get(0));
            } else {
                Discarded = duplicates.get(1);
                OpponentHand.remove(duplicates.get(1));
            }
            TextView pileview = findViewById(R.id.discardView);
            String piletext = Discarded.type + "\n" + Discarded.value;
            pileview.setText(piletext);
        }
    }

    public void replaceCard(View view) {
        String newcard;
        int oldcardviewid = view.getId();
        TextView oldcardview = findViewById(oldcardviewid);
        String oldcard = oldcardview.getText().toString();
        Card oldcardobject = BlankCard;
        for (Card c : Hand) {
            if ((c.type + "\n" + c.value).equals(oldcard)) {
                oldcardobject = c;
            }
        }
        if (oldcardobject.value > 0) {
            if (TopOfDeck.value > 0) {
                newcard = TopOfDeck.type + "\n" + TopOfDeck.value;
                Hand.add(TopOfDeck);
                deckPicked = false;
                oldcardview.setText(newcard);
                Hand.remove(oldcardobject);
                if (Discarded.value > 0) {
                    Deck.add(Discarded);
                }
                Discarded = oldcardobject;
                TopOfDeck = BlankCard;
                TextView deckview = findViewById(R.id.deckView);
                deckview.setBackgroundColor(colorFocused);
                String decktext = "Deck\n";
                deckview.setText(decktext);
                TextView pileview = findViewById(R.id.discardView);
                String piletext = Discarded.type + "\n" + Discarded.value;
                pileview.setText(piletext);
                OpponentPlay();
            } else if (discardTaken) {
                newcard = Discarded.type + "\n" + Discarded.value;
                oldcardview.setText(newcard);
                Hand.add(Discarded);
                Hand.remove(oldcardobject);
                Discarded = oldcardobject;
                TextView pileview = findViewById(R.id.discardView);
                pileview.setBackgroundColor(colorFocused);
                String piletext = Discarded.type + "\n" + Discarded.value;
                pileview.setText(piletext);
                discardTaken = false;
                OpponentPlay();
            }
        }
        testFinished();
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
        for (Card c : Hand) {
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
        TextView waitercountview = findViewById(R.id.bonusView);
        TextView opwaitercountview = findViewById(R.id.opponentBonusView);
        bonusCountText = bonusCount + " waiter(s)";
        opponentBonusCountText = opponentBonusCount + " waiter(s)";
        waitercountview.setText(bonusCountText);
        opwaitercountview.setText(opponentBonusCountText);
        TextView resultview = findViewById(R.id.resultView);
        String resultstring = "";
        int playerscore = 0, opscore = 0;
        ArrayList<Card> scorebuilder = new ArrayList<>(Hand);
        ArrayList<Card> opscorebuilder = new ArrayList<>(OpponentHand);
        Hand.remove(Bonuses.get(0));
        Hand.remove(Bonuses.get(1));
        Hand.remove(Bonuses.get(2));
        OpponentHand.remove(Bonuses.get(0));
        OpponentHand.remove(Bonuses.get(1));
        OpponentHand.remove(Bonuses.get(2));
        TextView mcard1 = findViewById(R.id.card1);
        TextView mcard2 = findViewById(R.id.card2);
        TextView mcard3 = findViewById(R.id.card3);
        TextView mcard4 = findViewById(R.id.card4);
        TextView mcard5 = findViewById(R.id.card5);
        TextView mcard6 = findViewById(R.id.card6);
        TextView mcard7 = findViewById(R.id.card7);
        TextView mcard8 = findViewById(R.id.card8);
        TextView xcard1 = findViewById(R.id.opponentCard1);
        String m1 = Hand.get(0).type + "\n" + Hand.get(0).value;
        mcard1.setText(m1);
        String m2 = Hand.get(1).type + "\n" + Hand.get(1).value;
        mcard2.setText(m2);
        String m3 = Hand.get(2).type + "\n" + Hand.get(2).value;
        mcard3.setText(m3);
        String m4 = Hand.get(3).type + "\n" + Hand.get(3).value;
        mcard4.setText(m4);
        String m5 = Hand.get(4).type + "\n" + Hand.get(4).value;
        mcard5.setText(m5);
        String m6 = Hand.get(5).type + "\n" + Hand.get(5).value;
        mcard6.setText(m6);
        String m7 = Hand.get(6).type + "\n" + Hand.get(6).value;
        mcard7.setText(m7);
        String m8 = Hand.get(7).type + "\n" + Hand.get(7).value;
        mcard8.setText(m8);
        String oc1 = OpponentHand.get(0).type + "\n" + OpponentHand.get(0).value;
        xcard1.setText(oc1);
        xcard1.setVisibility(View.VISIBLE);
        TextView xcard2 = findViewById(R.id.opponentCard2);
        String oc2 = OpponentHand.get(1).type + "\n" + OpponentHand.get(1).value;
        xcard2.setText(oc2);
        xcard2.setVisibility(View.VISIBLE);
        TextView xcard3 = findViewById(R.id.opponentCard3);
        String oc3 = OpponentHand.get(2).type + "\n" + OpponentHand.get(2).value;
        xcard3.setText(oc3);
        xcard3.setVisibility(View.VISIBLE);
        TextView xcard4 = findViewById(R.id.opponentCard4);
        String oc4 = OpponentHand.get(3).type + "\n" + OpponentHand.get(3).value;
        xcard4.setText(oc4);
        xcard4.setVisibility(View.VISIBLE);
        TextView xcard5 = findViewById(R.id.opponentCard5);
        String oc5 = OpponentHand.get(4).type + "\n" + OpponentHand.get(4).value;
        xcard5.setText(oc5);
        xcard5.setVisibility(View.VISIBLE);
        TextView xcard6 = findViewById(R.id.opponentCard6);
        String oc6 = OpponentHand.get(5).type + "\n" + OpponentHand.get(5).value;
        xcard6.setText(oc6);
        xcard6.setVisibility(View.VISIBLE);
        TextView xcard7 = findViewById(R.id.opponentCard7);
        String oc7 = OpponentHand.get(6).type + "\n" + OpponentHand.get(6).value;
        xcard7.setText(oc7);
        xcard7.setVisibility(View.VISIBLE);
        TextView xcard8 = findViewById(R.id.opponentCard8);
        String oc8 = OpponentHand.get(7).type + "\n" + OpponentHand.get(7).value;
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