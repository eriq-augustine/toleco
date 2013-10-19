package cero;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for Table
 * @author Dirk Cummings
 * @version 1.0 - April 17, 2010
 */
public class TableTest {

    public TableTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("-- TESTING Table --");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("\n-- Table TEST DONE --");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getColor method, of class Table.
     */
    @Test
    public void testGetColor() throws FileNotFoundException {
        System.out.println("\n-- TESTING getColor --");
        Table instance = new Table();
        instance.performColorChosenAction("blue");
        Color expResult = Color.blue;
        Color result = instance.getColor();
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of getDealtCard method, of class Table.
     */
    @Test
    public void testGetDealtCard() throws Exception {
        System.out.println("\n-- TESTING getDealtCard --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        instance.startGame();
        
        instance.performDrawAction(Players.kHuman);
        Card expResult = new Card(Rank.eight, Color.blue);
        Card result = instance.getDealtCard();
        assertEquals(expResult, result);

        instance.performDrawAction(Players.kHuman);
        instance.performDrawAction(Players.kHuman);
        expResult = new Card(Rank.six, Color.red);
        result = instance.getDealtCard();
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of getDirection method, of class Table.
     */
    @Test
    public void testGetDirection() {
        System.out.println("\n-- TESTING getDirection --");
        Table instance = new Table();
        boolean expResult = false;
        boolean result = instance.getDirection();
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of getDiscardCard method, of class Table.
     */
    @Test
    public void testGetDiscardCard() throws Exception {
        System.out.println("\n-- TESTING getDiscardCard --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        instance.startGame();

        Card expResult = new Card(Rank.four, Color.green);
        Card result = instance.getDiscardCard();
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of getDiscardDeckSize method, of class Table.
     */
    @Test
    public void testGetDiscardDeckSize() throws Exception {
        System.out.println("\n-- TESTING getDiscardDeckSize --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck1.txt");
        instance.setDiscardDeck(deck);

        int expResult = 108;
        int result = instance.getDiscardDeckSize();
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of getDrawDeckSize method, of class Table.
     */
    @Test
    public void testGetDrawDeckSize() throws Exception {
        System.out.println("\n-- TESTING getDrawDeckSize --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck1.txt");
        instance.setDrawDeck(deck);
        int expResult = 108;
        int result = instance.getDrawDeckSize();
        assertEquals(expResult, result);

        instance.startGame();
        expResult = 79;
        result = instance.getDrawDeckSize();
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of getHand method, of class Table.
     */
    @Test
    public void testGetHand() throws Exception {
        System.out.println("\n-- TESTING getHand --");
        Players player = Players.kHuman;
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck2.txt");
        instance.setDrawDeck(deck);
        instance.startGame();

        deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck2.txt");
        
        ArrayList<Card> expResult = (ArrayList<Card>) deck.dealHand(7);
        ArrayList result = instance.getHand(player);
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of hasWinner method, of class Table.
     */
    @Test
    public void testHasWinner() throws Exception {
        System.out.println("\n-- TESTING hasWinner --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        instance.startGame();
        
        boolean expResult = false;
        boolean result = instance.hasWinner();
        assertEquals(expResult, result);
        
        System.out.println("-- PASSED --");
    }

    /**
     * Test of isPlayableCard method, of class Table.
     */
    @Test
    public void testIsPlayableCard() throws Exception {
        System.out.println("\n-- TESTING isPlayableCard --");
        Card card = new Card(Rank.eight, Color.blue);
        Card wild = new Card(Rank.wild, Color.wild);
        Card wd4 = new Card(Rank.wilddrawfour, Color.wild);
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        instance.startGame();

        boolean expResult = false;
        boolean result = instance.isPlayableCard(card);
        assertEquals(expResult, result);

        expResult = true;
        result = instance.isPlayableCard(wild);
        assertEquals(expResult, result);

        expResult = true;
        result = instance.isPlayableCard(wd4);
        assertEquals(expResult, result);

        card = new Card(Rank.four, Color.green);
        expResult = true;
        result = instance.isPlayableCard(card);
        assertEquals(expResult, result);
        
        card = new Card(Rank.four, Color.blue);
        expResult = true;
        result = instance.isPlayableCard(card);
        assertEquals(expResult, result);
        
        card = new Card(Rank.two, Color.green);
        expResult = true;
        result = instance.isPlayableCard(card);
        assertEquals(expResult, result);
        
        card = new Card(Rank.skip, Color.red);
        expResult = false;
        result = instance.isPlayableCard(card);
        assertEquals(expResult, result);

        System.out.println("-- PASSED --");
    }

    /**
     * Test of performColorChosenAction method, of class Table.
     */
    @Test
    public void testPerformColorChosenAction() {
        System.out.println("\n-- TESTING performColorChosenAction --");
        Table instance = new Table();
        
        Color expResult = Color.blue;
        instance.performColorChosenAction("blue");
        Color result = instance.getColor();

        expResult = Color.red;
        instance.performColorChosenAction("red");
        result = instance.getColor();

        System.out.println("-- PASSED --");
    }

    /**
     * Test of performDiscardAction method, of class Table.
     */
    @Test
    public void testPerformDiscardAction() throws Exception {
        System.out.println("\n-- TESTING performDiscardAction --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        instance.startGame();

        // Discard a card actually in the player's hand
        instance.performDiscardAction(Players.kHuman,
                new Card(Rank.zero, Color.blue));

        assertEquals(new Card(Rank.zero, Color.blue),
                instance.getDiscardCard());

        // Discard a card NOT in the player's hand
        instance.performDiscardAction(Players.kHuman,
                new Card(Rank.wilddrawfour, Color.wild));

        assertEquals(new Card(Rank.wilddrawfour, Color.wild),
                instance.getDiscardCard());

        assertEquals(3, instance.getDiscardDeckSize());

        System.out.println("-- PASSED --");
    }

    /**
     * Test of performReverseAction method, of class Table.
     */
    @Test
    public void testPerformReverseAction() {
        System.out.println("\n-- TESTING performReverseAction --");
        Table instance = new Table();
        instance.startGame();
        assertTrue(instance.getDirection());

        instance.performReverseAction();
        assertFalse(instance.getDirection());

        instance.performReverseAction();
        instance.performReverseAction();
        instance.performReverseAction();

        assertTrue(instance.getDirection());

        System.out.println("-- PASSED --");
    }

    /**
     * Test of performDrawAction method, of class Table.
     */
    @Test
    public void testPerformDrawAction() throws Exception {
        System.out.println("\n-- TESTING performDrawAction --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        instance.startGame();
        
        instance.performDrawAction(Players.kHuman);
        assertEquals(58, instance.getDrawDeckSize());
        assertEquals(new Card(Rank.eight, Color.blue),
                instance.getDealtCard());

        // Draw until empty deck
        while(instance.getDrawDeckSize() != 0)
            instance.performDrawAction(Players.kHuman);

        // Drawing from empty hand shouldn't do anything
        instance.performDrawAction(Players.kHuman);
        assertEquals(0, instance.getDrawDeckSize());
        
        System.out.println("-- PASSED --");
    }

    /**
     * Test of resetDeck method, of class Table.
     */
    @Test
    public void testResetDeck() throws FileNotFoundException {
        System.out.println("\n-- TESTING resetDeck --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck1.txt");
        ArrayList<Card> cards = (ArrayList<Card>)deck.dealHand(108);
        instance.setDrawDeck(deck);
        deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck2.txt");
        instance.setDiscardDeck(deck);
        instance.resetDeck();
        assertEquals(108, instance.getDrawDeckSize());
        assertEquals(0, instance.getDiscardDeckSize());

        System.out.println("-- PASSED --");
    }

    /**
     * Test of reshuffleDrawDeck method, of class Table.
     */
    @Test
    public void testReshuffleDrawDeck() throws Exception {
        System.out.println("\n-- TESTING reshuffleDrawDeck --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        instance.setDiscardDeck(Deck.makeDeckFromFile("test/deckfiles/presetDeck4.txt"));
        instance.reshuffleDrawDeck();

        System.out.println("-- PASSED --");
    }

    /**
     * Test of setDiscardDeck method, of class Table.
     */
    @Test
    public void testSetDiscardDeck() throws FileNotFoundException  {
        System.out.println("\n-- TESTING setDiscardDeck --");
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck1.txt");
        Table instance = new Table();
        instance.setDiscardDeck(deck);

        assertEquals(108, instance.getDiscardDeckSize());

        System.out.println("-- PASSED --");
    }

    /**
     * Test of setDrawDeck method, of class Table.
     */
    @Test
    public void testSetDrawDeck() throws FileNotFoundException {
        System.out.println("\n-- TESTING setDrawDeck --");
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        Table instance = new Table();
        instance.setDrawDeck(deck);

        assertEquals(88, instance.getDrawDeckSize());

        System.out.println("-- PASSED --");
    }

    /**
     * Test of startGame method, of class Table.
     */
    @Test
    public void testStartGame() throws Exception {
        System.out.println("\n-- TESTING startGame --");
        Table instance = new Table();
        Deck deck = Deck.makeDeckFromFile("test/deckfiles/presetDeck3.txt");
        instance.setDrawDeck(deck);
        
        instance.startGame();

        assertEquals(1, instance.getDiscardDeckSize());
        assertEquals(59, instance.getDrawDeckSize());
        assertTrue(instance.getDirection());
        assertEquals(7, instance.getHand(Players.kHuman).size());

        System.out.println("-- PASSED --");
    }
}