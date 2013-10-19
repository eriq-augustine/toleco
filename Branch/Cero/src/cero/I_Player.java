package cero;

import java.util.Observable;

/**
 * I_Player represents a partipant of the Cero card game.  An I_Player
 * will make decisions based on the information provided by an I_Table
 * and I_Controller.  Once an I_Player decides to make a move, it will
 * inform the I_Controller of its decision.
 *
 * @author Dirk Cummings
 * @author Ilya Seletsky
 * @version version 1.0 - Feb 28, 2010
 */

public interface I_Player extends java.util.Observer
{

    /**
     * Save a reference to the GameController in this game.
     * @param gc The GameController running this game.
     */
    void setControl(I_Controller gc);

    /**
     * Save a reference to the Table in this game.
     * @param tbl The Table where this game is played.
     */
    void setTable(I_Table tbl);

    /** Handle a request to do the interface setup.
     *  @pre The table and game controller are not null.
     */
    void setup();

    /** Make request to reset the deck in the game.
     *  @pre The table and game controller are not null.
     */
    void resetDeck();

    /** Interact with the user to obtain their move in the game.
     *  @pre The table and game controller are not null.
     */
    void doTurn();

    /** 
     * Have the interface show that the current game has ended.
     * @pre winner is not null.
     * @param winner The player who won the game.
     */
    void showGameOver(Players winner);

    /** Interact with the user to obtain their choice of a color.
     *  @pre Color chooser is hidden.
     *  @post Color chooser is visible.
     */
    void showColorChooser();

    /**
     * Handle updates from the model.
     * @param obs The observable.
     * @param obj The object passed from the observable.
     */
    void update(java.util.Observable obs, Object obj);

    /**
     * Shows each player's most recent action.
     * @param mes Each player's action.
     */
    void showStatusMessage(String mes);
}
