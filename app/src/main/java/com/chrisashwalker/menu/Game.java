package com.chrisashwalker.menu;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Game extends AppCompatActivity {

    public static Deck deck;
    int playerCount = 2;
    ArrayList<Player> players;
    TextView deckView;
    TextView discardView;
    TextView finishView;
    TextView bonusView;
    HashMap<Integer, ArrayList<TextView>> playerViewLists;
    ConstraintLayout gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
    Random random = new Random();

    public void startNew() {
        deck = new Deck();
        players = new ArrayList<>();
        while (players.size() < playerCount) {
            players.add(new Player());
        }
        setContentView(R.layout.activity_play);
        findViews();
        players.get(0).human = true;
        updateViews(players.get(0));
        for (Player p : players) {
            if (!p.human) {
                p.goal = random.nextInt(deck.types.length * deck.countOfEachType + (deck.bonuses * deck.bonusValue));
            }
        }
        testFinished();
    }

    public void findViews() {
        deckView = findViewById(R.id.deckView);
        discardView = findViewById(R.id.discardView);
        finishView = findViewById(R.id.finishView);
        bonusView = findViewById(R.id.bonusView1);
        playerViewLists = new HashMap<>();
        for (Player p : players) {
            playerViewLists.put(p.id, new ArrayList<TextView>());
        }
    }

    public void updateViews(Player p) {
        ArrayList<TextView> viewList = playerViewLists.get(p.id);
        if (viewList != null) {
            for (int i = 0; i < gameLayout.getChildCount(); i++) {
                if (gameLayout.getChildAt(i).getTag().equals(R.string.card)) {
                    viewList.add((TextView) gameLayout.getChildAt(i));
                }
            }
            for (TextView t : viewList) {
                String text = p.hand.cards.get(viewList.indexOf(t)).type + "\n" + p.hand.cards.get(viewList.indexOf(t)).value;
                t.setText(text);
            }
        }
        if (p.hand.hasBonuses()) {
            bonusView.setVisibility(View.VISIBLE);
            String bonusText = "Bonus points: " + p.hand.bonusScore;
            bonusView.setText(bonusText);
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
            TopOfDeck = Deck.get(0);
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
                TopOfDeck = Deck.get(0);
                Deck.remove(TopOfDeck);
                Deck.add(Discarded);
                if (TopOfDeck.type.equals(bonusType)) {
                    OpponentBonusHand.add(TopOfDeck);
                    while (TopOfDeck.type.equals(bonusType)) {
                        TopOfDeck = Deck.get(0);
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
        TextView bonusView = findViewById(R.id.bonusView1);
        HandViews.clear();
        HandViews.addAll(Arrays.asList(
                cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8));

        TextView opponentBonusView = findViewById(R.id.bonusView2);
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
