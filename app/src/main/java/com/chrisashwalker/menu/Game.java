package com.chrisashwalker.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends AppCompatActivity {
    
    private Deck deck;
    private ArrayDeque<Card> discards;
    private ArrayList<Player> players;
    private Player activePlayer;

    private ConstraintLayout gameLayout;
    private TextView deckView;
    private TextView discardView;
    private TextView finishView;
    private HashMap<Integer, ArrayList<TextView>> playerViewLists;
    private HashMap<Integer, TextView> playerBonusViews;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        startNew();
    }

    private void startNew() {
        deck = new Deck();
        discards = new ArrayDeque<>();
        int humanPlayerCount = 1;
        int cpuPlayerCount = 1;
        players = Player.assemble(deck,humanPlayerCount,cpuPlayerCount);
        activePlayer = players.get(0);
        findViews();
        updateViews(activePlayer);
        updateBonusViews(activePlayer);
        checkFullSetObtained(activePlayer);
    }

    private void findViews() {
        gameLayout = findViewById(R.id.gameLayout);
        deckView = findViewById(R.id.deckView);
        discardView = findViewById(R.id.discardView);
        finishView = findViewById(R.id.finishView);
        playerViewLists = new HashMap<>();
        playerBonusViews = new HashMap<>();
        for (Player p : players) {
            playerViewLists.put(p.getId(), new ArrayList<TextView>());
            playerBonusViews.put(p.getId(), null);
        }
    }

    private void updateViews(Player p) {
        ArrayList<TextView> viewList = playerViewLists.get(p.getId());
        assert viewList != null;
        if (viewList.isEmpty()) {
            for (int i = 0; i < gameLayout.getChildCount(); i++) {
                if (gameLayout.getChildAt(i).getTag() != null && gameLayout.getChildAt(i).getTag().equals(getString(R.string.card))) {
                    viewList.add((TextView) gameLayout.getChildAt(i));
                }
            }
        }
        for (TextView t : viewList) {
            String text = p.getCards().get(viewList.indexOf(t)).getType() + "\n" + p.getCards().get(viewList.indexOf(t)).getValue();
            t.setText(text);
            t.setTag(p.getCards().get(viewList.indexOf(t)));
        }
    }

    private void updateBonusViews(Player p) {
        TextView view = playerBonusViews.get(p.getId());
        if (view == null) {
            for (int i = 0; i < gameLayout.getChildCount(); i++) {
                if (gameLayout.getChildAt(i).getTag() != null && gameLayout.getChildAt(i).getTag().equals(getString(R.string.bonuses))) {
                    view = (TextView) gameLayout.getChildAt(i);
                    break;
                }
            }
        }
        if (p.getHand().hasBonuses() && view != null) {
            view.setVisibility(View.VISIBLE);
            String bonusText = "Bonus points: " + p.getHand().getBonusScore();
            view.setText(bonusText);
        }
    }

    private ArrayList<String> checkFullSetObtained(Player p){
        ArrayList<String> missingTypes = new ArrayList<>(deck.getCardTypes());
        for (Card c : p.getCards()) {
            missingTypes.remove(c.getType());
        }
        if (missingTypes.isEmpty() && p.getCards().size() == p.getHand().getCapacity()) {
            finishView.setVisibility(View.VISIBLE);
        } else {
            finishView.setVisibility(View.INVISIBLE);
        }
        return missingTypes;
    }

    private void toggleFocus(View view, boolean focus) {
        if (focus) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFocused));
        } else {
            view.setBackgroundResource(0);
        }
    }

    private void takeDiscard(View view) {
        Card discard = discards.peekFirst() instanceof Card ? discards.peekFirst() : null;
        if (discard != null && activePlayer.getCards().size() == activePlayer.getHand().getCapacity()) {
            toggleFocus(view, true);
            activePlayer.getHand().addCard(discards.pollFirst());
            discard = discards.peekFirst() instanceof Card ? discards.peekFirst() : null;
            String viewText = discard != null ? discard.getType() + "\n" + discard.getValue() : getString(R.string.discards);
            ((TextView) view).setText(viewText);
        }
    }

    private void takeDiscard() {
        Card discard = discards.peekFirst() instanceof Card ? discards.peekFirst() : null;
        if (discard != null && activePlayer.getCards().size() == activePlayer.getHand().getCapacity()) {
            activePlayer.getHand().addCard(discards.pollFirst());
            discard = discards.peekFirst() instanceof Card ? discards.peekFirst() : null;
            String viewText = discard != null ? discard.getType() + "\n" + discard.getValue() : getString(R.string.discards);
            discardView.setText(viewText);
        }
    }

    private void takeDeck(View view) {
        Card topCard = deck.getCards().peekFirst() instanceof Card ? deck.getCards().peekFirst() : null;
        if (topCard != null && activePlayer.getCards().size() == activePlayer.getHand().getCapacity()) {
            if (topCard.getType().equals(deck.getBonusType())) {
                activePlayer.getHand().addBonus(deck.getCards().pollFirst());
                updateBonusViews(activePlayer);
            } else {
                String viewText = topCard.getType() + "\n" + topCard.getValue();
                ((TextView) deckView).setText(viewText);
                activePlayer.getHand().addCard(deck.getCards().pollFirst());
                toggleFocus(view, true);
            }
        }
    }

    private void takeDeck() {
        Card topCard = deck.getCards().peekFirst() instanceof Card ? deck.getCards().peekFirst() : null;
        if (topCard != null && activePlayer.getCards().size() == activePlayer.getHand().getCapacity()) {
            if (topCard.getType().equals(deck.getBonusType())) {
                activePlayer.getHand().addBonus(deck.getCards().pollFirst());
                updateBonusViews(activePlayer);
            } else {
                activePlayer.getHand().addCard(deck.getCards().pollFirst());
            }
        }
    }

    private void discard(View view) {
        if (activePlayer.getCards().size() > activePlayer.getHand().getCapacity()) {
            for (int i = 0; i < gameLayout.getChildCount(); i++) {
                if (gameLayout.getChildAt(i).equals(view)) {
                    discards.offerFirst(activePlayer.getCards().get(i));
                    String viewText = activePlayer.getCards().get(i).getType() + "\n" + activePlayer.getCards().get(i).getValue();
                    ((TextView) discardView).setText(viewText);
                    activePlayer.getHand().removeCard(i);
                    toggleFocus(discardView, false);
                    toggleFocus(deckView, false);
                    break;
                }
            }
            switchPlayer();
            updateViews(activePlayer);
        }
    }

    private void discard(Card c) {
        if (activePlayer.getCards().size() > activePlayer.getHand().getCapacity()) {
            discards.offerFirst(c);
            String viewText = c.getType() + "\n" + c.getValue();
            discardView.setText(viewText);
            activePlayer.getHand().removeCard(c);
            switchPlayer();
            updateViews(activePlayer);
        }
    }

    private void switchPlayer() {
        int nextPlayer = players.indexOf(activePlayer) + 1 <= players.size() - 1 ? players.indexOf(activePlayer) + 1 : 0;
        activePlayer = players.get(nextPlayer);
        if (!activePlayer.checkIsHuman()) {
            autoPlay();
        }
    }

    private void autoPlay() {
        ArrayList<String> missingTypes = checkFullSetObtained(activePlayer);
        if (missingTypes.isEmpty() && activePlayer.getGoal() >= activePlayer.getHand().getTotalScore()) {
            finishGame(finishView);
        } else {
            Card discard = discards.peekFirst() instanceof Card ? discards.peekFirst() : null;
            if (discard != null) {
                if (missingTypes.contains(discard.getType())) {
                    takeDiscard();
                } else {
                    takeDeck();
                }
            }
            if (activePlayer.getCards().size() > activePlayer.getHand().getCapacity()) {
                ArrayList<String> foundTypes = new ArrayList<>();
                Card lowestValueCard = null;
                for (Card c : activePlayer.getCards()) {
                    foundTypes.add(c.getType());
                    if (lowestValueCard == null) {
                        lowestValueCard = c;
                        continue;
                    }
                    if (foundTypes.contains(c.getType()) && c.getValue() <= lowestValueCard.getValue()) {
                        lowestValueCard = c;
                    }
                }
                discard(lowestValueCard);
            }

        }
        switchPlayer();
        updateViews(activePlayer);
    }

    private void finishGame(View view) {
        setContentView(R.layout.activity_result);
        String result;
        StringBuilder equalScorers = new StringBuilder();
        StringBuilder scores = new StringBuilder();
        ArrayList<Player> highestScorers = new ArrayList<>();
        for (Player p : players) {
            updateViews(p);
            updateBonusViews(p);
            if (highestScorers.isEmpty() || p.getHand().getTotalScore() > highestScorers.get(0).getHand().getTotalScore()) {
                highestScorers.clear();
                highestScorers.add(p);
                equalScorers.delete(0, equalScorers.length());
                equalScorers.append("P").append(p.getId());
            } else if (p.getHand().getTotalScore() == highestScorers.get(0).getHand().getTotalScore()) {
                highestScorers.add(p);
                equalScorers.append(" & P").append(p.getId());
            }
            scores.append("P").append(p.getId()).append(":").append(p.getHand().getTotalScore()).append("; ");
        }
        if (highestScorers.size() > 1) {
            result = "There's a draw. " + equalScorers;
        } else {
            result = "P " + highestScorers.get(0).getId() + " wins!";
        }
        TextView resultView = findViewById(R.id.resultView);
        resultView.setText(result);
    }

    protected void launchMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
}
