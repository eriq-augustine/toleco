package cero;

import java.util.Map;
import java.util.HashMap;

/**
 * A Card represents the main component in a game of Cero. Each card has a color
 * and a rank. Cards can be compared by their colors and ranks. Class Card also
 * contains a collection of Cards with all possible combinations of ranks and
 * colors.
 *
 * @author Dirk Cummings - Javadocs
 * @author Annabel Hung - Pseudocode, Implementation
 * @version 1.0 - March 1, 2010
 */
public class Card implements Comparable<Card>
{

    /**
     * This card's rank.
     */
    private Rank rank;

    /**
     * This card's color.
     */
    private Color color;
    
    /**
     * Constant used for building the map.
     */
    private static final int kFOUR = 4;

    /**
     * A Lookup map to associate a card with its string representation.
     */
    private static HashMap<String, Card> kLookupMap =
            new HashMap<String, Card>();

    /**
     * Build the Lookup map with the cards and string representations.
     */
    static
    {
        // FOR each card color
        for (Color currentColor : Color.values())
        {
            // FOR each card rank
            for (Rank currentRank : Rank.values())
            {
                // Create a new card with the current color and rank
                Card temp = new Card(currentRank, currentColor);
                // IF the current color is not Wild THEN
                if (currentColor != Color.wild)
                {
                    // IF the current rank is 0 THEN
                    if (currentRank == Rank.zero)
                    {
                        // Add the new card to the map once
                        kLookupMap.put(temp.toString(), temp);
                    } // ELSE
                    else
                    {
                        // Add the new card to the map twice
                        for (int index = 0; index < 2; index++)
                        {
                            kLookupMap.put(temp.toString(), temp);
                        }
                    }
                    // END IF
                } // ELSE
                else
                {
                    // IF the current rank is Wild or Wild Draw Four THEN
                    if (currentRank == Rank.wild ||
                            currentRank == Rank.wilddrawfour)
                    {
                        // Add the current card to the map four times
                        for (int index = 0; index < kFOUR; index++)
                        {
                            kLookupMap.put(temp.toString(), temp);
                        }
                        // END IF
                    }
                    // END IF
                }
                // END FOR
            }
            // END FOR
        }
    }

    /**
     * Constructs a card with the specified rank and color.
     *
     * @pre rank is not-null
     * @pre color is non-null
     * @post this card's rank is non-null
     * @post this card's color is non-null
     * @param  rank    the desired rank of the card.
     * @param  color   the desired color of the card.
     */
    public Card(Rank rank, Color color)
    {
        // SET this card's rank to rank
        this.rank = rank;
        // SET this card's color to color
        this.color = color;
    }

    /**
     * Returns a String representation of this card.
     *
     * @pre rank is non-null
     * @pre color is non-null
     * @return a two-character String that represents this card. The first
     * character is the first letter of the color name in upper case. The
     * second character is the character representing this card's rank. For
     * example, R2 is returned for a card with color Red and rank 2.
     */
    public String toString()
    {
        // Get the first character of this card's rank
        char rankLetter = rank.symbol();
        // Get the first character of this card's color
        char colorLetter = ((color.toString()).toUpperCase()).charAt(0);
        // RETURN the two characters (color first) in upper case
        return new String("" + colorLetter + rankLetter);
    }

    /**
     * Returns a string representation of this card, where the full name of
     * this card's color and rank are spelled out.
     *
     * @pre rank is non-null
     * @pre color is non-null
     * @return a two-word String that represents this card. The first word is
     * the color of this card, and the second word is the rank of this card.
     */
    public String toLongString()
    {
        // CALL toString RETURNING shortString
        // Get the name of the color corresponding to the first
        // character of shortString
        // Get the name of the rank corresponding to the second
        // character of shortString
        // RETURN the names (color first)

        String thisColor = color.toString();
        String thisRank = rank.toString();
        return new String("" + thisColor + " " + thisRank);
    }

    /**
     * Returns this card's rank.
     *
     * @pre rank is non-null
     * @return this card's rank.
     */
    // public Rank getRank()
    public  Rank rank()
    {
        // RETURN this card's rank
        return rank;
    }

    /**
     * Returns this card's color.
     *
     * @pre rank is non-null
     * @return this card's color.
     */
    // public Color getColor()
    public Color color()
    {
        // RETURN this card's color
        return color;
    }

    /**
     * Compares two cards.
     *
     * @param   aThat   the card to be compared to this one
     * @return  -1,0,1  if this card's color and rank is less, equal, or greater
     *                  than aThat.
     * @pre     aThat   is not null.
     */
    public int compareTo(Card aThat)
    {
        // SET kEqual to 0
        int kEqual = 0;
        // IF this card's color is equal to aThat's color THEN
        if (color.equals(aThat.color()))
        {
            // IF this card's rank is less than aThat's rank THEN
            if (rank.compareTo(aThat.rank()) < 0)
            {
                // SET kEqual to -1
                kEqual = -1;
            } // ELSE IF this card's rank is greater than aThat's rank THEN
            else if (rank.compareTo(aThat.rank()) > 0)
            {
                // SET kEqual to 1;
                kEqual = 1;
            }
            // END IF
        } // ELSE
        else
        {
            // IF this card's color is less than aThat's color THEN
            if (color.compareTo(aThat.color()) < 0)
            {
                // SET kEqual to -1
                kEqual = -1;
            } // ELSE if this card's color is greater than aThat's color THEN
            else if (color.compareTo(aThat.color()) > 0)
            {
                // SET kEqual to 1
                kEqual = 1;
            }
            // END IF
        }
        // END IF
        // RETURN kEqual
        return kEqual;
    }

    /**
     * Determines if two cards are equal. Cards are equal if their colors and
     * ranks are identical.
     *
     * @param   aThat   the card to be compared to this one
     * @return  true    if the cards are equal, false otherwise
     * @pre     aThat   is not null.
     */
    public boolean equals(Object aThat)
    {
        // SET kBool to true
        boolean kBool = true;
        // IF aThat is not a Card object THEN
        if (!(aThat.getClass().equals(this.getClass())))
        {
            // SET kBool to false
            kBool = false;
        } // ELSE IF this card's rank is not equal to aThat's rank THEN
        else if (!rank.equals(((Card) aThat).rank()))
        {
            // SET kBool to false
            kBool = false;
        } // ELSE IF this card's color is not equal to aThat's color THEN
        else if (!color.equals(((Card) aThat).color()))
        {
            // SET kBool to false
            kBool = false;
        }
        // END IF
        // RETURN kBool
        return kBool;
    }

    /**
     * Returns a hash code value for the object. This method is supported for
     * the benefit of hashtables such as those provided by java.util.Hashtable.
     *
     * @return a hash code value for this card
     */
    public int hashCode()
    {
        // RETURN this card's hash code
        return ((Object) this).hashCode();
    }

    /**
     * Returns a card given its name as a string.
     *
     * @param   name    the string representation of a card
     * @pre     name    is a valid card name.
     * @return  the Card corresponding to the given name, or null if there is
     *          no Card with the given name.
     */
    public static Card valueOf(String name)
    {
        // Get the card corresponding to name from the map
        Card aCard = kLookupMap.get(name);
        // RETURN the card
        return aCard;
    }
}
