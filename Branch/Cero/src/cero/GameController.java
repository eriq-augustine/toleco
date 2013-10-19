package cero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.SwingUtilities;

/**
 * The controller is a communication medium between the table and user
 * interfaces and contains the high level game logic.  It receives requested
 * actions from the players of the game and commits those actions to
 * the playing field, a.k.a the game table.  It keeps track of the current
 * state of the game and which player's turn it is, enforces the customized
 * settings from I_Options, and finally, determines the winner of the game.
 *
 * @author Raymond Wong - javadoc
 * @author Dirk Cummings - psuedocode
 * @version 1.0 - March 2, 2010
 */
public class GameController implements ActionListener, I_Controller
{
    /** The player who is currently taking a turn. */
    private Players whoseTurn;

    /** Represents the order of play. */
    private ArrayList<Players> playOrder;

    /** The player who discards the last card in its hand first */
    private Players winner;

    /** The Table where the game is played. */
    private I_Table table;

    /** A map of players and the user interface associated with each one. */
    private HashMap<Players, I_Player> players;

    /** The game's current state. */
    private State gameState;

    /** Indicates if the player has already drawn a card during their turn */
    private boolean hasDrawn;

    // Implements Change Request # 17
    /** Indicates if the game is being run in the console or with a GUI */
    private boolean consoleMode;

    /**
     * The game options with specific rules of the game.
     * The controller acts as the enforcer of the rules dictated in
     * options.
     */
    private I_Options options;

    /**
     * The amount of time to wait between each computer player's turn before
     * the player's turn is made.
     */
    private static final long kTurnLength = 999;

    /**
     * Construct a new GameController and associate it with a Table.
     * At the same time, playOrder and players will be instantiated,
     * and winner and whoseTurn will be set to null.
     * @post playOrder is not null.
     * @post players is not null.
     * @post winner = null.
     * @post whoseTurn = null.
     */
    public GameController()
    {
        // Initialize the players
        players = new HashMap<Players, I_Player>();
        // Set the winner to nobody
        winner = null;
        // Set has drawn to false
        hasDrawn = false;
        // Initialize options to a new default GameOptions
        options = new GameOptions();
        // Setup the player order (Human, Computer1, Computer2, Computer3)
        playOrder = new ArrayList<Players>(Arrays.asList(new Players[]{
            Players.kHuman, Players.kComputer1, Players.kComputer2,
            Players.kComputer3}));
        // Set the game mode to GUI by default
        consoleMode = false;
    }

    /** {@inheritDoc} */
    public void setTable(I_Table tbl)
    {
        // Save the reference to the Table
        table = tbl;
    }

    /**
     * Sets the current draw deck to presetDeck.
     * This feature is also used for testing purposes.
     * @param presetDeck A deck of known cards.
     * @see Deck
     */
    public void setDrawDeck(Deck presetDeck)
    {
        // Set the table's draw deck to the preset deck
        table.setDrawDeck(presetDeck);
    }

    /**
     * Sets the current discard deck to the presetDeck.
     * This feature is also used for testing purposes.
     * @param presetDeck A deck of known cards.
     * @see Deck
     */
    public void setDiscardDeck(Deck presetDeck)
    {
        // Set the table's discard deck to the preset deck
        table.setDiscardDeck(presetDeck);
    }

    /** {@inheritDoc} */
    public void addPlayer(Players who, I_Player newInterface)
    {
        // Save the reference to the player and interface to the player map
        players.put(who, newInterface);
    }

    /**
     * {@inheritDoc}
     * @post gameState is not null.
     * @post whoseTurn is not null.
     */
    public void startGame()
    {
        // Make the human player the player taking a turn
        whoseTurn = Players.kHuman;
        // Put the game into playing state
        gameState = State.playing;
        // CALL table to start the game
        table.startGame();
        // CALL the human's interface to setup the game
        players.get(Players.kHuman).setup();
        players.get(Players.kHuman).doTurn();
    }

    /** {@inheritDoc} */
    public void changeTurn()
    {
        // IF the game is not over THEN
        if(gameState != State.gameOver)
        {
            // Remove the first person in the play order
            // Add the removed person to the end of the play order
            playOrder.add(playOrder.remove(0));
            // Make the first person in the play order the player taking a turn
            whoseTurn = playOrder.get(0);
            // Indicate the new player has not yet drawn a card
            hasDrawn = false;

            // Implements Change Request # 17
            // IF the game is running in a console interface THEN
            if(consoleMode)
            {
                // Have the player's interface do a turn
                players.get(whoseTurn).doTurn();
            }
            // ELSE
            else
            {
                // Create a new Runnable to handle the next player's turn
                // Fixed defect 226
                Runnable run = new Runnable()
                {
                    // In the runnable's run method
                    public void run()
                    {
                        // IF the current player is not the human player
                        if(whoseTurn != Players.kHuman)
                        {
                            // Have the current thread sleep for turnLength nseconds
                            try
                            {
                                Thread.sleep(kTurnLength);
                            }
                            catch(Exception exp)
                            {
                                exp.printStackTrace();
                            }
                        }
                        // END IF
                        // Have the player's interface do a turn
                        players.get(whoseTurn).doTurn();
                    }
                };

                // CALL SwingUtilities and have the new runnable be invoked later
                SwingUtilities.invokeLater(run);
            }
            // END IF
        }
        // END IF
    }

    /** {@inheritDoc} */
    public void actionPerformed(ActionEvent evt)
    {
        // Create a temporary variable of the length of "color"

        // Get the action command string associated with this event
        String cmd = evt.getActionCommand();
        // Lookup player action for this command string
        PlayerAction action = PlayerAction.get(cmd);
        // Get the current player's name from the Options
        String name = options.getName(Players.kHuman);
        // SWITCH (action)
        switch(action)
        {
            // CASE color
            case color:
                // Extract the desired color from the tail of the command
                cmd = cmd.substring("color".length());
                // Have the table perform a color choosen action with this color
                table.performColorChosenAction(cmd);
                // Inform the Human Player interface who change the colour
                // and the new colour
                players.get(Players.kHuman)
                    .showStatusMessage(options.getName(whoseTurn) 
                    + " changed the colour to "+cmd);

                // IF the discarded card was a wild draw four THEN
                if(table.getDiscardCard().rank().equals(Rank.wilddrawfour))
                {
                    // CALL skip next player
                    skipNextPlayer();
                }
                // CALL change turns
                changeTurn();
            // END CASE
                break;
            // CASE draw
            case draw:
                // IF the draw deck is NOT empty THEN
                if(table.getDrawDeckSize() > 0)
                {
                    // IF multiple draws are NOT allowed THEN
                    if(!options.multipleDrawsAllowed())
                    {
                        // IF the current player hasn't already drawn THEN
                        if(!hasDrawn)
                        {
                            // Have table perform draw action for current player
                            table.performDrawAction(whoseTurn);
                            // Set hasDrawn to true
                            hasDrawn = true;
                        }
                        // END IF
                    }
                    // ELSE
                    else
                    {
                        // Have table perform draw action for current player
                        table.performDrawAction(whoseTurn);
                    }
                    // END IF
                }
                // END IF
            // END CASE
                break;
            // CASE play
            case play:
                // Parse the command string into an integer position in the hand
                int position = Integer.valueOf(cmd);
                // Get card at the position from player taking a turn's hand
                Card card = table.getHand(whoseTurn).get(position);
                // Have table perform discard action for this card
                table.performDiscardAction(whoseTurn, card);
                // Inform the Human Player interface who played what card
                players.get(Players.kHuman)
                    .showStatusMessage(options.getName(whoseTurn) + " " + "played a "
                    + card.toLongString());
                // IF the table has a winner THEN
                if(table.hasWinner())
                {
                    // CALL make game over
                    makeGameOver();
                }
                // ELSE
                else
                {
                    // SWITCH (card rank)
                    switch(card.rank())
                    {
                        // CASE wild
                        case wild:
                            // Show choose color dialog for current player
                            players.get(whoseTurn).showColorChooser();
                        // END CASE
                            break;
                        // CASE wilddrawfour
                        case wilddrawfour:
                            // Create a constant 4 number of cards to deal

                            // FOR the number of cards to draw
                            for(int numCards = Integer.valueOf("4");
                                numCards > 0; numCards--)
                            {
                                // Have table a draw card for the next player
                                table.performDrawAction(Players.next(whoseTurn));
                            }
                            // END LOOP
                            // Show choose color dialog to current player
                            players.get(whoseTurn).showColorChooser();
                        // END CASE
                            break;
                        // CASE drawtwo
                        case drawtwo:
                            // Create a constant 2 number of cards to deal

                            // FOR the number of cards to draw
                            for(int numCards = 2; numCards > 0; numCards--)
                            {
                                // Have table draw a card for the next player
                                table.performDrawAction(Players.next(whoseTurn));
                            }
                            // END LOOP
                            // CALL skip next player
                            skipNextPlayer();
                            // CALL change turn
                            changeTurn();
                        // END CASE
                            break;
                        // CASE reverse
                        case reverse:
                            // CALL reverse play order
                            reversePlayOrder();
                            // Have table perform a reverse action
                            table.performReverseAction();
                            // CALL change turn
                            changeTurn();
                        // END CASE
                            break;
                        // CASE skip
                        case skip:
                            // CALL skip next player
                            skipNextPlayer();
                            // CALL change turn
                            changeTurn();
                        // END CASE
                            break;
                        // DEFAULT
                        default:
                            // CALL change turn
                            changeTurn();
                    }
                    // END SWITCH
                }
                // END IF
            // END CASE
                break;
            // CASE pass
            case pass:
                // Inform the Human Player interface who passed
                players.get(Players.kHuman)
                        .showStatusMessage(options.getName(whoseTurn)
                        + " decided to pass on their turn");
                // CALL change turn
                changeTurn();
            // END CASE
                break;
            // DEFAULT
            default:
                // Do nothing
        }
        // END SWITCH
    }

    /** {@inheritDoc} */
    public State getState()
    {
        // Return the current game state
        return gameState;
    }

    /** {@inheritDoc} */
    public Players getCurrentPlayer()
    {
        //Fixes Defect #236
        // Return the current player taking a turn
        return whoseTurn;
    }

    /** Set the game state to gameOver and determine the winner of the game.*/
    private void makeGameOver()
    {
        // Set the game state to game over
        gameState = State.gameOver;
        // Set the winner to nobody
        winner = null;
        // Inform the Human Player interface the game is over
        players.get(Players.kHuman).showStatusMessage("This Game " +
                "has concluded!");

        // IF the human's hand is empty THEN
        if(table.getHand(Players.kHuman).size() == 0)
        {
            // Indicate the human is the winner
            winner = Players.kHuman;
        }
        // ELSE IF computer player 1's hand is empty THEN
        else if(table.getHand(Players.kComputer1).size() == 0)
        {
            // Indicate computer player 1 is the winner
            winner = Players.kComputer1;
        }
        // ELSE IF computer player 2's hand is empty THEN
        else if(table.getHand(Players.kComputer2).size() == 0)
        {
            // Indicate computer player 2 is the winner
            winner = Players.kComputer2;
        }
        // ELSE IF computer player 3's hand is empty THEN
        else if(table.getHand(Players.kComputer3).size() == 0)
        {
            // Indicate computer player 3 is the winner
            winner = Players.kComputer3;
        }
        // END IF

        // Have the computer interfaces show game over
        players.get(Players.kComputer1).showGameOver(winner);
        players.get(Players.kComputer2).showGameOver(winner);
        players.get(Players.kComputer3).showGameOver(winner);
        // Have the human interface show game over
        players.get(Players.kHuman).showGameOver(winner);

        // Fixes Defect 256
        // Inform the Human Interface who won the game
        players.get(Players.kHuman).showStatusMessage(options.getName(winner) +
                        " has won the game.");
    }

    /**
     * Reverses the current order of play, i.e. change from a clockwise
     * direction to counter-clockwise direction.
     */
    private void reversePlayOrder()
    {
        // Create a new list of players
        ArrayList<Players> newOrder = new ArrayList<Players>();

        // Add the current player to the begining of the new list
        newOrder.add(whoseTurn);

        // FOR each player in the play order list except the first player
        for(Players player: playOrder)
        {
            // Add the player to the second position of the new list
            if(player != whoseTurn)
            {
                newOrder.add(1, player);
            }
            // END IF
        }
        // END LOOP

        // Set the play order to the new list
        playOrder = newOrder;
    }

    /**
     * Skip the next player's turn.
     */
    private void skipNextPlayer()
    {
        // Remove the first player from the play order list
        playOrder.add(playOrder.remove(0));
        // Add the removed player to the end of the play order list

        // Make the first person in the play order list the person taking a turn
        whoseTurn = playOrder.get(0);
    }

    /**
     * Accessor to winner.
     * @return The player who won the game, or null if game not over.
     */
    public Players getWinner()
    {
        // Return the winner
        return winner;
    }

    /** {@inheritDoc} */
    public void setOptions(I_Options opt) 
    {
        // Save the reference to the options
        options = opt;
    }

    /** {@inheritDoc} */
    public I_Options getOptions()
    {
        // Return a reference to the options
        return options;
    }

    /** {@inheritDoc} */
    public ArrayList<Players> getPlayOrder()
    {
        // Return a reference to the play order
        return playOrder;
    }

    /**
     * Set the Game Controller to respond to a Swing GUI or a Console based
     * game play.
     * @param cmode Indicator of current type of interface. True if console,
     * false if GUI.
     */
    public void setConsoleMode(boolean cmode)
    {
        // Set this game's mode to cmode
        consoleMode = cmode;
    }

    /** {@inheritDoc} */
    public void resetGame()
    {
        // Reinitialize the play order:
        // kHuman, kComputer1, kComputer2, kComputer3
        playOrder = new ArrayList<Players>(Arrays.asList(new Players[]{
            Players.kHuman, Players.kComputer1, Players.kComputer2,
            Players.kComputer3}));
        // Resset table's decks
        table.resetDeck();
        // Set the winner to nobody
        winner = null;
        // Set has drawn to false
        hasDrawn = false;
        // Remove all computer players from players
        players.remove(Players.kComputer1);
        players.remove(Players.kComputer2);
        players.remove(Players.kComputer3);
        // Add new computer players to players based on the options
        // Make three I_Players: comp1, comp2, comp3

        // CALL setupNewComputerPlayer for kComputer1 for comp1
        setupNewComputerPlayer(Players.kComputer1);
        // CALL setupNewComputerPLayer for kComputer2 for comp2
        setupNewComputerPlayer(Players.kComputer2);
        // CALL setupNewComputerPlayer for kComputer3 for comp3
        setupNewComputerPlayer(Players.kComputer3);

        // Set all three I_Players' table to this table
        players.get(Players.kComputer1).setTable(table);
        players.get(Players.kComputer2).setTable(table);
        players.get(Players.kComputer3).setTable(table);
        // Set all three I_Players' controler to this
        players.get(Players.kComputer1).setControl(this);
        players.get(Players.kComputer2).setControl(this);
        players.get(Players.kComputer3).setControl(this);
        // Add all three computer players to players hash map
    }

    /**
     * Helper method for resetGame which creates a new appropriate computer
     * player for the game based by the computer level in the current game's
     * options.
     * @param player The player to process and create a new computer player for.
     * @param comp A reference to the player's interface to create the new
     * player for.
     */
    private void setupNewComputerPlayer(Players player)
    {
        // Added by change request 23
        I_Player comp = new MediumComputerPlayer();
        // Save a copy of the first computer player's difficulty from options
        ComputerLevel level = options.getComputerLevel(player);
        // SWITCH (level)
        switch(level)
        {
            // CASE easy
            case easy:
                // Make comp1 a new easy computer player
                comp = (I_Player) new EasyComputerPlayer();
                break;
            // END CASE
            // CASE medium
            case medium:
                // Make comp1 a new medium computer palyer
                comp = (I_Player) new MediumComputerPlayer();
                break;
            // END CASE
            // CASE hard
            case hard:
                // Make comp1 a new hard computer player
                comp = (I_Player) new HardComputerPlayer();
                break;
            // END CASE
            // DEFAULT
            default:
                // Do Nothing
                break;
        }
        players.put(player, comp);
        // END SWITCH
    }
}
