package cero;

/**
 * The I_Options interface represents a catagory of option menus and
 * defines a set of methods to set up the menu items.
 * 
 * @author Raymond Wong
 * @version 1.0 - March 1, 2010
 */
public interface I_Options
{
    /**
     * Set the name for a player in the game.
     * @param player Player to bind name with.
     * @param name The player's name.
     */
    void setName(Players player, String name);

    /**
     * Set the difficulty level for a computer opponent.
     * @param computer The computer to set difficulty level with.
     * @param level Desired difficulty level.
     */
    void setComputerLevel(Players computer, ComputerLevel level);

    /**
     * Sets whether or not a player may draw multiple times per turn
     * or once per turn.
     * @param allow Flag indicates if multiple card draws are allowed.
     */
    void setMultipleDraws(boolean allow);

    /**
     * Retrieves a player's name.
     * @param player The requested player.
     * @return The requested player's name.
     */
    public String getName(Players player);

    /**
     * Retrieves the difficulty level of a computer.
     * @param player The requested computer.
     * @return The requested computer's difficulty.
     */
    public ComputerLevel getComputerLevel(Players player);

    /**
     * Returns whether or not multiple draws are allowed.
     * @return Is a player allowed to draw more than once on a turn?
     */
    public boolean multipleDrawsAllowed();
}
