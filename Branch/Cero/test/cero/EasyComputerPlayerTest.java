package cero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
/**
 * Test class for the EasyComputerPlayer.
 * Does not do much to test showColorChooser, since its output is random, and
 * doesn't really matter anyway.
 * @author Eric Gustafson
 */
public class EasyComputerPlayerTest {

    private FakeController control;
    private EasyComputerPlayer player;
    private FakeTable table;
    private ArrayList<Card> hand;
    
    public EasyComputerPlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before

    
    public void setUp() {
        player = new EasyComputerPlayer();
        control = new FakeController();
        table = new FakeTable();
        control.setTable(table);
        player.setControl(control);
        player.setTable(table);
        hand = new ArrayList<Card>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of doTurn method, of class EasyComputerPlayer.
     * The computer player behaves the same way in all cases
     * so the following two tests differ aside from the deck.
     */
    @Test
    public void testDoTurn() {
        
        System.out.println("Testing doTurn() in EasyComputerPlayer: ");
        //Make a hand
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("B8"));
        hand.add(Card.valueOf("Y6"));
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("G9"));
        hand.add(Card.valueOf("GS"));
        hand.add(Card.valueOf("YT"));
        table.setHand(hand);
        table.performDiscardAction(null, null);
        player.doTurn();
        assertEquals("2",control.getLastAction());

    }


    /**
     * Test another normal turn.
     */
    @Test
    public void testDoTurnDraw() {
        System.out.println("Testing doTurn() in EasyComputerPlayer with " +
                "drawing: ");

        hand.add(Card.valueOf("B8"));
        hand.add(Card.valueOf("G5"));
        hand.add(Card.valueOf("Y6"));
        hand.add(Card.valueOf("RR"));
        hand.add(Card.valueOf("G9"));
        hand.add(Card.valueOf("Y4"));
        hand.add(Card.valueOf("YT"));
        table.setHand(hand);

        table.performDiscardAction(null, null);
        table.performDrawAction(null);
        player.doTurn();
        assertEquals("6",control.getLastAction());

    }

    @Test
    public void testDefect209() {
        System.out.println("Testing for Defect #209 (findPlayableCard()):");
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("G5"));
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("G9"));
        hand.add(Card.valueOf("Y4"));
        hand.add(Card.valueOf("YT"));
        table.setHand(hand);
        table.performDiscardAction(null, null);
        
        player.doTurn();
        assertEquals("1",control.getLastAction());

    }


    /**
     * Test of showColorChooser method, of class EasyComputerPlayer.
     * For easy, we don't care what color it returns, as long as it returns
     * anything at all.
     */
    @Test
    public void testShowColorChooser() {
        System.out.println("Testing showColorChooser() in EasyComputerPlayer:");
        player.showColorChooser();
        assertNotNull(control.getLastAction());
    }

}