package cero;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * I_Controller represents the behavior of any game controller.
 * A controller acts as a communication medium between the users or players
 * of the game and the table.  It keeps track of the current state
 * of the game, which player is taking its turn, and commits the players
 * requested actions to the table.
 * 
 * @author Dirk Cummings, Ilya Seletsky, Raymond Wong
 * @version 1.0 - March 3, 2010
 */
public interface I_Controller
{

    /**
     * An enumerated type for the states of the game.<br/><br/>
     * playing - when a game has started and every player still has cards
     * in their hand<br/><br/>
     * gameOver - when one player has discarded the last card
     * in its hand<br/>
     */
    enum State
    {playing, gameOver};

    /**
     * Save a reference to the Table in this game.
     * @pre The table must not be null.
     * @param tbl The Table where this game is played.
     */
    void setTable(I_Table tbl);

    /** 
     * Add a player and their user interface to the controller.
     * If who is not a recognized enumerated type, then addPlayer throws
     * an EnumConstantNotPresentException.
     * If newInterface is null, addPlayer throws an Exception.
     * @pre who must not be null.
     * @pre newInterface must not be null.
     * @param who The new player.
     * @param newInterface The user interface for the new player.
     */
    void addPlayer(Players who, I_Player newInterface);

    /** Begin playing the game.  The human always gets the first turn. */
    void startGame();

    /**
     * Advance the turn to the next player based on the direction
     * of the current game.
     */
    void changeTurn();

    /**
     * Handle an action performed request from a user interface.
     * If an evt is not recognized, the controller will do nothing.
     * @pre evt cannot be null.
     * @param evt The event which triggered this action.
     */
    void actionPerformed(ActionEvent evt);

    /**
     * Retrieves the player who is currently taking their turn.
     * @return The current player taking their turn.
     */
    public Players getCurrentPlayer();

    /**
     * Retrieves the current state of the game.
     * @return The current state of the game.
     */
    public State getState();

    /**
     * Save a reference to the options chosen by the human player.
     * @param opt The I_Options to be enforced
     */
    public void setOptions(I_Options opt);

    /**
     * Accessor to the Controller's Options.
     * @return A reference to the options chosen by the human player.
     */
    public I_Options getOptions();

    /**
     * Accessor to the Controller's current order of play.
     * @return A list of the current order in which the players are to take
     * turns.
     */
    public ArrayList<Players> getPlayOrder();

    /**
     * Reset the current game for a new game. Play order is reset, the table's
     * decks are reset, and the winner is set to no one.
     */
    public void resetGame();
}
