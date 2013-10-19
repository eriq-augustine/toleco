package cero;

/**
 * The ComputerLevel enum represents the different difficulty levels of the
 * computer players. There are three levels available: easy, medium, and hard.
 *
 * @author Raymond Wong - Javadocs
 * @author Annabel Hung - Pseudocode, Implementation
 * @version 1.0 -  February 28, 2010
 */
public enum ComputerLevel
{

    /** easy */
    easy,

    /** medium */
    medium,

    /** hard */
    hard;

    /**
     * Returns a computer level based on its index.
     * @param index the position in the enum (zero-based)
     * @pre index is a non-negative number
     * @return ComputerLevel value at the given index
     */
    public static ComputerLevel getLevel(int index)
    {
        // CALL ComputerLevel.values()[index] RETURNING aLevel
        ComputerLevel aLevel = ComputerLevel.values()[index];
        // RETURN aLevel
        return aLevel;
    }
}
