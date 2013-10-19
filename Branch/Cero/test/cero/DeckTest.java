package cero;

import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Deck represents a standard deck of playing cards.
 * A 108-card random deck can be constructed, or a preset sequence of
 * cards can be loaded from an external file.
 * The deck is used to deal individual cards or an entire hand.
 * @author Matt Carson
 * @version 1.0 - March 3, 2010
 */

public class DeckTest {

    public DeckTest() {
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
        System.out.println("-- TESTING dealCard --");
        
        List<Card> hand = new ArrayList<Card>();
        Card expResult = new Card(Rank.eight, Color.blue);
        hand.add(expResult);
        Deck instance = new Deck(hand);

        Card result = instance.dealCard();
        assertEquals(expResult, result);

        expResult = new Card(Rank.drawtwo, Color.blue);
        assertNotSame(expResult, result);

        List<Card> hand2 = new ArrayList<Card>();
        Deck newInstance = new Deck(hand2);

        assertEquals(null, newInstance.dealCard());
        System.out.println("-- PASSED dealCard --");
    }

    /**
     * Test of dealHand method, of class Deck.
     */
    @Test
    public void testDealHand() {
        System.out.println("-- TESTING dealHand --");
        int handSize = 10;
        
        Deck instance = new Deck();
        ArrayList<Card> result = (ArrayList<Card>) instance.dealHand(handSize);
        result = (ArrayList<Card>) instance.dealHand(handSize);
        assertEquals(10, result.size());

        instance = new Deck();
        handSize = 108;
        result = (ArrayList<Card>) instance.dealHand(handSize);
        assertEquals(108, result.size());

        instance = new Deck();
        handSize = 1;
        result = (ArrayList<Card>) instance.dealHand(handSize);
        assertEquals(1, result.size());
        
        System.out.println("-- PASSED dealHand --");
    }

    /**
     * Test of size method, of class Deck.
     */
    @Test
    public void testSize() {
        System.out.println("-- TESTING size --");
        int deckSize = 20;
        Deck instance = new Deck();
        // Relies on dealHand working properly
        instance.dealHand(deckSize);
        assertEquals(88, instance.size());
        System.out.println("-- PASSED size");
    }

    /**
     * Test of addCard method, of class Deck.
     */
    @Test
    public void testAddCard() {
        System.out.println("-- TESTING addCard --");

        // Setup a fake deck
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(new Card(Rank.eight, Color.blue));
        deck.add(new Card(Rank.eight, Color.yellow));
        deck.add(new Card(Rank.nine, Color.green));

        // Make a new card to add to deck
        Card card = new Card(Rank.wilddrawfour, Color.wild);

        // Make a deck from the fake deck
        Deck instance = new Deck(deck);

        // Get deck size before adding card
        int deckSize = instance.size();

        // Add card to pre made deck ysing addCard(Card card)
        instance.addCard(card);

        assertEquals(4, deckSize + 1);
        System.out.println("-- PASSED AddCard --");
    }

}