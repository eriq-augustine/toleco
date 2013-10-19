package cero;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Deck represents a standard deck of playing cards.
 * A 108-card random deck can be constructed, or a preset sequence of
 * cards can be loaded from an external file.
 * The deck is used to deal individual cards or an entire hand.
 * @author Raymond Wong, Dirk Cummings - Javadocs
 * @author Matt Carson - Psuedocode, Unit Code
 * @author Ilya Seletsky - Skeletonizing it all over again for Maintenance!!!!!!
 * @version 1.0 - March 3, 2010
 */
public class Deck
{
    /** theDeck is a list of Cards */
    private ArrayList<Card> theDeck;
    /**  Declare a prototype deck of cards in order. */
    private static final List<Card> kProtoDeck = new ArrayList<Card>();

    //WHAT! this is not commented
    private static final int kNumTimes = 4;

    // Build the prototype deck of all 108 cards
        // Add to Prototype Deck:
        //    19 Blue Cards (0-9)
        //    19 Red Cards (0-9)
        //    19 Green Cards (0-9)
        //    19 Yellow Cards (0-9)

    //WHAT!
    static
    {
        // FOR each color
        for (Color clr : Color.values())
        {
            // FOR each rank
            for (Rank rank : Rank.values())
            {
                // IF the Color is not 'wild' THEN
                if (clr != Color.wild)
                {
                    // IF The Rank is Zero ('0'), THEN
                    if (rank == Rank.zero)
                    {
                        // ADD new card
                        kProtoDeck.add(new Card(rank, clr));
                    }
                    //WAHT! (incorrect)
                    // ELSE IF The Rank is 0 - 9, THEN
                    else if (rank.ordinal() > Rank.zero.ordinal()
                        && rank.ordinal() <= Rank.nine.ordinal())
                    {
                        // ADD two of that card
                        kProtoDeck.add(new Card(rank, clr));
                        kProtoDeck.add(new Card(rank, clr));
                    }
                    //END IF
                }
                // END IF
            }
            // END FOR LOOP
        }
        // END FOR LOOP

        // Add to Prototype Deck:
        //   8 Draw Two Cards (2 Each Color)
        //   8 Reverse Cards (2 Each Color)
        //   8 Skip Cards (2 Each Color)
        //   Don't add 'wild' colors
        
        //WHAT!
        // FOR 2 of each color
        for (int i = 0; i < 2; i++)
        {
            // FOR each color
            for (Color clr : Color.values())
            {
                // IF color is not wild THEN
                if (clr != Color.wild)
                {
                    // Add a drawTwo, reverse, and skip
                    kProtoDeck.add(new Card(Rank.drawtwo, clr));
                    kProtoDeck.add(new Card(Rank.reverse, clr));
                    kProtoDeck.add(new Card(Rank.skip, clr));
                }

                // END IF
            }
            // END FOR LOOP
        }
        // END FOR LOOP

        // Add to Prototype Deck:
        //    4 Wild Cards
        //    4 Wild Draw Four Cards
        // FOR 0 to 4
        for (int i = 0; i < 4; i++)
        {
            // Add wild and wilddrawfour
            kProtoDeck.add(new Card(Rank.wild, Color.wild));
            kProtoDeck.add(new Card(Rank.wilddrawfour, Color.wild));
        }
            
        // END FOR LOOP

    }

    /** Construct a shuffled Deck of Cards.
     * @post theDeck is not null.
     * @see Collections
     */
    public Deck()
    {
        //WHAT! (copy?, deep copy?) HOW?
        // initialize theDeck from a prototype deck
        theDeck = new ArrayList<Card>();
        for (Card card : kProtoDeck)
        {
            theDeck.add(new Card(card.rank(), card.color()));
        }

        // CALL Collections.shuffle for theDeck
        Collections.shuffle(theDeck);
    }

    /**
     * Construct a new Deck with the given cards in the order in which they exist
     * in cards.
     * @param cards A List of Cards to be added to the new deck.
     */
    public Deck(List<Card> cards)
    {
        // initialize theDeck deck from cards
        theDeck = new ArrayList<Card>(cards);

        //WHAT! this is not an acceptable pseudocode. Grammar is incorrect!
        //BTW look at the javadoc for ArrayList to see that you can just
        //pass cards theDeck's constructor to automatically add the collection
    }

    /**
     * Make a deck from an external text file.  This is used for testing.
     * Each line of the file represents one card in the deck.
     * The value on each line is the String representation of a card.
     * Empty lines are ignored.
     * Lines that begin with a '#' character are ignored.
     * @see cero.Card
     * @param filename The name of the text file containing the deck.
     * @return A Deck created from the text file.
     * @throws java.io.FileNotFoundException if deck file is not found.
     */
    public static Deck makeDeckFromFile(String filename)
        throws FileNotFoundException
    {
        // Declare a string to hold a line of input
        String temp = new String();

        // Create a new deck
        Deck deck = new Deck();

        // Clear the new deck's theDeck
        deck.theDeck.clear();

        // Create a scanner to read the file
        Scanner sc = new Scanner(new File(filename));

        // WHILE there are more lines in the file LOOP
        while (sc.hasNextLine())
        {
            // Read a line
            temp = sc.nextLine().trim();

            // IF the line is not ignorable THEN (A line is ignorable if it
                //starts with '#' or is totally blank)
            if (temp.length() > 0 && temp.charAt(0) != '#')
            {
                // Create a card from the line
                Card tempCard = Card.valueOf(temp);
                    //The line will be a string that can be passed to
                    //Card.valueOf to retreive the right card.

                // IF the card is not null THEN
                if (tempCard != null)
                {
                    // add it to the deck
                    //Fixes defect #235
                    deck.theDeck.add(tempCard);
                }
             // END IF
            }
            // END IF
        }
        // END LOOP

        // RETURN the new deck
        return deck;
    }

    /** Deal a card from the top of the deck.
     * @return Top Card of the deck, or null if the deck is empty.
     */
    public Card dealCard()
    {
        // IF deck size is greater than zero THEN
        if (theDeck.size() > 0)
        {
            // remove the top card from the deck and return it
            //WHAT? What is top? Test doesnt care!
            return theDeck.remove(0);
        }
        // ELSE
        else
        {
            // RETURN null
            return null;
        }
        // END IF
    }

    /** Deal a hand of size desiredHandSize from the top of the deck.
     * @param desiredHandSize Number of cards in desired hand.
     * @pre deck.size() > desiredHandSize
     * @return List<Card> containing desired number of cards
     */
    public List<Card> dealHand(int desiredHandSize)
    {
        //WHAT! what is a hand? do you mean list<Card>?
        // Create an empty hand with the desiredHandSize (an ArrayList)
        ArrayList<Card> hand = new ArrayList<Card>();

        // Iterate for desired number of cards in hand
        for (int i = 0; i < desiredHandSize; i++)
        {
            // CALL dealCard to get one card
            // Add the card to the hand
            hand.add(dealCard());
        }
        // RETURN the hand

        return hand;
    }

    /** Accessor to size of the deck.
     * @return Number of cards in the deck.
     * @post 0 <= size() <= 108
     */
    public int size()
    {
        // RETURN the deck size
        return theDeck.size();
    }

    /**
     * Shuffle the deck.
     * @see Collections
     */
    public void shuffle()
    {
        // Call Collections.shuffle on theDeck
        Collections.shuffle(theDeck);
    }

    /**
     * Adds a Card to the top of the deck.
     * @param card Card to be added to the top of the deck.
     * @pre card is a valid non-null Card.
     */
    public void addCard(Card card)
    {
        //WHAT! what is "top"? 
        // Add a card to the top of this deck
        //Fixed Defect #235
        theDeck.add(0, card);
    }
}
