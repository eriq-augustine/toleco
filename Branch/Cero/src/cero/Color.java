package cero;

/**
 * An enumerated type for the colors that a Card can have. There are four
 * real-world colors: red, blue, green, and yellow. The fifth is not an
 * actual color, but it is used to represent Wild cards.
 *
 * @author Dirk Cummings - Javadocs
 * @author Annabel Hung - Pseudocode, Implementation
 * @version 1.0 - February 27, 2010
 */
public enum Color
{

    /**
     * The color red.
     */
    red,

    /**
     * The color blue.
     */
    blue,

    /**
     * The color green.
     */
    green,

    /**
     * The color yellow.
     */
    yellow,

    /**
     * The color representing wild.
     */
    wild;

    /**
     * Returns the color of the card at the specified position.
     *
     * @param index the position in the enum (zero-based)
     * @pre index is a non-negative number
     * @return  the enum constant with the specified position.
     */
    public static Color pos(int index)
    {
        // CALL Color.values()[index] RETURNING aColor
        Color aColor = Color.values()[index];
        // RETURN aColor
        return aColor;
    }
}
