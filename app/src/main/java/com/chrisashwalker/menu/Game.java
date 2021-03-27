package com.chrisashwalker.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        startNew();
    }

    public Deck deck;
    ArrayDeque<Card> discardPile;
    int playerCount;
    ArrayList<Player> players;
    Player activePlayer;
    TextView deckView;
    TextView discardView;
    TextView finishView;
    HashMap<Integer, ArrayList<TextView>> playerViewLists;
    HashMap<Integer, TextView> playerBonusViews;
    ConstraintLayout gameLayout;
    Random random;

    public void startNew() {
        gameLayout = findViewById(R.id.gameLayout);
        playerCount = 2;
        deck = new Deck();
        discardPile = new ArrayDeque<>();
        random = new Random();
        players = new ArrayList<>();
        while (players.size() < playerCount) {
            players.add(new Player());
        }
        findViews();
        activePlayer = players.get(0);
        activePlayer.human = true;
        for (Player p : players) {
            p.hand = new Hand(deck);
            int highestPossibleScore = deck.types.size() * deck.countOfEachType + (deck.bonuses * deck.bonusValue);
            if (p.human) {
                p.goal = p.highScore + 1 <= highestPossibleScore ? p.highScore + 1 : p.highScore;
            } else {
                p.goal = random.nextInt(highestPossibleScore);
            }
        }
        updateViews(activePlayer);
        updateBonusViews(activePlayer);
        testFinished(activePlayer);
    }

    public void findViews() {
        deckView = findViewById(R.id.deckView);
        discardView = findViewById(R.id.discardView);
        finishView = findViewById(R.id.finishView);
        playerViewLists = new HashMap<>();
        playerBonusViews = new HashMap<>();
        for (Player p : players) {
            playerViewLists.put(p.id, new ArrayList<TextView>());
            playerBonusViews.put(p.id, null);
        }
    }

    public void updateViews(Player p) {
        ArrayList<TextView> viewList = playerViewLists.get(p.id);
        if (viewList != null) {
            if (viewList.isEmpty()) {
                for (int i = 0; i < gameLayout.getChildCount(); i++) {
                    if (gameLayout.getChildAt(i).getTag() != null && gameLayout.getChildAt(i).getTag().equals(getString(R.string.card))) {
                        viewList.add((TextView) gameLayout.getChildAt(i));
                    }
                }
            }
            for (TextView t : viewList) {
                String text = p.hand.cards.get(viewList.indexOf(t)).type + "\n" + p.hand.cards.get(viewList.indexOf(t)).value;
                t.setText(text);
                t.setTag(p.hand.cards.get(viewList.indexOf(t)));
            }
        }
    }

    public void updateBonusViews(Player p) {
        TextView view = playerBonusViews.get(p.id);
        if (view == null) {
            for (int i = 0; i < gameLayout.getChildCount(); i++) {
                if (gameLayout.getChildAt(i).getTag() != null && gameLayout.getChildAt(i).getTag().equals(getString(R.string.bonuses))) {
                    view = (TextView) gameLayout.getChildAt(i);
                    break;
                }
            }
        }
        if (p.hand.hasBonuses() && view != null) {
            view.setVisibility(View.VISIBLE);
            String bonusText = "Bonus points: " + p.hand.bonusScore;
            view.setText(bonusText);
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
            String viewText = discard != null ? discard.type + "\n" + discard.value : getString(R.string.discards);
            ((TextView) view).setText(viewText);
        }
    }

    public void takeDiscard() {
        Card discard = discardPile.peekFirst() instanceof Card ? discardPile.peekFirst() : null;
        if (discard != null && activePlayer.hand.cards.size() == Hand.size) {
            activePlayer.hand.addCard(discardPile.pollFirst());
            discard = discardPile.peekFirst() instanceof Card ? discardPile.peekFirst() : null;
            String viewText = discard != null ? discard.type + "\n" + discard.value : getString(R.string.discards);
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
                String viewText = deck.cards.peekFirst().type + "\n" + deck.cards.peekFirst().value;
                ((TextView) deckView).setText(viewText);
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
                    ((TextView) discardView).setText(viewText);
                    activePlayer.hand.cards.remove(i);
                    toggleFocus(discardView, false);
                    toggleFocus(deckView, false);
                    break;
                }
            }
            switchPlayer();
            updateViews(activePlayer);
        }
    }

    public void discard(Card c) {
        if (activePlayer.hand.cards.size() > Hand.size) {
            discardPile.offerFirst(c);
            String viewText = c.type + "\n" + c.value;
            discardView.setText(viewText);
            activePlayer.hand.cards.remove(c);
            switchPlayer();
            updateViews(activePlayer);
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
                ArrayList<String> foundTypes = new ArrayList<>();
                Card lowestValueCard = null;
                for (Card c : activePlayer.hand.cards) {
                    foundTypes.add(c.type);
                    if (lowestValueCard == null) {
                        lowestValueCard = c;
                        continue;
                    }
                    if (foundTypes.contains(c.type) && c.value <= lowestValueCard.value) {
                        lowestValueCard = c;
                    }
                }
                discard(lowestValueCard);
            }

        }
        switchPlayer();
        updateViews(activePlayer);
    }

    public void finishGame(View view) {
        setContentView(R.layout.activity_result);
        String result;
        StringBuilder equalScorers = new StringBuilder();
        StringBuilder scores = new StringBuilder();
        ArrayList<Player> highestScorers = new ArrayList<>();
        for (Player p : players) {
            updateViews(p);
            updateBonusViews(p);
            if (highestScorers.isEmpty() || p.hand.getScore() > highestScorers.get(0).hand.getScore()) {
                highestScorers.clear();
                highestScorers.add(p);
                equalScorers.delete(0, equalScorers.length());
                equalScorers.append("P").append(p.id);
            } else if (p.hand.getScore() == highestScorers.get(0).hand.getScore()) {
                highestScorers.add(p);
                equalScorers.append(" & P").append(p.id);
            }
            scores.append("P").append(p.id).append(":").append(p.hand.getScore()).append("; ");
        }
        if (highestScorers.size() > 1) {
            result = "There's a draw. " + equalScorers;
        } else {
            result = "P " + highestScorers.get(0).id + " wins!";
        }
        TextView resultView = findViewById(R.id.resultView);
        resultView.setText(result);
    }

}
