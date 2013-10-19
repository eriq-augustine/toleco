/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cero;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the integration test for ConsolePlayer.  It's like ConsolePlayerTest
 * except doesn't use fake classes.
 * @author ilya
 */
public class ConsolePlayerIntegrationTest {

    private ConsolePlayer consPlayer;
    private I_Player compPlayer1;
    private I_Player compPlayer2;
    private I_Player compPlayer3;

    private I_Table table;
    private I_Controller controller;
    private I_Options options;
    //private ArrayList<Card> drawDeck;

    public ConsolePlayerIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {                
        table = new Table();
        controller = new GameController();
        options = new GameOptions();

        controller.setOptions(options);

        consPlayer = new ConsolePlayer();
        compPlayer1 = new StupidPlayer();
        compPlayer2 = new StupidPlayer();
        compPlayer3 = new StupidPlayer();

        consPlayer.setControl(controller);
        consPlayer.setTable(table);

        compPlayer1.setControl(controller);
        compPlayer1.setTable(table);

        compPlayer2.setControl(controller);
        compPlayer2.setTable(table);

        compPlayer3.setControl(controller);
        compPlayer3.setTable(table);

        controller.setTable(table);

        controller.addPlayer(Players.kHuman, consPlayer);
        controller.addPlayer(Players.kComputer1, compPlayer1);
        controller.addPlayer(Players.kComputer2, compPlayer2);
        controller.addPlayer(Players.kComputer3, compPlayer3);

        //drawDeck = new ArrayList<Card>(1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resetDeck method, of class ConsolePlayer.
     */
    @Test
    public void testResetDeck() {
        consPlayer.resetDeck();
    }

    /**
     * Test of showGameOver method, of class ConsolePlayer.
     */
    @Test
    public void testShowGameOver() {
        consPlayer.showGameOver(Players.kHuman);
        //Expect to see a message about the Human winning
        consPlayer.showGameOver(Players.kComputer1);
        //Expect to see a message about computer1 winning
        consPlayer.showGameOver(Players.kComputer2);
        //Expect to see a message about computer2 winning
        consPlayer.showGameOver(Players.kComputer3);
        //Expect to see a message about computer3 winning
    }

    /**
     * Test of showColorChooser method, of class ConsolePlayer.
     */
    @Test
    public void testShowColorChooser() {
        System.setIn(new ByteArrayInputStream("blue\n".getBytes()));
        setUp();
        table.startGame();
        consPlayer.showColorChooser();
        //expect to see that blue was chosen as the color

        System.setIn(new ByteArrayInputStream("green\n".getBytes()));
        setUp();
        table.startGame();
        consPlayer.showColorChooser();
        //expect to see that green was chosen as the color

        System.setIn(new ByteArrayInputStream("red\n".getBytes()));
        setUp();
        table.startGame();
        consPlayer.showColorChooser();
        //expect to see that red was chosen as the color

        System.setIn(new ByteArrayInputStream("yellow\n".getBytes()));
        setUp();
        table.startGame();
        consPlayer.showColorChooser();
        //expect to see that yellow was chosen as the color
    }

    /**
     * Test of update method, of class ConsolePlayer.
     */
    @Test
    public void testUpdate() throws FileNotFoundException {

        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/rpresetDeck8.txt"));
        table.startGame();

        consPlayer.update(null, null);
        //expect to see all players with hands of Green 4's
        //the discarded card is a green 4
        //expect cards left in draw deck to be 6
    }

    /**
     * Test of showStatusMessage method, of class ConsolePlayer.
     */
    @Test
    public void testShowStatusMessage() {
        consPlayer.showStatusMessage("I like pie");
        //expect a status message of "I like pie"
    }

    /**
     * Test of playing a card in the doTurn method, of class ConsolePlayer.
     */
    @Test
    public void testPlayCard() throws FileNotFoundException {
        
 //       table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/rpresetDeck9.txt"));
//        table.startGame();

//        controller.startGame();

        System.setIn(new ByteArrayInputStream(
                "Cheese\nWW\nG4\nq\n".getBytes()));
        setUp();
        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/rpresetDeck9.txt"));
        controller.startGame();
        //consPlayer.doTurn();
        //expect a reprompt for a play command
        //expect a reprompt for a play command
        //expect the player having just played a G4
    }

    /**
     * Test of playing a card in the doTurn method, of class ConsolePlayer.
     */
    @Test
    public void testDrawCard() throws FileNotFoundException {

        System.setIn(new ByteArrayInputStream(
                "\nG4\nG4\nGT\nG4\nG4\nG4\nG4\nG4\nq\n".getBytes()));
        setUp();
        table.setDrawDeck(Deck.
                makeDeckFromFile("test/deckfiles/rpresetDeck12.txt"));
        controller.startGame();
        //consPlayer.doTurn();
        //expect the player to draw a card and add a Green 0 to its hand
    }

    /**
     * Test of playing a card in the doTurn method, of class ConsolePlayer.
     */
    @Test
    public void testPassCard() throws FileNotFoundException {
        System.setIn(new ByteArrayInputStream("p\nq\n".getBytes()));
        setUp();
        table.setDrawDeck(Deck.makeDeckFromFile(
            "test/deckfiles/rpresetDeck15.txt"));
        controller.startGame();
        //consPlayer.doTurn();
        //expect the player to draw a card and add a Green 0 to its hand

        System.setIn(new ByteArrayInputStream("p\nq\n".getBytes()));
        setUp();
        table.setDrawDeck(Deck.makeDeckFromFile(
            "test/deckfiles/rpresetDeck15.txt"));
        controller.startGame();
        //consPlayer.doTurn();
        //expect a pass action to have occured
    }

    /**
     * Test of setup method, of class ConsolePlayer.
     */
    @Test
    public void testSetup() throws FileNotFoundException {

        /*
        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/rpresetDeck8.txt"));
        table.startGame();

        System.setIn(new ByteArrayInputStream("q\n".getBytes()));

        consPlayer.setup();
        //expect to see the game prompt for input and quit
         */
    }

    /**
     * Test of getGameOptions method, of class ConsolePlayer.
     */
    @Test
    public void testGetGameOptions() {
        //assertEquals(options, consPlayer.getGameOptions());
    }

    /**
     * Test of showGameRules method, of class ConsolePlayer.
     */
    @Test
    public void testShowGameRules() {
        consPlayer.showGameRules();
        //Expect to see a list of game rules
    }

    /**
     * Test of showDevelopers method, of class ConsolePlayer.
     */
    @Test
    public void testShowDevelopers() {
        consPlayer.showDevelopers();
        //Expect to see a list of developers
    }

    /**
     * Test of showVersion method, of class ConsolePlayer.
     */
    @Test
    public void testShowVersion() {
        consPlayer.showVersion();
        //Expect to see the game version
    }

    /**
     * Test of showBuild method, of class ConsolePlayer.
     */
    @Test
    public void testShowBuild() {
        consPlayer.showVersion();
        //Expect to see the build
    }

    private class StupidPlayer implements I_Player
    {

        public void setControl(I_Controller gc) {
        }

        public void setTable(I_Table tbl) {
        }

        public void setup() {
        }

        public void resetDeck() {
        }

        public void doTurn() {
        }

        public void showGameOver(Players winner) {
        }

        public void showColorChooser() {
        }

        public void update(Observable obs, Object obj) {
        }

        public void showStatusMessage(String mes) {
        }

    }

}