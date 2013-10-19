package cero;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Integration Test for AbstractComputerPlayer
 *
 * @author Dirk Cummings
 * @version 1.0 - April 29, 2010
 */
public class AbstractComputerPlayerIntTest {

    public AbstractComputerPlayerIntTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("-- TESTING AbstractComputerPlayer --");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("-- AbstractComputerPlayer TEST DONE --");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setControl method, of class AbstractComputerPlayer.
     */
    @Test
    public void testSetGetControl() {
        System.out.println("-- TESTING setControl and getControl --");
        I_Controller gc = new GameController();
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.setControl(gc);
        assertEquals(gc, instance.getControl());
        System.out.println("-- PASSED --");
    }

    /**
     * Test of setTable & getTable method, of class AbstractComputerPlayer.
     */
    @Test
    public void testSetGetTable() {
        System.out.println("-- TESTING setTable getTable --");
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        I_Table expResult = new Table();
        instance.setTable(expResult);
        I_Table result = instance.getTable();
        assertEquals(expResult, result);
        System.out.println("-- PASSED --");
    }

    /**
     * Test of setup method, of class AbstractComputerPlayer.
     */
    @Test
    public void testSetup() {
        System.out.println("-- TESTING setup --");
        AbstractComputerPlayer instance = new EasyComputerPlayer();
        instance.setup();
        System.out.println("-- PASSED --");
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of showColorChooser method, of class AbstractComputerPlayer.
     */
    @Test
    public void testShowColorChooser() {
        System.out.println("-- TESTING showColorChooser --");
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.showColorChooser();
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of showGameOver method, of class AbstractComputerPlayer.
     */
    @Test
    public void testShowGameOver() {
        System.out.println("-- TESTING showGameOver --");
        Players winner = null;
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.showGameOver(winner);
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of update method, of class AbstractComputerPlayer.
     */
    @Test
    public void testUpdate() {
        System.out.println("-- TESTING update --");
        Observable obs = null;
        Object obj = null;
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.update(obs, obj);
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of getComputerMove method, of class AbstractComputerPlayer.
     */
    @Test
    public void testGetComputerMove() {
        System.out.println("-- TESTING getComputerMove --");
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        Card expResult = new Card(Rank.zero, Color.red);
        Card result = instance.getComputerMove();
        assertEquals(expResult, result);
        System.out.println("-- PASSED --");
    }

    /**
     * Test of showStatusMessage method, of class AbstractComputerPlayer.
     */
    @Test
    public void testShowStatusMessage() {
        System.out.println("-- TESTING showStatusMessage --");
        String mes = "";
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.showStatusMessage(mes);
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of findPlayableCard method, of class AbstractComputerPlayer.
     */
    @Test
    public void testFindPlayableCard() throws Exception {
        System.out.println("-- TESTING findPlayableCard --");
        I_Controller con = new GameController();
        I_Table tab = new Table();
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.setControl(con);
        instance.setTable(tab);
        con.setTable(tab);
        con.addPlayer(Players.kHuman, instance);
        con.addPlayer(Players.kComputer1, new AbstractComputerPlayerImpl());
        con.addPlayer(Players.kComputer2, new AbstractComputerPlayerImpl());
        con.addPlayer(Players.kComputer2, new AbstractComputerPlayerImpl());
        tab.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/presetDeck2.txt"));
        con.startGame();
        // Modified to test and fix defect #209.
        // card on discard pile is Y4
        // first playable card in hand is Y5
        Card expResult = new Card(Rank.wild, Color.wild);
        Card result = instance.findPlayableCard();
        System.out.println("Card: " + result);
        assertEquals(expResult, result);
        System.out.println("-- PASSED --");
    }


    public class AbstractComputerPlayerImpl extends AbstractComputerPlayer {

        public void showColorChooser() {

        }

        public Card getComputerMove() {
            return new Card(Rank.zero, Color.red);
        }

        public void doTurn() {

        }
    }
}