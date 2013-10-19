package cero;

import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration test for Deck.
 *
 * @author Annabel Hung
 * @version 1.0 - April 29, 2010
 */

public class DeckIntTest {

    public DeckIntTest() {
        super();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * Test of dealCard method, of class Deck.
     */
    @Test
    public void testDealCard() {
        // Create an empty hand
        ArrayList<Card> hand = new ArrayList<Card>();
        assertEquals(0, hand.size());
        // Create a preset deck with two cards
        List<Card> littleDeck = new ArrayList<Card>();
        littleDeck.add(new Card(Rank.one, Color.red));
        littleDeck.add(new Card(Rank.two, Color.blue));
        // Create an actual deck with data from preset
        Deck myDeck = new Deck(littleDeck);
        int deckSize = myDeck.size();
        assertEquals(2, deckSize);
        // Deal
        hand.add(myDeck.dealCard());
        // Check correct card was dealt
        assertEquals(hand.get(0), new Card(Rank.one, Color.red));
        // Check size of deck has decreased by one
        assertEquals(1, myDeck.size());

    }

    /**
     * Test of dealHand method, of class Deck.
     */
    @Test
    public void testDealHand() {
        int handSize = 10;

        Deck instance = new Deck();
        ArrayList<Card> result = (ArrayList<Card>) instance.dealHand(handSize);
        result = (ArrayList<Card>) instance.dealHand(handSize);
        assertEquals(handSize, result.size());

        instance = new Deck();
        handSize = 108;
        result = (ArrayList<Card>) instance.dealHand(handSize);
        assertEquals(handSize, result.size());

        instance = new Deck();
        handSize = 1;
        result = (ArrayList<Card>) instance.dealHand(handSize);
        assertEquals(handSize, result.size());

    }

    /**
     * Test of size method, of class Deck.
     */
    @Test
    public void testSize() {
        int deckSize = 20;
        Deck instance = new Deck();
        // Check default size
        assertEquals(108, instance.size());

        // Relies on dealHand working properly
        instance.dealHand(deckSize);
        assertEquals(88, instance.size());
    }

    /**
     * Test of addCard method, of class Deck.
     */
    @Test
    public void testAddCard() {
        // Create a deck with preset data
        List<Card> preset = new ArrayList<Card>();
        Card initialTop = new Card(Rank.one, Color.red);
        preset.add(initialTop);
        preset.add(new Card(Rank.two, Color.blue));
        Deck myDeck = new Deck(preset);
        // Check the size
        assertEquals(2, myDeck.size());
        // Add a card
        Card newTop = new Card(Rank.three, Color.yellow);
        myDeck.addCard(newTop);
        // Check the size, should be +1
        assertEquals(3, myDeck.size());
        // Check the card on top, should be different from intial
        Card top = myDeck.dealCard();
        assertFalse(initialTop.equals(top));
        assertTrue(newTop.equals(top));
    }

    /**
     * Testing of shuffle() is not necessary because it calls the Collections
     * class's shuffle(). The Collections class is assumed to be correct.
     */
}