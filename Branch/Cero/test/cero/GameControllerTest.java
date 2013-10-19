package cero;

import cero.I_Controller.State;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for GameController
 * @author Dirk Cummings
 * @author 1.0 - April 17, 2010
 */
public class GameControllerTest {

    private GameController gc;
    private I_Table table;
    private ScriptedPlayer hu, cp1, cp2, cp3;
    private I_Options opt;

    public GameControllerTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("-- TESTING GameController --");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("\n-- GameController TEST DONE --");
    }

    @Before
    public void setUp() {
        gc = new GameController();
        table = new FakeTable();
        opt = new FakeOptions();
        gc.setTable(table);
        gc.setOptions(opt);
        // Adding console mode support as per Change Request #17
        gc.setConsoleMode(true);
    }

    private void reinit()
    {
        hu.setControl(gc);
        cp1.setControl(gc);
        cp2.setControl(gc);
        cp3.setControl(gc);
        hu.setTable(table);
        cp1.setTable(table);
        cp2.setTable(table);
        cp3.setTable(table);
        gc.addPlayer(Players.kHuman, hu);
        gc.addPlayer(Players.kComputer1, cp1);
        gc.addPlayer(Players.kComputer2, cp2);
        gc.addPlayer(Players.kComputer3, cp3);
    }

    @After
    public void tearDown() {
        
    }

    /**
     * Test Game 1. Skips Played. Winner Human.
     */
    @Test
    public void testGame1() throws Exception
    {
        System.out.println("\n-- TESTING Game 1 (Deck 4) --");
        // Cards G4, Y6, B1, R5, G2, YS, GS
        hu = new ScriptedPlayer("0000");
        cp1 = new ScriptedPlayer("00");
        cp2 = new ScriptedPlayer("0D");
        cp3 = new ScriptedPlayer("0D0");

        reinit();

        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Rank.four, Color.green));
        hand.add(new Card(Rank.six, Color.yellow));
        hand.add(new Card(Rank.one, Color.blue));
        hand.add(new Card(Rank.five, Color.red));
        hand.add(new Card(Rank.two, Color.green));
        hand.add(new Card(Rank.skip, Color.yellow));
        hand.add(new Card(Rank.skip, Color.green));
        ((FakeTable)table).setHand(hand);

        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kHuman).size());
        assertEquals(Players.kHuman, gc.getWinner());
        
        System.out.println("-- PASSED --");
    }

    /**
     * Test Game 2. Wild & WDF Played. Winner Human.
     */
    @Test
    public void testGame2() throws Exception
    {
        System.out.println("\n-- TESTING Game 2 (Deck 5) --");
        // Cards Y1, WW, B7, RS, WF, R7, R2
        hu = new ScriptedPlayer("000");
        cp1 = new ScriptedPlayer("0b0r0");
        cp2 = new ScriptedPlayer("00");
        cp3 = new ScriptedPlayer("000");

        reinit();

        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Rank.one, Color.yellow));
        hand.add(new Card(Rank.wild, Color.wild));
        hand.add(new Card(Rank.seven, Color.blue));
        hand.add(new Card(Rank.skip, Color.red));
        hand.add(new Card(Rank.wilddrawfour, Color.wild));
        hand.add(new Card(Rank.seven, Color.red));
        hand.add(new Card(Rank.two, Color.red));
        ((FakeTable)table).setHand(hand);

        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kHuman).size());
        assertEquals(Players.kHuman, gc.getWinner());

        System.out.println("-- PASSED --");
    }

    /**
     * Test Game 3. Reverse. Winner CP1.
     */
    @Test
    public void testGame3() throws Exception
    {
        System.out.println("\n-- TESTING Game 3 (Deck 6) --");
        // Cards R7, GR, Y7, G1, B6, Y8, B8
        hu = new ScriptedPlayer("0000");
        cp1 = new ScriptedPlayer("000");
        cp2 = new ScriptedPlayer("DD0");
        cp3 = new ScriptedPlayer("D00");

        reinit();
        
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Rank.seven, Color.red));
        hand.add(new Card(Rank.reverse, Color.green));
        hand.add(new Card(Rank.seven, Color.yellow));
        hand.add(new Card(Rank.one, Color.green));
        hand.add(new Card(Rank.six, Color.blue));
        hand.add(new Card(Rank.eight, Color.yellow));
        hand.add(new Card(Rank.eight, Color.blue));
        ((FakeTable)table).setHand(hand);

        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kHuman).size());
        assertEquals(Players.kHuman, gc.getWinner());

        System.out.println("-- PASSED --");
    }

    /**
     * Test Game 4. Draw Two. Winner CP3.
     */
    @Test
    public void testGame4() throws Exception
    {
        System.out.println("\n-- TESTING Game 4 (Deck 7) --");
        // Cards B5, Y5, B7, BT, B8, R3, G7
        hu = new ScriptedPlayer("00");
        cp1 = new ScriptedPlayer("000");
        cp2 = new ScriptedPlayer("00");
        cp3 = new ScriptedPlayer("00");

        reinit();

        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Rank.five, Color.blue));
        hand.add(new Card(Rank.five, Color.yellow));
        hand.add(new Card(Rank.seven, Color.blue));
        hand.add(new Card(Rank.drawtwo, Color.blue));
        hand.add(new Card(Rank.eight, Color.blue));
        hand.add(new Card(Rank.three, Color.red));
        hand.add(new Card(Rank.seven, Color.green));
        ((FakeTable)table).setHand(hand);

        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kHuman).size());
        assertEquals(Players.kHuman, gc.getWinner());

        System.out.println("-- PASSED --");
    }

    /**
     * Test Setting and Geting Options
     */
    @Test
    public void testSetGetOptions()
    {
        System.out.println("\n-- TESTING getOptions & setOptions --");
        opt = new FakeOptions();

        gc.setOptions(opt);
        assertEquals(opt, gc.getOptions());
        System.out.println("-- PASSED --");
    }

    /**
     * Test setDrawDeck
     */
    @Test
    public void testSetDrawDeck()
    {
        System.out.println("\n-- TESTING setDrawDeck --");
        gc.setDrawDeck(null);
        assertEquals(1, table.getDrawDeckSize());

        System.out.println("-- PASSED --");
    }

    /**
     * Test setDiscardDeck
     */
    @Test
    public void testSetDiscardDeck()
    {
        System.out.println("\n-- TESTING setDiscardDeck --");
        gc.setDiscardDeck(null);
        assertEquals(1, table.getDiscardDeckSize());

        System.out.println("-- PASSED --");
    }

    /**
     * Test getPlayOrder
     */
    @Test
    public void testGetPlayOrder()
    {
        System.out.println("\n-- TESTING getPlayOrder --");

        // Cards B5, Y5, B7, BT, B8, R3, G7
        hu = new ScriptedPlayer("00");
        cp1 = new ScriptedPlayer("000");
        cp2 = new ScriptedPlayer("00");
        cp3 = new ScriptedPlayer("00");

        reinit();

        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(Rank.five, Color.blue));
        hand.add(new Card(Rank.five, Color.yellow));
        hand.add(new Card(Rank.seven, Color.blue));
        hand.add(new Card(Rank.drawtwo, Color.blue));
        hand.add(new Card(Rank.eight, Color.blue));
        hand.add(new Card(Rank.three, Color.red));
        hand.add(new Card(Rank.seven, Color.green));
        ((FakeTable)table).setHand(hand);

        assertEquals(4, gc.getPlayOrder().size());

        gc.startGame();

        assertEquals(Players.kComputer1, gc.getPlayOrder().get(0));
        assertEquals(Players.kComputer2, gc.getPlayOrder().get(1));
        assertEquals(Players.kComputer3, gc.getPlayOrder().get(2));
        assertEquals(Players.kHuman, gc.getPlayOrder().get(3));

        System.out.println("-- PASSED --");
    }
}