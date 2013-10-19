package cero;

/**
 * The ranks of a standard playing card.
 *
 * @author Dirk Cummings - Javadocs
 * @author Matt Carson - Psuedocode, Unit Code
 * @version 1.0 - February 27, 2010
 */

public enum Rank
{
    /** 
     * 0
     */
    zero('0'),

    /**
     * 1
     */
    one('1'),

    /**
     * 2
     */
    two('2'),

    /**
     * 3
     */

    three('3'),

    /** 
     * 4
     */

    four('4'),

    /** 
     * 5
     */

    five('5'),

    /** 
     * 6
     */

    six('6'),

    /** 
     * 7
     */

    seven('7'),

    /** 
     * 8
     */

    eight('8'),

    /**
     * 9 */

    nine('9'),

    /** 
     * Draw Two
     */

    drawtwo('T'),

    /**
     * Reverse
     */

    reverse('R'),

    /** 
     * Skip
     */

    skip('S'),

    /**
     * Wild */

    wild('W'),

    /**
     * Wild Draw Four
     */

    wilddrawfour('F');

    /** 
     * A rank has a printable symbol.
     */
    private final char symbol;

    /** 
     * Associates the specified symbol with the enum value.
     * @pre symbol = '0' - '9' | 'T' | 'R' | 'S' | 'W'
     * @param   symbol  the specified symbol
     */
    Rank(char symbol)
    {
        // Assign symbol to this symbol
        this.symbol = symbol;
    }

    /**
     * Accessor for a rank's symbol.
     *
     * @return char the symbol associated with this rank
     */
    public char symbol()
    {
        // RETURN symbol
        return symbol;
    }
}