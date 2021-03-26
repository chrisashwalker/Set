package com.chrisashwalker.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {



    ArrayList<Card> Deck = new ArrayList<>();
    ArrayList<Card> Hand = new ArrayList<>();
    ArrayList<Card> OpponentHand = new ArrayList<>();
    ArrayList<Card> BonusHand = new ArrayList<>();
    ArrayList<Card> OpponentBonusHand = new ArrayList<>();
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
        opponentScoreTest = 0;
        opponentScoreGoal = random.nextInt(41) + 17;
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
            for (Card cardTest : Hand) {
                if (card.type.equals(cardTest.type) && card.value < cardTest.value) {
                    playerScore -= card.value;
                    break;
                }
            }
            playerScore += card.value;
        }
        for (Card card : OpponentHand) {
            for (Card cardTest : OpponentHand) {
                if (card.type.equals(cardTest.type) && card.value < cardTest.value) {
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
