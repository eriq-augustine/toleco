package cero;
import java.util.ArrayList;

/**
 * Table interface for Cero respresents a table which a game of Cero
 * can be played on. The Table contains the draw deck, discard pile,
 * each player's hands, and it keeps track of the current color chosen when
 * a wild or wild and draw four cards is played.
 *
 * @author Dirk Cummings
 * @author Ilya Seletsky
 */
public interface I_Table
{
    /**
     * Accessor to number of cards remaining in the draw deck.
     * @return int The draw deck size.
     */
    int getDrawDeckSize();

    /**
     * Accessor to the number of cards remaining in the discard deck.
     * @return int The number of cards reamining in the discard deck.
     */
    int getDiscardDeckSize();

    /**
     * Accessor to the hand for the given player.
     * @param player The player for whom we want access to their hand.
     * @return The hand for the specified player.
     */
    ArrayList<Card> getHand(Players player);

    /** Accessor to the currently discarded card.
     * @return Card The recently discarded card.
     */
    Card getDiscardCard();

    /**
     * Accessor to the most recently drawn card from the draw deck.
     * @return The most recently drawn card.
     */
    Card getDealtCard();

    /**
     * Accessor to the chosen color.
     * @return Color The currently chosen color.
     */
    Color getColor();

    /**
     * Accessor to get the current play drection
     * @return true: clockwise, false: counter clockwise
     */
    boolean getDirection();

    /** Reset the draw deck to a new set of cards. Empties the discard deck. */
    void resetDeck();

    /**
     * Resets the draw deck by reshuffling the discard deck and assigning it
     * as the new draw deck. The top card of the old discard deck remains on
     * top of the discard deck and is not added to the new draw deck.
     */
    void reshuffleDrawDeck();

    /**
     * Set the current draw deck to the specified deck, for testing.
     * @param presetDeck A deck of known cards.
     */
    void setDrawDeck(Deck presetDeck);

    /**
     * Set the current discard deck to the specified deck, for testing.
     * @param presetDeck A deck of known cards.
     */
    void setDiscardDeck(Deck presetDeck);

    /**
     * Begin a game by dealing each player a hand and discarding
     * the top card on the deck.
     * @pre The number of players in the game is 4.
     * @post A hand is dealt to each player.  For each of the 4 players, the
     * hand size is 7.
     */
    void startGame();

    /**
     * Determines whether the specified card is currently playable.
     * A playable card is one that matches the current discard's rank, color, or
     * has a rank of a wild or wilddrawfour.
     * @pre card is not null.
     * @param card A card to test for playability.
     * @return Returns true if the card can be played according
     * to the rules of Cero, otherwise false.
     */
    boolean isPlayableCard(Card card);

    /**
     * Discard the specified card for the given player.
     * @param player The player to operate on.
     * @param card The Card to discard.
     * @pre player is valid.
     * @pre card is currently in the hand of player.
     * @post card is removed from player's hand.
     * Card is made the current discard.
     * Chosen color is deselected.
     */
    void performDiscardAction(Players player, Card card);

    /**
     * Indicate the player has made a color choice after playing a
     * wild or wild and draw four.
     * @param colorChoice Which color was chosen by the player.
     * @pre colorChoice is one of blue, red, green, or yellow.
     */
    void performColorChosenAction(String colorChoice);

    /**
     * Reverse the direction of play
     */
    void performReverseAction();

    /**
     * Draws a card for the given player.
     * @param player The player to draw a card for.
     * @pre player is valid.
     * @post player has one card added to his or her hand from the top of
     * the draw deck. If the draw deck is empty, the discard deck is reshuffled and
     * becomes the new draw deck and the card is then drawn.
     */
    void performDrawAction(Players player);

    /**
     * Determines whether a player has won the game.
     * @return boolean Returns true if any player has discarded all their cards,
     * otherwise false.
     */
    boolean hasWinner();
}
