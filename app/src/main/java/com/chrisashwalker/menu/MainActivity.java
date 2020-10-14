package com.chrisashwalker.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    ArrayList<Card> cardsOfSameType = new ArrayList<>();

    Boolean discardTaken = false;
    Boolean deckPicked = false;

    int bonusCount = 0;
    int opponentBonusCount = 0;
    int opponentScoreTest = 0;
    int opponentScoreGoal = 0;

    String bonusType = "Waiter";
    String bonusCountText = "";
    String opponentBonusCountText = "";
    String TopOfDeckText = "";
    String DiscardedText = "";
    String CardText = "";
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

    Random random = new Random();
    int randomInt;

    TextView discardView;
    TextView deckView;
    TextView bonusView;
    TextView opponentBonusView;
    TextView finishView;
    TextView cardView1;
    TextView cardView2;
    TextView cardView3;
    TextView cardView4;
    TextView cardView5;
    TextView cardView6;
    TextView cardView7;
    TextView cardView8;

    public void playGame(View view) {
        setContentView(R.layout.activity_play);
        discardView = findViewById(R.id.discardView);
        deckView = findViewById(R.id.deckView);
        bonusView = findViewById(R.id.bonusView);
        opponentBonusView = findViewById(R.id.opponentBonusView);
        finishView = findViewById(R.id.finishView);
        cardView1 = findViewById(R.id.card1);
        cardView2 = findViewById(R.id.card2);
        cardView3 = findViewById(R.id.card3);
        cardView4 = findViewById(R.id.card4);
        cardView5 = findViewById(R.id.card5);
        cardView6 = findViewById(R.id.card6);
        cardView7 = findViewById(R.id.card7);
        cardView8 = findViewById(R.id.card8);
        HandViews.clear();
        HandViews.addAll(Arrays.asList(
                cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8));
        Hand.clear();
        OpponentHand.clear();
        BonusHand.clear();
        OpponentBonusHand.clear();
        Deck.clear();
        TopOfDeck = BlankCard;
        Discarded = BlankCard;
        bonusCount = 0;
        opponentBonusCount = 0;
        buildDeck();
        dealCards();
        Deck.addAll(Bonuses);
        opponentScoreTest = 0;
        opponentScoreGoal = random.nextInt(41) + 17;
        testFinished();
    }

    public void buildDeck() {
        Deck.addAll(Arrays.asList(Card1,Card2,Card3,Card4,Card5,Card6,Card7,Card8,Card9,Card10,
                Card11,Card12,Card13,Card14,Card15,Card16,Card17,Card18,Card19,Card20,
                Card21,Card22,Card23,Card24,Card25,Card26,Card27,Card28,Card29,Card30,
                Card31,Card32,Card33,Card34,Card35,Card36,Card37));

        Bonuses.clear();
        Bonuses.add(BonusCard1);
        Bonuses.add(BonusCard2);
        Bonuses.add(BonusCard3);

        RequiredSet.clear();
        RequiredSet.addAll(Arrays.asList(cardType1,cardType2,cardType3,cardType4,
                cardType5,cardType6,cardType7,cardType8));
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
        }
    }

    public void testFinished(){
        TestSet.clear();
        RequiredMissing.clear();
        RequiredMissing.addAll(RequiredSet);
        for (Card card : Hand) {
            TestSet.add(card.type);
        }
        RequiredMissing.removeAll(TestSet);
        if (RequiredMissing.isEmpty()) {
            finishView.setVisibility(View.VISIBLE);
        } else {
            finishView.setVisibility(View.INVISIBLE);
        }
    }

    public void takePile(View view) {
        if (TopOfDeck.value == 0 && Discarded.value > 0) {
            discardView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFocused));
            discardTaken = true;
        }
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
                bonusView.setVisibility(View.VISIBLE);
            } else {
                bonusCount = BonusHand.size();
                bonusCountText = bonusCount + " " + bonusType + "(s)";
                TopOfDeckText = TopOfDeck.type + "\n" + TopOfDeck.value;
                bonusView.setText(bonusCountText);
                deckView.setText(TopOfDeckText);
                deckView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.colorFocused));
            }
        } else if (deckPicked){
            if (Discarded.value > 0) {
                Deck.add(Discarded);
            }
            Discarded = TopOfDeck;
            TopOfDeck = BlankCard;
            deckView.setBackgroundResource(0);
            deckView.setText(R.string.deck);
            deckPicked = false;
            opponentPlay();
        }
    }

    public void opponentPlay() {
        TestSet.clear();
        RequiredMissing.clear();
        RequiredMissing.addAll(RequiredSet);
        opponentScoreTest = 0;
        for (Card card : OpponentHand) {
            TestSet.add(card.type);
            opponentScoreTest += card.value;
        }
        RequiredMissing.removeAll(TestSet);
        if (RequiredMissing.isEmpty() && opponentScoreTest >= opponentScoreGoal) {
            finishGame(finishView);
        } else {
            if (RequiredMissing.contains(Discarded.type)) {
                OpponentHand.add(Discarded);
                Discarded = BlankCard;
                discardView.setText(R.string.discards);
            } else {
                int randomInt = random.nextInt(Deck.size());
                TopOfDeck = Deck.get(randomInt);
                Deck.remove(TopOfDeck);
                if (TopOfDeck.type.equals(bonusType)) {
                    OpponentBonusHand.add(TopOfDeck);
                    while (TopOfDeck.type.equals(bonusType)) {
                        randomInt = random.nextInt(Deck.size());
                        TopOfDeck = Deck.get(randomInt);
                        Deck.remove(TopOfDeck);
                        if (TopOfDeck.type.equals(bonusType)) {
                            OpponentBonusHand.add(TopOfDeck);
                        }
                    }
                }
                if (OpponentBonusHand.size() > 0){
                    opponentBonusCount = OpponentBonusHand.size();
                    opponentBonusCountText = opponentBonusCount + " " + bonusType + "(s)";
                    opponentBonusView.setText(opponentBonusCountText);
                    opponentBonusView.setVisibility(View.VISIBLE);
                }
                OpponentHand.add(TopOfDeck);
                TopOfDeck = BlankCard;
            }
            TestSet.clear();
            for (Card card : OpponentHand) {
                TestSet.add(card.type);
            }
            for (String type : RequiredSet) {
                TestSet.remove(type);
            }
            String firstTypePicked = TestSet.get(0);
            cardsOfSameType.clear();
            for (Card card : OpponentHand) {
                if (firstTypePicked.equals(card.type)) {
                    cardsOfSameType.add(card);
                }
            }
            if (cardsOfSameType.get(0).value < cardsOfSameType.get(1).value) {
                Discarded = cardsOfSameType.get(0);
            } else {
                Discarded = cardsOfSameType.get(1);
            }
            OpponentHand.remove(Discarded);
            DiscardedText = Discarded.type + "\n" + Discarded.value;
            discardView.setText(DiscardedText);
        }
    }

    public void replaceCard(View view) {
        String cardId = view.getResources().getResourceEntryName(view.getId());
        int cardIndex = Integer.parseInt(cardId.replace("card","")) - 1;
        if (deckPicked) {
            if (Discarded.value > 0) {
                Deck.add(Discarded);
            }
            Discarded = Hand.get(cardIndex);
            Hand.set(cardIndex, TopOfDeck);
            CardText = Hand.get(cardIndex).type + "\n" + Hand.get(cardIndex).value;
            HandViews.get(cardIndex).setText(CardText);
            TopOfDeck = BlankCard;
            deckView.setBackgroundResource(0);
            deckView.setText(R.string.deck);
            DiscardedText = Discarded.type + "\n" + Discarded.value;
            discardView.setText(DiscardedText);
            deckPicked = false;
            opponentPlay();
        } else if (discardTaken) {
            Card replacedCard = Hand.get(cardIndex);
            Hand.set(cardIndex, Discarded);
            Discarded = replacedCard;
            CardText = Hand.get(cardIndex).type + "\n" + Hand.get(cardIndex).value;
            HandViews.get(cardIndex).setText(CardText);
            discardView.setBackgroundResource(0);
            DiscardedText = Discarded.type + "\n" + Discarded.value;
            discardView.setText(DiscardedText);
            discardTaken = false;
            opponentPlay();
        }
        testFinished();
    }

    public void finishGame(View view) {
        setContentView(R.layout.activity_result);
        TextView resultView = findViewById(R.id.resultView);
        TextView cardView1 = findViewById(R.id.card1);
        TextView cardView2 = findViewById(R.id.card2);
        TextView cardView3 = findViewById(R.id.card3);
        TextView cardView4 = findViewById(R.id.card4);
        TextView cardView5 = findViewById(R.id.card5);
        TextView cardView6 = findViewById(R.id.card6);
        TextView cardView7 = findViewById(R.id.card7);
        TextView cardView8 = findViewById(R.id.card8);
        TextView bonusView = findViewById(R.id.bonusView);
        HandViews.clear();
        HandViews.addAll(Arrays.asList(
                cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8));

        TextView opponentBonusView = findViewById(R.id.opponentBonusView);
        TextView opponentCardView1 = findViewById(R.id.opponentCard1);
        TextView opponentCardView2 = findViewById(R.id.opponentCard2);
        TextView opponentCardView3 = findViewById(R.id.opponentCard3);
        TextView opponentCardView4 = findViewById(R.id.opponentCard4);
        TextView opponentCardView5 = findViewById(R.id.opponentCard5);
        TextView opponentCardView6 = findViewById(R.id.opponentCard6);
        TextView opponentCardView7 = findViewById(R.id.opponentCard7);
        TextView opponentCardView8 = findViewById(R.id.opponentCard8);
        OpponentHandViews.clear();
        OpponentHandViews.addAll(Arrays.asList(
                opponentCardView1,opponentCardView2,opponentCardView3,opponentCardView4,
                opponentCardView5,opponentCardView6,opponentCardView7,opponentCardView8));
        bonusCount = BonusHand.size();
        opponentBonusCount = OpponentBonusHand.size();
        bonusCountText = bonusCount + " " + bonusType + "(s)";
        opponentBonusCountText = opponentBonusCount + " " + bonusType + "(s)";
        bonusView.setText(bonusCountText);
        opponentBonusView.setText(opponentBonusCountText);
        String results = "";
        int playerScore = 0;
        int opponentScore = 0;
        for (int i = 0; i < 8; i++) {
            String handText = Hand.get(i).type + "\n" + Hand.get(i).value;
            String opponentHandText = OpponentHand.get(i).type + "\n" + OpponentHand.get(i).value;
            HandViews.get(i).setText(handText);
            OpponentHandViews.get(i).setText(opponentHandText);
            OpponentHandViews.get(i).setVisibility(View.VISIBLE);
        }
        for (Card card : Hand) {
            for (Card cardtest : Hand) {
                if (card.type.equals(cardtest.type) && card.value < cardtest.value) {
                    playerScore -= card.value;
                    break;
                }
            }
            playerScore += card.value;
        }
        for (Card card : OpponentHand) {
            for (Card cardtest : OpponentHand) {
                if (card.type.equals(cardtest.type) && card.value < cardtest.value) {
                    opponentScore -= card.value;
                    break;
                }
            }
            opponentScore += card.value;
        }
        for (Card card : BonusHand) {
            playerScore += card.value;
        }
        for (Card card : OpponentBonusHand) {
            opponentScore += card.value;
        }
        if (playerScore > opponentScore) {
            results = "You win! Your score: " + playerScore + "; Opponent score: " + opponentScore;
        } else if (playerScore == opponentScore) {
            results = "It's a draw. Your score: " + playerScore + "; Opponent score: " + opponentScore;
        }
        if (playerScore < opponentScore) {
            results = "You lose. Your score: " + playerScore + "; Opponent score: " + opponentScore;
        }
        resultView.setText(results);
    }
}
