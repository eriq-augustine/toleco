package cero;
import java.util.HashMap;

/**
 * GameOptions is a set of extra features where the user may customize the
 * game to his or her desires, such as his/her name and the difficulty
 * level of each computer player.
 *
 * @author Raymond Wong - Javadocs
 * @author Robert Bernal - Pseudocode and source code
 * @version 1.0 - February 28, 2010
 */
public class GameOptions implements I_Options
{

    /** A map of players and the name associated with each player. */
    private HashMap<Players, String> playerNames;

    /** A map of computer players and the difficulty level with each one. */
    private HashMap<Players, ComputerLevel> computerLevels;

    /** Indicates whether multiple card draws are allowed or not. 
    Default is true */
    private boolean drawMultiple = true;

    /**
     * Construct GameOptions by making new maps and allowing multiple
     * draws.
     * @post drawMultiple is true.
     */
    public GameOptions()
    {
        //SET playerNames to a new HashMap
        //SET computerLevels to a new HashMap
        //SET drawMultiple to true
        playerNames = new HashMap<Players, String>();
        computerLevels = new HashMap<Players, ComputerLevel>();
        drawMultiple = true;

        playerNames.put(Players.kHuman, "Player");
        playerNames.put(Players.kComputer1, "Annabel Raymond");
        playerNames.put(Players.kComputer2, "Eric Robert Ilya");
        playerNames.put(Players.kComputer3, "Matt Dirk");


        computerLevels.put(Players.kComputer1, ComputerLevel.medium);
        computerLevels.put(Players.kComputer2, ComputerLevel.medium);
        computerLevels.put(Players.kComputer3, ComputerLevel.medium);
    }

    /** {@inheritDoc} */
    public void setName(Players player, String name)
    {
        //SET the String associated with player to name
        playerNames.remove(player);
        playerNames.put(player, name);
    }

    /** {@inheritDoc} */
    public void setComputerLevel(Players computer, ComputerLevel level)
    {
        //SET the ComputerLevel associated with computer to level
        computerLevels.remove(computer);
        computerLevels.put(computer, level);
    }

    /** {@inheritDoc} */
    public void setMultipleDraws(boolean allow)
    {
        //SET drawMultiple to allow
        drawMultiple = allow;
    }

    /** {@inheritDoc} */
    public String getName(Players player)
    {
        //RETURN String(name) associated with player
        return playerNames.get(player);
    }

    /** {@inheritDoc} */
    public ComputerLevel getComputerLevel(Players player)
    {
        //RETURN ComputerLevel(level) associated with player
        return computerLevels.get(player);
    }

    /** {@inheritDoc} */
    public boolean multipleDrawsAllowed()
    {
        //RETURN drawMultiple
        return drawMultiple;
    }
}
