package cero;

import java.util.Observable;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Integration Test for HardComputerPlayer
 *
 * @author Dirk Cummings
 * @version 1.0 - April 29, 2010
 */
public class HardComputerPlayerIntTest {

    public HardComputerPlayerIntTest() {
    }

    private I_Controller control;
    private I_Player player;
    private I_Player hplayer;
    private I_Table table;
    private Deck deck;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("-- TESTING HardComputerPlayer --");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("-- HardComputerPlayer TEST DONE --");
    }

    @Before
    public void setUp() {
        player = new HardComputerPlayer();
        hplayer = new StupidPlayer();
        control = new GameController();
        table = new Table();
        control.setTable(table);
        control.addPlayer(Players.kHuman, hplayer);
        control.addPlayer(Players.kComputer1, player);
        control.addPlayer(Players.kComputer2, hplayer);
        control.addPlayer(Players.kComputer3, hplayer);
        player.setControl(control);
        player.setTable(table);
    }
    /**
     * Test of doTurn method, of class HardComputerPlayer.
     * The computer player behaves the same way in all cases
     * so the following two tests differ aside from the deck.
     */
    @Test
    public void testDoTurn() throws Exception {
        System.out.println("-- TESTING doTurn --");
        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/presetDeck9.txt"));
        control.startGame();
        player.doTurn();
        assertEquals(Card.valueOf("R0"),table.getDiscardCard());
        System.out.println("-- PASSED --");
    }

    /**
     * Test another normal turn.
     */
    @Test
    public void testDoTurnDraw() throws Exception {
        System.out.println("-- TESTING doTurn() with drawing --");
        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/presetDeck8.txt"));
        control.startGame();
        player.doTurn();
        assertEquals(Card.valueOf("B8"), table.getDiscardCard());
        System.out.println("-- PASSED --");
    }

    @Test
    public void testDefect209() throws Exception {
        System.out.println("-- TESTING for Defect #209 (findPlayableCard()) --");
        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/presetDeck10.txt"));
        control.startGame();
        player.doTurn();
        assertEquals(Card.valueOf("R0"),table.getDiscardCard());
        System.out.println("-- PASSED --");
    }

    @Test
    public void testShowColorChooser() throws Exception {
        System.out.println("-- TESTING showColorChooser() & findMostFrequentColor()) --");
        table.setDrawDeck(Deck.makeDeckFromFile("test/deckfiles/presetDeck10.txt"));
        control.startGame();
        player.showColorChooser();

        assertEquals(Color.red, table.getColor());
        System.out.println("-- PASSED --");
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