package cero;

import java.util.ArrayList;

/**
 * FakeTable represents the table on which a Cero game is played.
 * It has one discard deck and one draw deck of cards
 * and one hand to represent all player hands. It always returns blue as
 * the choosen color. All cards are playable. Discard and Dealt card are
 * always a Blue Eight. Never a winner. All deck sizes 1. Direction always
 * true.
 *
 * @author Raymond Wong - skeleton
 * @author Dirk Cummings - unit code
 * @version 1.0 - February 28, 2010
 * @version 2.0 - April 17, 2010
 */
public class FakeTable  extends java.util.Observable implements I_Table
{
    /** The game deck for the table. */
    private Deck disDeck, drawDeck;

    private Card discard;

    /** A hand of cards shared by all players. */
    private ArrayList<Card> hand;

    /**
     * Construct a table by resetting the deck and
     * creating an empty hand.
     * @post hand is not null.
     */
    public FakeTable()
    {
        hand = new ArrayList<Card>();
    }

    /** {@inheritDoc} */
    public Color getColor() {
        return discard.color();
    }

    /** {@inheritDoc} */
    public Card getDealtCard() {
        return new Card(Rank.eight, Color.blue);
    }

    /** {@inheritDoc} */
    public boolean getDirection() {
        return true;
    }

    /** {@inheritDoc} */
    public Card getDiscardCard() {
        return discard;
    }

    /** {@inheritDoc} */
    public int getDiscardDeckSize() {
        return 1;
    }

    /** {@inheritDoc} */
    public int getDrawDeckSize() {
        return 1;
    }

    /** {@inheritDoc} */
    public ArrayList<Card> getHand(Players player) {
        return hand;
    }

    public void setHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }

    /** {@inheritDoc} */
    public boolean hasWinner() {
        return hand.size() == 0;
    }

    /** {@inheritDoc} */
    public boolean isPlayableCard(Card card) {
        return (card.equals(discard) || card.rank() == Rank.wild);
    }

    /** {@inheritDoc} */
    public void performColorChosenAction(String colorChoice) {
        setChanged();
        notifyObservers(PlayerAction.color);
    }

    /** {@inheritDoc} */
    public void performDiscardAction(Players player, Card card) {
        discard = hand.remove(0);
        setChanged();
        notifyObservers(PlayerAction.play);
    }

    /** {@inheritDoc} */
    public void performReverseAction() {
        
    }

    /** {@inheritDoc} */
    public void performDrawAction(Players player) {
        hand.add(new Card(Rank.eight, Color.blue));
        setChanged();
        notifyObservers(PlayerAction.draw);
    }

    /** {@inheritDoc} */
    public void resetDeck() {
        
    }

    /** {@inheritDoc} */
    public void reshuffleDrawDeck() {
        
    }

    /** {@inheritDoc} */
    public void setDiscardDeck(Deck presetDeck) {
        disDeck = presetDeck;
    }

    /** {@inheritDoc} */
    public void setDrawDeck(Deck presetDeck) {
        drawDeck = presetDeck;
    }

    /** {@inheritDoc} */
    public void startGame() {
        
    }
}
