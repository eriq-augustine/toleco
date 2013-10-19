/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cero;

import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.Application;

/**
 * Tests the SwingPlayer
 * @author ilya
 */
public class SwingPlayerTest {

    /*public static I_Controller controller;
    public static I_Table table;
    public static I_Options options;*/

    public SwingPlayerTest() {
        
        FakeSingleFrameApplication.main(null);

        //Let it sleep to ensure the launch event has time to call startup
        sleep(1000);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        FakeSingleFrameApplication.setOptions(new FakeOptions());
        //FakeSingleFrameApplication.setTable(new FakeTable());

        I_Table testTable = new FakeTable2();

        ArrayList<Card> drawDeck = new ArrayList<Card>(10);
        drawDeck.add(new FakeCard(Rank.eight, Color.red));
        drawDeck.add(new FakeCard(Rank.nine, Color.blue));
        drawDeck.add(new FakeCard(Rank.wild, Color.wild));
        drawDeck.add(new FakeCard(Rank.drawtwo, Color.yellow));

        FakeSingleFrameApplication.setTable(testTable);

        testTable.setDrawDeck(new FakeDeck(drawDeck));

        FakeSingleFrameApplication.setController(new FakeController());
    }

    @After
    public void tearDown() {
        sleep(1000);
    }

    /**
     * Conveneince method for forcing the thread to sleep
     * @param millis How many milliseconds to sleep
     */
    private void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch(Exception expt)
        {
            expt.printStackTrace();
        }
    }

    /**
     * Test of update method, of class SwingPlayer.
     */
    @Test
    public void testUpdate() {

        System.out.println("--TESTING UPDATE---");

        I_Table testTable = new FakeTable2();

        ArrayList<Card> drawDeck = new ArrayList<Card>(10);
        drawDeck.add(new FakeCard(Rank.eight, Color.red));
        drawDeck.add(new FakeCard(Rank.nine, Color.blue));
        drawDeck.add(new FakeCard(Rank.wild, Color.wild));
        drawDeck.add(new FakeCard(Rank.drawtwo, Color.yellow));

        FakeSingleFrameApplication.setTable(testTable);

        testTable.setDrawDeck(new FakeDeck(drawDeck));

        FakeSingleFrameApplication.appUI.update(null, null);
        //The human card selector should be enabled
        //The human card area should be highlighted to indicate player's turn
        //The computer cards should be displayed and not highlighted to indicate
            //it's not their turn
        //The deck panel should be visible and have the draw button
            //enabled without a discard card placed down but showing the card
            //back
        //The arrows in the corners should all be clockwise

        sleep(10000);
    }

    /**
     * Test of showAboutBox method, of class SwingPlayer.
     */
    @Test
    public void testShowAboutBox() {

        System.out.println("--TESTING SHOW ABOUT BOX---");

        FakeSingleFrameApplication.appUI.showAboutBox();
        //Expect to see an about box pop up
    }

    /**
     * Test of showHowToPlayBox method, of class SwingPlayer.
     */
    @Test
    public void testShowHowToPlayBox() {

        System.out.println("--TESTING SHOW HOW TO PLAY BOX---");

        FakeSingleFrameApplication.appUI.showHowToPlayBox();
        //Expect to see a how to play box pop up
    }

    /**
     * Test of showOptionsMenu method, of class SwingPlayer.
     */
    @Test
    public void testShowOptionsMenu() {

        System.out.println("--TESTING SHOW OPTIONS MENU---");

        FakeSingleFrameApplication.appUI.showHowToPlayBox();
        //Expect to see an options menu pop up
    }

    /**
     * Test of setup method, of class SwingPlayer.
     */
    @Test
    public void testSetup() {

        System.out.println("--TESTING SETUP---");

        FakeSingleFrameApplication.appUI.setup();
        //expect the same result as after calling update, described below
    }

    /**
     * Test of doTurn method, of class SwingPlayer.
     */
    @Test
    public void testDoTurn() {

        System.out.println("--TESTING DO TURN---");

        FakeSingleFrameApplication.appUI.doTurn();

        //draw button should be enabled now
        //human card chooser should be enabled
    }

    /**
     * Test of showGameOver method, of class SwingPlayer.
     */
    @Test
    public void testShowGameOver() {

        System.out.println("--TESTING SHOW SHOW GAME OVER---");

        FakeSingleFrameApplication.appUI.showGameOver(Players.kHuman);
        //Everything in the middle should be hidden except for the message area
        //Message should say you won
        //Player's color chooser background should be yellow
            //while everyone else's is default colored

        sleep(1000);

        FakeSingleFrameApplication.appUI.showGameOver(Players.kComputer1);
        //Computer 1's color chooser background should be yellow
            //while everyone else's is default colored

        sleep(1000);

        FakeSingleFrameApplication.appUI.showGameOver(Players.kComputer2);
        //Computer 2's color chooser background should be yellow
            //while everyone else's is default colored

        sleep(1000);

        FakeSingleFrameApplication.appUI.showGameOver(Players.kComputer3);
        //Computer 3's color chooser background should be yellow
            //while everyone else's is default colored
    }

    /**
     * Test of showColorChooser method, of class SwingPlayer.
     */
    @Test
    public void testShowColorChooser() {

        System.out.println("--TESTING SHOW COLOR CHOOSER---");

        FakeSingleFrameApplication.appUI.showColorChooser();
        //Deck panel background should be the color of the background
        //Draw button and deck back is not visible
        //All the color chooser buttons should be visible

        sleep(10000);
    }

    /**
     * Test of showStatusMessage method, of class SwingPlayer.
     */
    @Test
    public void testShowStatusMessage() {

        System.out.println("--TESTING SHOW STATUS MESSAGE---");

        FakeSingleFrameApplication.appUI.showStatusMessage("I like pie!!!");

        //The status message should say I like pie!!!
    }

}

//Written by Raymond in ConsolePlayerTest

class FakeCard extends Card
{
    private Rank cardRank;
    private Color cardColor;

    public FakeCard(Rank rank, Color color)
    {
        super(rank, color);
        cardRank = rank;
        cardColor = color;
    }

    public String toString()
    {
        if (cardRank.equals(Rank.four) && cardColor.equals(Color.green))
        {
            return "G4";
        }
        else if (cardRank.equals(Rank.zero) &&
            cardColor.equals(Color.green))
        {
            return "G0";
        }
        else if (cardRank.equals(Rank.eight) &&
            cardColor.equals(Color.yellow))
        {
            return "Y8";
        }
        else
        {
            System.out.println("Can't test with this card");
            return "G8";
        }
    }
}

class FakeDeck extends Deck
{
    private ArrayList<Card> myDeck;

    public FakeDeck(ArrayList<Card> deck)
    {
        myDeck = deck;
    }

    public int size()
    {
        return myDeck.size();
    }
}

class FakeTable2 extends FakeTable
{
    private Deck drawDeck;
    private ArrayList<Card> hand;
    private FakeDeck resetDeckVersion;

    public FakeTable2()
    {
        hand = new ArrayList<Card>();

        for(int num = 0; num < 5; num ++)
        {
            hand.add(new FakeCard(Rank.zero, Color.green));
            hand.add(new FakeCard(Rank.four, Color.green));
            hand.add(new FakeCard(Rank.eight, Color.yellow));
        }
    }

    public int getDrawDeckSize()
    {
        return drawDeck.size();
    }

    public int getDiscardDeckSize()
    {
        return 1;
    }

    public ArrayList<Card> getHand(Players player)
    {
        return hand;
    }

    public Card getDiscardCard()
    {
        return new FakeCard(Rank.eight, Color.yellow);
    }

    public Color getColor()
    {
        return Color.yellow;
    }

    public void resetDeck()
    {
        ArrayList<Card> fakeResetDeck = new ArrayList<Card>();
        fakeResetDeck.add(new FakeCard(Rank.seven, Color.blue));
        resetDeckVersion = new FakeDeck(fakeResetDeck);
    }

    public int fakeResetDeckSize() {
        return resetDeckVersion.size();
    }

    public void setDrawDeck(Deck presetDeck)
    {
        drawDeck = presetDeck;
    }

    public boolean isPlayableCard(Card card)
    {
        return true;
    }
}