package com.chrisashwalker.menu;

import android.view.View;
import androidx.core.content.ContextCompat;

public class Hand {

    public void dealCards() {
        Card dealtCard;
        String cardText;
        while (Hand.size() < 8) {
            dealtCard = Deck.get(0);
            while (dealtCard.type.equals(bonusType)) {
                BonusHand.add(dealtCard);
                Deck.remove(dealtCard);
                dealtCard = Deck.get(0);
            }
            Deck.remove(dealtCard);
            Hand.add(dealtCard);
            cardText = dealtCard.type + "\n" + dealtCard.value;
            HandViews.get(Hand.size() - 1).setText(cardText);
        }
        while (OpponentHand.size() < 8) {
            dealtCard = Deck.get(0);
            while (dealtCard.type.equals(bonusType)) {
                OpponentBonusHand.add(dealtCard);
                Deck.remove(dealtCard);
                dealtCard = Deck.get(0);
            }
            Deck.remove(dealtCard);
            OpponentHand.add(dealtCard);
        }
        if (BonusHand.size() > 0) {
            bonusView.setVisibility(View.VISIBLE);
            bonusCount = BonusHand.size();
            bonusCountText = bonusCount + " " + bonusType + "(s)";
            bonusView.setText(bonusCountText);
        }
        if (OpponentBonusHand.size() > 0) {
            opponentBonusView.setVisibility(View.VISIBLE);
            opponentBonusCount = OpponentBonusHand.size();
            opponentBonusCountText = opponentBonusCount + " " + bonusType + "(s)";
            opponentBonusView.setText(opponentBonusCountText);
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

}
