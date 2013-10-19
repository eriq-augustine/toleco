/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cero;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author edgustaf
 */
public class MediumComputerPlayerTest {

    public MediumComputerPlayerTest() {
    }
    private FakeController control;
    private MediumComputerPlayer player;
    private FakeTable table;
    private ArrayList<Card> hand;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        player = new MediumComputerPlayer();
        control = new FakeController();
        table = new FakeTable();
        control.setTable(table);
        player.setControl(control);
        player.setTable(table);
        hand = new ArrayList<Card>();
    }


    /**
     * Test of doTurn method, of class EasyComputerPlayer.
     * The computer player behaves the same way in all cases
     * so the following two tests differ aside from the deck.
     */
    @Test
    public void testDoTurn() {

        System.out.println("Testing doTurn() in MediumComputerPlayer: ");
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
        System.out.println("Testing doTurn() in MediumComputerPlayer " +
                "with drawing: ");
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

    @Test
    public void testShowColorChooser() {
        System.out.println("Testing showColorChooser() in " +
                "MediumComputerPlayer\n(also tests findMostFrequentColor()):");
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("G5"));
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("R0"));
        hand.add(Card.valueOf("G9"));
        hand.add(Card.valueOf("Y4"));
        hand.add(Card.valueOf("YT"));
        table.setHand(hand);
        player.showColorChooser();

        assertEquals("colorred",control.getLastAction());

    }

    @Test
    public void testDefect254() {
        System.out.println("Testing Defect #254 (all wilds)");
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        table.setHand(hand);
        player.showColorChooser();

        assertNotNull(control.getLastAction());

    }

    @Test
    public void testDefect254b() {
        System.out.println("Testing Defect #254 (Mostly wilds)");
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("WW"));
        hand.add(Card.valueOf("R9"));
        table.setHand(hand);
        player.showColorChooser();

        assertEquals("colorred",control.getLastAction());

    }
}
