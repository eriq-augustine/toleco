package toleco.controller;

import toleco.logic.GameBoard;
import toleco.TolecoApp;
import toleco.view.I_GameView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;
import toleco.terrain.Terrain;
import toleco.unit.Unit;

/**
* A GameController is a module that handles all communications between the
* GameBoard and the logic while playing the game. The GameController will keep
* track of whose turn it is, a collection of all the players playing the
* game, the board the game is being played on, the controller for the
* GameView, and the state that the game is in.
*
* @author Evan Ralston (Javadocs)
* @author Adam Armstrong (Implementation)
* @version 1.0
*/
public class GameController implements I_Controller
{

    /**
    * The player who is eligible to move his/her units and attack with them.
    */
    private Player activePlayer;

    /**
    * A collection of all players on the board mapped to their names.
    */
    private HashMap<Player, String> playerNames;

    /**
    * The playing area that the units and terrain occupy.
    */
    private GameBoard board;

    /**
    * Controls what is displayed by the UI.
    */
    private I_GameView view;

    /**
    * Represents the state that the game is currently in.
    */
    private GameState state;

    /**
    * The reference to the TolecoApp that created this controller.
    */
    private TolecoApp app;

    /**
     * Used to construct a new GameController.
     * @param board supplied game board for the controller to pass on calls to
     */
    public GameController(GameBoard board)
    {
        //Initialoze this.board to the passed in board.

        //Set the current state to kNoneSelected.

        //Set the active player to kPlayer1.

        //Initialize playerNames to be a new HashMap<Player, String>.

        //Enter a name for kPlayer1 into playerNames.

        //Enter a name for kPlayer2 into playerNames.
    }

    /**
     * Sets the view the controller uses to manipulate the game display.
     * @param view I_GameView that the controller maintains a reference to
     */
    public void setView(I_GameView view)
    {
        this.view = view;
    }

    /**
    * Changes the current state to kAttack.
    */
    private void attackClicked()
    {
        //set state to kAttack
    }

    /**
    * Switches the active player to the other team.
    */
    private void endTurn()
    {
        //CALL board.selectTerrain with -1, -1.

        //IF active player is player 1
        {
            //CALL board.resetUnits with player 2

            //Set activePlayer to player 2
        }
        //ELSE
        {
            //CALL board.resetUnits with player 1

            //Set activePlayer to player 1

        }
        //ENDIF
    }

    /**
    * Ends the game causing the application to return to the main menu.
    */
    private void gameOver(String winner)
    {

        //SET state to GameState.gameOver

        //CALL view.displayGameOver with the appropriate player.

        //CALL quit()
    }

    /**
    * Tells the gameboard to move the unit on the selected terrain if it
    * belongs to the player currently taking his/her turn.
    *
    * @pre moveDirection must be a kMoveUp, kMoveDown, kMoveLeft or kMoveRight
    * @param moveDirection is the direction to move the unit
    */
    private void move(PlayerAction moveDirection)
    {
        //Fixes defext #226.
        //IF state equals GameState.kTerrainSelected and the selected location is
        //not null and the selected location has a unit
        {
            //IF selectedCell's Unit's player equals activePlayer THEN
            {
                //Get the number of moves the selected unit has remaining

                //CASE moveDirection OF
                {
                    //kMoveUp: CALL moveUp with moves

                    //kMoveDown: CALL moveDown with moves

                    //kMoveLeft: CALL moveLeft with moves

                    //kMoveRight: CALL movesRight with moves
                }
                //ENDCASE
            }
            //ENDIF
        }
        //ENDIF
    }


    /**
    * {@inheritDoc}
    */
    public void quit()
    {
        //CALL app.cleanUp
    }

    /**
    * {@inheritDoc}
    */
    public void save(String fileName)
    {
        //CALL board.saveMap with fileName and the current active player
    }

    /**
    * {@inheritDoc}
    */
    public void selectCell(int xCoord, int yCoord)
    {
        //SET state to kTerrainSelected

        //CALL board.selectCell(int, int) with xCoord, yCoord

        //CALL view.displayTerrainSelected

    }

    //CHECKSTYLE:OFF - Ignore the cyclomatic complexity of this method.
    // Ignore authorized by Dr. Dalbey.
    /**
    * {@inheritDoc}
    */
    public void update(Observable observe, Object obj)
    {
        //CHECKSTYLE:ON

        //IF object passed to update is an instance of String
        {
            //Cast the object to a String

            //CREATE a Scanner of the action command string

            //CREATE a PlayerAction from the first token read from the Scanner

            //CASE playerAction OF
            {
                //CASE kSelect : COMPUTE xCoord, yCoord from event's actionCommand

                    //IF state equals GameState.kAttackMode THEN
                    {
                        //CALL runAttackMode with the X and Y coordinates

                    }
                    //ELSE
                    {
                        //CALL selectCell with xCoord, yCoord

                    }
                    //END IF

                //CASE kMoveUp : CALL move with playerAction

                    //SET state to kTerrainSelected

                //CASE kMoveDown : CALL move with playerAction

                    //SET state to kTerrainSelected

                //CASE kMoveLeft : CALL move with playerAction

                    //Update the board's selected terrain

                //CASE kMoveRight : CALL move with playerAction

                    //SET state to kTerrainSelected

                //CASE kQuit : CALL quit()

                //CASE kSave : COMPUTE fileName from the command string

                    //Fixes defext #230
                    //CALL save with fileName making sure to trim
                    // the aquired String and replace all spaces with '_'s.

                //CASE kAttack : SET state to GameState.attackMode
                    
                    //CALL board.getEnemiesInRange

                //CASE kEndTurn : CALL endTurn

                //CASE kCancelAttack : SET state to kTerrainSelected

                    //CALL (view as SwingGameView) remove highlights

            }
            //ENDCASE
        }
        //END IF
    }

    /**
     * Sets the application from which the controller was created.
     * @param app the application that created this controller
     */
    public void setApp(TolecoApp app)
    {
        //SET this controller's app to given app
        this.app = app;
    }

    /**
     * Sets the game board which the controller interacts with.
     * @param board the board to set the controller's board with
     */
    public void setBoard(GameBoard board)
    {
        //SET this controller's board to the given board
        this.board = board;
    }

    /**
     * Get the game view from this controller.
     * @return this controller's game view
     */
    public I_GameView getView()
    {
        return view;
    }

    /**
     * Get the game state from this controller.
     * @return this controller's game state
     */
    public GameState getState()
    {
        return state;
    }

    /**
     * Set the state of the game within this controller.
     * @param state the state of the game
     */
    public void setState(GameState state)
    {
        this.state = state;
    }

    /**
     * Sets the active player in the game.
     * @param player active player in the game for use by GameController
     */
    public void setActivePlayer(Player player)
    {
        activePlayer = player;
    }

    /**
     * Checks game over conditon on the map.
     * @return true if one player has no units otherwise false
     */
    private boolean isGameOver()
    {
        //CALL board.getMap and store the map

        //Create boolean value for player 1

        //Create boolean value for player 2


        //FOR each Terrain array in the map
        {
            //FOR each Terrain in the Terrain array
            {
                //IF terrain.getUnit is not null
                {
                    //IF player one owns the unit
                    {
                        //SET player 1's boolean value to true

                    }
                    //ELSE
                    {
                        //SET player 2's boolean value to true

                    }
                    //ENDIF
                }
                //ENDIF
            }
            //END FOR
        }
        //END FOR

        //Return if player 1 or player 2 are out of units
        return true;
    }

    /**
     * Load a map from the specified file name and sets the active player.
     * @param filename path to the map file
     */
    public void loadMap(String filename) throws java.io.FileNotFoundException
    {
        //Set activePlayer with a call to board.loadMap with the fileName

        //CALL view.displayBackStory with the back story returned from board

    }

    //Fixes defect #161
    /**
     * Form a battle summary string based off of the passed in information
     * following this format:
     * "<attacking player's name> attacked <defending player's name> for
     * <damage> damage.\n
     * [<defending player's name> counter-attacked <attacking player's name>
     * for <damage> damage.]"
     *
     * @pre both Units and the ArrayList are non-null, and the ArrayList has
     * atleast one element.
     *
     * @param damageDealt an ArrayList containing the damage dealt to the defener
     *          and the damage dealt to the attacker (if any).
     * @return a String representing the battle summary.
     */
    private String formBattleSummary(ArrayList<Integer> damageDealt,
        Unit attacker, Unit defender)
    {
        //Create an empty String.

        //Add the attacking player's name (not the enum, look it up in the name table).

        //Add " attacked " to the String.

        //Add the defending player's name to the String.

        //Add " for <damageDealt.get(0)> damage.\n" to the String.

        //IF there was a counter-attack.
        {
            //Add the defending player's name to the String.

            //Add " counter-attacked " to the String.

            //Add the attacking player's name to the String.

            //Add " for <damageDealt.get(1)> damage.\n" to the String.

        }
        //ENDIF

        //Return the build String.
        return "";
    }

    /**
     * Analyzes and carries out the attack.
     * @param xCoord x-coordinate on the map
     * @param yCoord y-coordinate on the map
     */
    private void runAttackMode(int xCoord, int yCoord)
    {
        //IF the selected unit can attack, the unit getting attacked
        //is within range, the grid space getting attacked has a unit,
        //and the unit getting attacked is owned by the other player
        {
            //Get the attacking unit

            //Get the defending unit

            //CALL board.attack with xCoord, yCoord and
            //keep the returned ArrayList.

            //CALL formBattleSummary with the result from attack.

            //CALL view.displayBattleSummary with the battle summary

            //CALL view.removeHighLights

            //IF game is over
            {
                //Create a String to hold the name of the winning Player.

                //IF damageDealt has move than one entry
                // A counter-attack occured, so the active player could
                // not have won.
                {
                    //The winner is the currently active player.

                }
                //ELSE
                {
                    //The winner is the player who is not currently active.

                }
                //ENDIF

                //CALL gameOver with the winning player's name.
            }
            //END IF

            //SET state to kTerrainSelected
            
        }
        //ENDIF
    }

    /**
     * Move the unit up one location on the map.
     * @param moves current moves the unit has remaining
     */
    private void moveUp(int moves)
    {
        //IF move is not out of the top bounds, the terrain movement cost
        //is not too much, and the selected location contains a unit
        if(board.getSelection().getX() != 0 &&
                board.getTerrain(board.getSelection().getX() - 1,
                board.getSelection().getY()).getMoveCost() <= moves
                && board.getTerrain(board.getSelection().getX() - 1,
                    board.getSelection().getY()).getUnit() == null)
        {
            //CALL board.move with -1 and 0 as arguments
            board.move(-1, 0);
            //Update the board's selected terrain
            selectCell(board.getSelection().getX() - 1,
                       board.getSelection().getY());
        }
        //END IF
    }

    /**
     * Move the unit down one location on the map.
     * @param moves current moves the unit has remaining
     */
    private void moveDown(int moves)
    {
        //IF move is not out of the bottom bounds, the terrain movement cost
        //is not too much, and the selected location contains a unit
        if(board.getMap()[0].length != board.getSelection().getX() + 1 &&
            board.getTerrain(board.getSelection().getX() + 1,
                board.getSelection().getY()).getMoveCost() <= moves &&
                board.getTerrain(board.getSelection().getX() + 1,
                board.getSelection().getY()).getUnit() == null)
        {
            //CALL board.move with 1 and 0 as arguments
            board.move(1, 0);
            //Update the board's selected terrain
            selectCell(board.getSelection().getX() + 1,
                       board.getSelection().getY());
        }
        //END IF
    }

    /**
     * Move the unit left one location on the map.
     * @param moves current moves the unit has remaining
     */
    private void moveLeft(int moves)
    {
        //IF move is not out of left bounds, the terrain movement cost
        //is not too much, and the selected location contains a unit
        if(board.getSelection().getY() != 0 &&
            board.getTerrain(board.getSelection().getX(),
                board.getSelection().getY() - 1).getMoveCost() <= moves
                && board.getTerrain(board.getSelection().getX(),
                    board.getSelection().getY() - 1).getUnit() == null)
        {
            //CALL board.move with 0 and -1 as arguments
            board.move(0, -1);
            //Update the board's selected terrain
            selectCell(board.getSelection().getX(),
                        board.getSelection().getY() - 1);
        }
        //END IF
    }

    /**
     * Move the unit right one location on the map.
     * @param moves current moves the unit has remaining
     */
    private void moveRight(int moves)
    {
        //IF move is not out of right bounds, the terrain movement cost
        //is not too much, and the selected location contains a unit
        if(board.getMap().length != board.getSelection().getY() + 1 &&
            board.getTerrain(board.getSelection().getX(),
                board.getSelection().getY() + 1).getMoveCost() <= moves
                && board.getTerrain(board.getSelection().getX(),
                    board.getSelection().getY() + 1).getUnit() == null)
        {
            //CALL board.move with 0 and 1 as arguments
            board.move(0, 1);
            //Update the board's selected terrain
            selectCell(board.getSelection().getX(),
                        board.getSelection().getY() + 1);
        }
        //END IF
    }
}
