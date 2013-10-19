package cero;

import java.io.FileNotFoundException;
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
 * @author Matt Carson
 */
public class EasyComputerPlayerIntTest {

    private GameController control;
    private I_Player player;
    private I_Player hplayer;
    private Table table;
    private ArrayList<Card> hand;
    
    public EasyComputerPlayerIntTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("-- BEGIN EasyComputerPlayer Intg8 Test --");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("-- END EasyComputerPlayer Intg8 Test --");
    }

    @Before

    
    public void setUp() throws Exception {
        player = new EasyComputerPlayer();
        hplayer = new StupidPlayer();
        control = new GameController();
        table = new Table();

        // Adding console mode support as per Change Request #17
        control.setConsoleMode(true);
        control.setTable(table);
        player.setControl(control);
        player.setTable(table);
        hplayer.setControl(control);
        hplayer.setTable(table);
        control.addPlayer(Players.kComputer1, player);
        control.addPlayer(Players.kHuman, hplayer);
        control.addPlayer(Players.kComputer2, hplayer);
        control.addPlayer(Players.kComputer3, hplayer);
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
    public void testDoTurn() throws FileNotFoundException {
        
        System.out.println("-- TESTING doTurn(): EasyComputerPlayer -- ");
        //Make a hand
        Deck presetDeck = Deck.makeDeckFromFile("test/deckfiles/presetDeck8.txt");
        table.setDrawDeck(presetDeck);
        control.startGame();
        player.doTurn();
        assertEquals(Card.valueOf("B8"), table.getDiscardCard());

    }


    /**
     * Test another normal turn.
     */
    @Test
    public void testDoTurnDraw() throws FileNotFoundException {
        System.out.println("-- TESTING doTurnDraw(): EasyComputerPlayer -- ");

        Deck presetDeck = Deck.makeDeckFromFile("test/deckfiles/presetDeck11.txt");
        table.setDrawDeck(presetDeck);
        control.startGame();
        player.doTurn();
        assertEquals(Card.valueOf("B6"), table.getDiscardCard());

    }


    /**
     * Test of showColorChooser method, of class EasyComputerPlayer.
     * For easy, we don't care what color it returns, as long as it returns
     * anything at all.
     */
    @Test
    public void testShowColorChooser() throws Exception {
        System.out.println("-- TESTING showColorChooser(): EasyComputerPlayer -- ");
        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/presetDeck1.txt"));
        table.startGame();
        player.showColorChooser();
        //assertNotNull(table.getColor());
    }


    public class StupidPlayer implements I_Player {

        public void showColorChooser() {

        }

        public void doTurn() {

        }

        public void setControl(I_Controller gc) {
        }

        public void setTable(I_Table tbl) {
        }

        public void setup() {
        }

        public void resetDeck() {
        }

        public void showGameOver(Players winner) {
        }

        public void update(Observable obs, Object obj) {
        }

        public void showStatusMessage(String mes) {

        }
    }
}