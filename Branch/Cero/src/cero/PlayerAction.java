package cero;

/**
 * PlayerAction represents the different actions or moves a player can
 * make in a Cero game.
 * 
 * @author Robert Bernal, Raymond Wong - Javadocs
 * @author Matt Carson - Psuedocode, Unit Code
 * @version 1.0 - March 3, 2010
 */
public enum PlayerAction
{
    /** Take a card from the deck and place it into the player's hand. */
    draw,
    /** Choose a color when playing a wild card. */
    color,
    /** Play a card (aka "discard"), taking a playable card from the player's
     *  hand and place it on top of the discard pile.
     */
    play,
    /** Only going to be used when the player has no other options */
    pass;

    /** 
     * Return an action given its name (string representation).
     *
     * @param name The string representation of an action (as would be returned
     * by toString()).  Only the first four characters of this string will be
     * used to identify the action.  Any extra characters are ignored
     * (unlike the valueOf() method).
     *
     * @pre name is a valid name for the player action based on the existing
     * enums.
     *
     * @return The action corresponding to the given name, or <code>play</code>
     * if there is no action with the given name.
     */
    public static PlayerAction get(String name)
    {
        // Used for checking the input parameter name
        final int kActionLength = 4;
        
        // IF the name is less than 4 characters long THEN
        if(name.length() < kActionLength)
        {
            // RETURN play
            return play;
        }
        // ENDIF

        // IF the name is 4 characters long THEN
        if(name.length() == kActionLength)
        {
            // RETURN draw or pass depending on value
            return PlayerAction.valueOf(name);
        }

        // ELSE
        else
        {
            // RETURN color
            return color;
        }
    }
}
