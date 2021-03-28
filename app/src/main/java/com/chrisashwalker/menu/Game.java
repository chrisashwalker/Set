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

    private boolean deckTaken;
    private boolean discardTaken;
    
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
        findMissingCardTypes(activePlayer);
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
            Card card = p.getCards().get(viewList.indexOf(t));
            String text = card.getType() + "\n" + card.getValue();
            t.setText(text);
            t.setTag(card);
        }
        updateBonusViews(p);
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

    private void toggleFocus(View view, boolean focus) {
        if (focus) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFocused));
        } else {
            view.setBackgroundResource(0);
        }
    }

    private ArrayList<String> findMissingCardTypes(Player p){
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

    private void switchPlayer() {
        int nextPlayer = players.indexOf(activePlayer) + 1 <= players.size() - 1 ? players.indexOf(activePlayer) + 1 : 0;
        activePlayer = players.get(nextPlayer);
        updateViews(activePlayer);
        if (!activePlayer.checkIsHuman()) {
            autoPlay();
        }
    }

    public void takeDeck(View view) {
        Card topCard = deck.getCards().peekFirst() instanceof Card ? deck.getCards().peekFirst() : null;
        if (topCard != null && !deckTaken && activePlayer.getCards().size() == activePlayer.getHand().getCapacity()) {
            if (topCard.getType().equals(deck.getBonusType())) {
                activePlayer.getHand().addBonus(deck.getCards().pollFirst());
                updateBonusViews(activePlayer);
            } else {
                deckTaken = true;
                activePlayer.getHand().addCard(deck.getCards().pollFirst());
                if (view != null) {
                    String viewText = topCard.getType() + "\n" + topCard.getValue();
                    ((TextView) view).setText(viewText);
                    toggleFocus(view, true);
                }
            }
        } else if (deckTaken) {
            discard(activePlayer.getCards().get(activePlayer.getHand().getCapacity()));
        }
    }

    public void takeDiscard(View view) {
        Card discard = discards.peekFirst() instanceof Card ? discards.peekFirst() : null;
        if (discard != null && !discardTaken && activePlayer.getCards().size() == activePlayer.getHand().getCapacity()) {
            discardTaken = true;
            toggleFocus(view, true);
            activePlayer.getHand().addCard(discards.pollFirst());
        } else if (discardTaken) {
            discard(activePlayer.getCards().get(activePlayer.getHand().getCapacity()));
        }
    }

    public void discard(View view) {
        if (activePlayer.getCards().size() > activePlayer.getHand().getCapacity()) {
            Card discarded = (Card) view.getTag();
            discard(discarded);
        }
    }

    private void discard(Card c) {
        if (activePlayer.getCards().size() > activePlayer.getHand().getCapacity()) {
            if (discards.peekLast() != null) {
                deck.addCard(discards.pollLast());
            }
            activePlayer.getHand().removeCard(c);
            discards.offerFirst(c);
            String viewText = c.getType() + "\n" + c.getValue();
            discardView.setText(viewText);
            deckView.setText(R.string.deck);
            deckTaken = false;
            discardTaken = false;
            toggleFocus(discardView, false);
            toggleFocus(deckView, false);
            switchPlayer();
        }
    }

    private void autoPlay() {
        ArrayList<String> missingTypes = findMissingCardTypes(activePlayer);
        if (missingTypes.isEmpty() && activePlayer.getGoal() >= activePlayer.getHand().getTotalScore()) {
            finishGame(finishView);
        } else {
            Card discard = discards.peekFirst() instanceof Card ? discards.peekFirst() : null;
            if (discard != null) {
                if (missingTypes.contains(discard.getType())) {
                    takeDiscard(discardView);
                } else {
                    takeDeck(deckView);
                }
            } else {
                takeDeck(deckView);
            }
            if (activePlayer.getCards().size() > activePlayer.getHand().getCapacity()) {
                ArrayList<String> foundTypes = new ArrayList<>();
                Card lowestValueCard = null;
                for (Card c : activePlayer.getCards()) {
                    if (lowestValueCard == null) {
                        lowestValueCard = c;
                    }
                    if (!foundTypes.contains(c.getType())) {
                        foundTypes.add(c.getType());
                    } else if (c.getValue() <= lowestValueCard.getValue()) {
                        lowestValueCard = c;
                    }
                }
                discard(lowestValueCard);
            }
        }
        switchPlayer();
    }

    public void finishGame(View view) {
        setContentView(R.layout.activity_result);
        String result;
        StringBuilder equalScorers = new StringBuilder();
        StringBuilder scores = new StringBuilder();
        ArrayList<Player> highestScorers = new ArrayList<>();
        for (Player p : players) {
            updateViews(p);
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

    public void launchMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
