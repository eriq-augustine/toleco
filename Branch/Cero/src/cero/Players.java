package cero;

/**
 * Players enumerates the participants in the Cero game.
 * @author Dirk Cummings - Javadocs
 * @author Matt Carson - Psuedocode, Unit Code
 * @version 1.0 - March 3, 2010
 */

public enum Players
{
    /** A computer player 1 */
    kComputer1,
    /** A computer player 2 */
    kComputer2,
    /** A computer player 3 */
    kComputer3,
    /** The human player */
    kHuman;
    /** an array of all the players */
    private static final Players[] kNames = {kComputer1, kComputer2,
        kComputer3, kHuman};

    /** Determine the next player in succession (as defined by
     * the enumeration order.)
     * @param aPlayer the given player
     * @return Players the player whose turn follows the given player.
     */
    public static Players next(Players aPlayer)
    {
        // RETURN the next name from the names array modulo array length
        int current = aPlayer.ordinal();
        int next = (current + 1) % kNames.length;
        return kNames[next];
    }
}
