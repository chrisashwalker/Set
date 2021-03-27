package com.chrisashwalker.menu;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Game extends AppCompatActivity {

    public Deck deck;
    ArrayDeque<Card> discardPile;
    int playerCount;
    ArrayList<Player> players;
    Player activePlayer;
    TextView deckView;
    TextView discardView;
    TextView finishView;
    TextView bonusView;
    HashMap<Integer, ArrayList<TextView>> playerViewLists;
    ConstraintLayout gameLayout = findViewById(R.id.gameLayout);
    Random random;

    public void startNew() {
        playerCount = 2;
        deck = new Deck();
        discardPile = new ArrayDeque<>();
        random = new Random();
        players = new ArrayList<>();
        while (players.size() < playerCount) {
            players.add(new Player());
        }
        setContentView(R.layout.activity_play);
        findViews();
        activePlayer = players.get(0);
        activePlayer.human = true;
        updateViews(activePlayer);
        updateBonusViews(activePlayer);
        for (Player p : players) {
            p.hand = new Hand(deck);
            int highestPossibleScore = deck.types.size() * deck.countOfEachType + (deck.bonuses * deck.bonusValue);
            if (p.human) {
                p.goal = p.highScore + 1 <= highestPossibleScore ? p.highScore + 1 : p.highScore;
            } else {
                p.goal = random.nextInt(highestPossibleScore);
            }
        }
        testFinished(activePlayer);
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
    }

    public void updateBonusViews(Player p) {
        if (p.hand.hasBonuses()) {
            bonusView.setVisibility(View.VISIBLE);
            String bonusText = "Bonus points: " + p.hand.bonusScore;
            bonusView.setText(bonusText);
        }
    }

    public ArrayList<String> testFinished(Player p){
        ArrayList<String> missingTypes = new ArrayList<>(deck.types);
        for (Card c : p.hand.cards) {
            missingTypes.remove(c.type);
        }
        if (missingTypes.isEmpty() && p.hand.cards.size() == Hand.size) {
            finishView.setVisibility(View.VISIBLE);
        } else {
            finishView.setVisibility(View.INVISIBLE);
        }
        return missingTypes;
    }

    public void toggleFocus(View view, boolean focus) {
        if (focus) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFocused));
        } else {
            view.setBackgroundResource(0);
        }
    }

    public void takeDiscard(View view) {
        Card discard = discardPile.peekFirst() instanceof Card ? discardPile.peekFirst() : null;
        if (discard != null && activePlayer.hand.cards.size() == Hand.size) {
            toggleFocus(view, true);
            activePlayer.hand.addCard(discardPile.pollFirst());
            discard = discardPile.peekFirst() instanceof Card ? discardPile.peekFirst() : null;
            String viewText = discard != null ? discard.type + "\n" + discard.value : String.valueOf(R.string.discards);
            ((TextView) view).setText(viewText);
        }
    }

    public void takeDiscard() {
        Card discard = discardPile.peekFirst() instanceof Card ? discardPile.peekFirst() : null;
        if (discard != null && activePlayer.hand.cards.size() == Hand.size) {
            activePlayer.hand.addCard(discardPile.pollFirst());
            discard = discardPile.peekFirst() instanceof Card ? discardPile.peekFirst() : null;
            String viewText = discard != null ? discard.type + "\n" + discard.value : String.valueOf(R.string.discards);
            discardView.setText(viewText);
        }
    }

    public void takeDeck(View view) {
        Card topCard = deck.cards.peekFirst() instanceof Card ? deck.cards.peekFirst() : null;
        if (topCard != null && activePlayer.hand.cards.size() == Hand.size) {
            if (topCard.type.equals(deck.bonusType)) {
                activePlayer.hand.bonuses.add(deck.cards.pollFirst());
                updateBonusViews(activePlayer);
            } else {
                activePlayer.hand.addCard(deck.cards.pollFirst());
                toggleFocus(view, true);
            }
        }
    }

    public void takeDeck() {
        Card topCard = deck.cards.peekFirst() instanceof Card ? deck.cards.peekFirst() : null;
        if (topCard != null && activePlayer.hand.cards.size() == Hand.size) {
            if (topCard.type.equals(deck.bonusType)) {
                activePlayer.hand.bonuses.add(deck.cards.pollFirst());
                updateBonusViews(activePlayer);
            } else {
                activePlayer.hand.addCard(deck.cards.pollFirst());
            }
        }
    }

    public void discard(View view) {
        if (activePlayer.hand.cards.size() > Hand.size) {
            for (int i = 0; i < gameLayout.getChildCount(); i++) {
                if (gameLayout.getChildAt(i).equals(view)) {
                    discardPile.offerFirst(activePlayer.hand.cards.get(i));
                    String viewText = activePlayer.hand.cards.get(i).type + "\n" + activePlayer.hand.cards.get(i).value;
                    ((TextView) view).setText(viewText);
                    activePlayer.hand.cards.remove(i);
                    break;
                }
            }
            switchPlayer();
        }
    }

    public void switchPlayer() {
        int nextPlayer = players.indexOf(activePlayer) + 1 <= players.size() - 1 ? players.indexOf(activePlayer) + 1 : 0;
        activePlayer = players.get(nextPlayer);
        if (!activePlayer.human) {
            autoPlay();
        }
    }

    public void autoPlay() {
        ArrayList<String> missingTypes = testFinished(activePlayer);
        if (missingTypes.isEmpty() && activePlayer.goal >= activePlayer.hand.getScore()) {
            finishGame(finishView);
        } else {
            Card discard = discardPile.peekFirst() instanceof Card ? discardPile.peekFirst() : null;
            if (discard != null) {
                if (missingTypes.contains(discard.type)) {
                    takeDiscard();
                } else {
                    takeDeck();
                }
            }
            if (activePlayer.hand.cards.size() > Hand.size) {
                Card lowestValueCard = null;
                for (Card c : activePlayer.hand.cards) {
                    if (lowestValueCard == null) {
                        lowestValueCard = c;
                        continue;
                    }
                    if (c.type)

                }
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
        switchPlayer();
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
