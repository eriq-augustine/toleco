package cero;

import java.util.Observable;
import java.util.ArrayList;

/**
 * The ComputerPlayer abstract class models an autonomous human player
 * of the card game Cero.
 *
 * @author Eric Gustafson
 * @author Ilya Seletsky
 * @author Dirk Cummings
 * @author Annabel Hung - Pseudocode, Implementation
 * @version 1.0 - March 3, 2010
 */
public abstract class AbstractComputerPlayer implements I_Player
{

    /** Reference to the controller for this player. */
    private I_Controller controller;

    /** Reference to the table for this player. */
    private I_Table table;

    /** {@inheritDoc} */
    public void resetDeck()
    {
        // Do nothing. Computer players will not modify the GUI.
    }

    /** {@inheritDoc} */
    public void setControl(I_Controller gc)
    {
        // Save the reference to gc.
        controller = gc;
    }

    /** {@inheritDoc} */
    public void setTable(I_Table tbl)
    {
        // Save the reference to tbl.
        table = tbl;
    }

    /** {@inheritDoc} */
    public void setup()
    {
        // Do nothing. Computer players will not modify the GUI.
    }

    /** {@inheritDoc} */
    public abstract void showColorChooser();

    /** {@inheritDoc} */
    public void showGameOver(Players winner)
    {
        // Do nothing. Computer players will not modify the GUI.
    }

    /** {@inheritDoc} */
    public void update(Observable obs, Object obj)
    {
        // Do nothing. Computer players will not modify the GUI.
    }

    /**
     * An abstract method to perform the computer strategy for
     * selecting a card to play. Strategy varies for each difficulty
     * type of ComputerPlayer.
     * @see EasyComputerPlayer
     * @see MediumComputerPlayer
     * @see HardComputerPlayer
     * @return The chosen Card to play, or null if unable to play a Card.
     */
    public abstract Card getComputerMove();

    /**
     * Shows each player's most recent action.
     * @param mes Each player's action.
     */
    public void showStatusMessage(String mes)
    {
        // Do nothing. Computer players will not modify the GUI.
    }

    /**
     * Accessor method for the game controller.
     * @return The game controller for this player.
     */
    public I_Controller getControl()
    {
        return controller;
    }

    /**
     * Accessor method for the table.
     * @return The table for this player.
     */
    public I_Table getTable()
    {
        return table;
    }

    /**
     * Analyze a hand to find a playable card. Returns the first playable card
     * in the player's hand.
     * @return A playable Card or null if no playable card is found.
     */
    public Card findPlayableCard()
    {
        // Get this player's hand
        ArrayList<Card> hand = table.getHand(controller.getCurrentPlayer());
        // FOR each card in this player's hand
        for(Card card : hand)
        {
            // IF this card is a playable card
            if(table.isPlayableCard(card))
            {
                // RETURN this card
                return card;
            }
            // END IF
        }
        // END FOR

        // RETURN null
        return null;
    }
}
