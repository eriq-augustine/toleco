/*
 * JUnit test for the Color class.
 *
 * @author Annabel Hung
 * @version 1.0 - April 20, 2010
 */

package cero;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractComputerPlayerTest {

    public AbstractComputerPlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resetDeck method, of class AbstractComputerPlayer.
     */
    @Test
    public void testResetDeck() {
        System.out.println("resetDeck");
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.resetDeck();
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of setControl method, of class AbstractComputerPlayer.
     */
    @Test
    public void testSetControl() {
        System.out.println("setControl");
        I_Controller gc = new FakeController();
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.setControl(gc);
        assertEquals(gc, instance.getControl());
    }

    /**
     * Test of setTable method, of class AbstractComputerPlayer.
     */
    @Test
    public void testSetTable() {
        System.out.println("setTable");
        I_Table tbl = new FakeTable();
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.setTable(tbl);
        assertEquals(tbl, instance.getTable());
    }

    /**
     * Test of setup method, of class AbstractComputerPlayer.
     */
    @Test
    public void testSetup() {
        System.out.println("setup");
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.setup();
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of showColorChooser method, of class AbstractComputerPlayer.
     */
    @Test
    public void testShowColorChooser() {
        System.out.println("showColorChooser");
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.showColorChooser();
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of showGameOver method, of class AbstractComputerPlayer.
     */
    @Test
    public void testShowGameOver() {
        System.out.println("showGameOver");
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
        System.out.println("update");
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
        System.out.println("getComputerMove");
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        Card expResult = new Card(Rank.zero, Color.red);
        Card result = instance.getComputerMove();
        assertEquals(expResult, result);
    }

    /**
     * Test of showStatusMessage method, of class AbstractComputerPlayer.
     */
    @Test
    public void testShowStatusMessage() {
        System.out.println("showStatusMessage");
        String mes = "";
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.showStatusMessage(mes);
        System.out.println("-PASS: Computer players do not use this method.");
    }

    /**
     * Test of findPlayableCard method, of class AbstractComputerPlayer.
     */
    @Test
    public void testFindPlayableCard() {
        System.out.println("findPlayableCard");
        FakeController fakeC = new FakeController();
        ACPFakeTable fakeT = new ACPFakeTable();
        AbstractComputerPlayer instance = new AbstractComputerPlayerImpl();
        instance.setControl(fakeC);
        instance.setTable(fakeT);
        // Modified to test and fix defect #209.
        // card on discard pile is Y4
        // first playable card in hand is Y5
        Card expResult = new Card(Rank.five, Color.yellow);
        Card result = instance.findPlayableCard();
        System.out.println("Card: " + result);
        assertEquals(expResult, result);
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

    public class ACPFakeTable extends FakeTable {

        // Added to test and fix defect #209.
        public ArrayList<Card> getHand(Players player)
        {
            ArrayList<Card> hand = new ArrayList<Card>();
            // wrong rank, wrong color = NOT playable
            hand.add(new Card(Rank.three, Color.red));
            // wrong rank, right color = PLAYABLE
            hand.add(new Card(Rank.five, Color.yellow));
            // right rank, wrong color = PLAYABLE
            hand.add(new Card(Rank.four, Color.green));
            // right rank, right color = PLAYABLE
            hand.add(new Card(Rank.four, Color.yellow));
            // wrong rank, right color = PLAYABLE
            hand.add(new Card(Rank.three, Color.yellow));
            return hand;
        }

        // Added to test and fix defect #209.
        public boolean isPlayableCard(Card card)
        {
            // card on discard pile is Y4
            Card match = new Card(Rank.four, Color.yellow);
            return card.color().equals(match.color()) ||
                    card.rank().equals(match.rank()) ||
                    card.rank().equals(Rank.wild);
        }
 
    }

}