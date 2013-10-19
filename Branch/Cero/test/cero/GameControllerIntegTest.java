package cero;

import cero.I_Controller.State;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 * GameController Integration Test
 * @author Raymond Wong
 */
public class GameControllerIntegTest
{
    private GameController gc;
    private I_Table table;
    private ScriptedPlayer hu, cp1, cp2, cp3;
    private I_Options opt;
    private ArrayList<Card> hp;

    public GameControllerIntegTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Before
    public void setUp() {
        gc = new GameController();
        table = new Table();
        opt = new GameOptions();
        gc.setTable(table);
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

    private void setPlayerHand()
    {
        final int kHandSize = 7;
        hp = new ArrayList<Card>(kHandSize);
        for (int card = 0; card < kHandSize; card++)
        {
            hp.add(new Card(Rank.four, Color.green));
        }
    }

    /**
     * Test Game 1.  Winner Human.  Skip card played. 
     */
    @Test
    public void testGame1()
    {
        System.out.println("\n-- GameController Integration test " +
            " Game 1 (Deck 9) --");
        //Human player and computer 1 has 6 G4's and 1 GS
        //Discard is G4.  
        //Computer 1 will be skipped a turn by the human player.
        hu = new ScriptedPlayer("0000000");
        cp1 = new ScriptedPlayer("0000000");
        cp2 = new ScriptedPlayer("PPPP000");
        cp3 = new ScriptedPlayer("PPPP000");

        reinit();
        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck9.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 9 not found");
        }

        gc.setDrawDeck(gameDeck);
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
    public void testGame2() 
    {
        System.out.println("\n-- GameController Integration Test" + 
            " Game 2 (Deck 10) --");
        // Human player will have 6 G4's and 1 R4.
        // Computer player 1 will have 5 G4's, 1 WW, and 1 WF
        hu = new ScriptedPlayer("0000000");
        cp1 = new ScriptedPlayer("0000g00r0");
        cp2 = new ScriptedPlayer("00000PP");
        cp3 = new ScriptedPlayer("PD000PP");

        reinit();

        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck10.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 10 not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kHuman).size());
        assertEquals(Players.kHuman, gc.getWinner());

        System.out.println("-- PASSED --");
    }

    /**
     * Test Game 3. Reverse Played. Winner Computer Player 1.
     */
    @Test
    public void testGame3() 
    {
        System.out.println("\n-- GameController Integration Test" +
            " Game 3 (Deck 11) --");
        // Computer 1 has a GR card to reverse order of game.
        hu = new ScriptedPlayer("000DD00");
        cp1 = new ScriptedPlayer("0000000");
        cp2 = new ScriptedPlayer("DD00000");
        cp3 = new ScriptedPlayer("DD00000");

        reinit();

        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck11.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 11 not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kComputer1).size());
        assertEquals(Players.kComputer1, gc.getWinner());

        System.out.println("-- PASSED --");
    }

    /**
     * Test Game 4. Draw Two. Winner Computer Player 3.
     */
    @Test
    public void testGame4() 
    {
        System.out.println("\n-- GameController Integration Test" +
            " Game 4 (Deck 12) --");
        // Human player has 6 G4's, 1 GT
        // Computer player 1 has 6 G4's, 1 GT
        // Computer player 2 has 7 G4's
        // Computer player 3 has 5 G4's, 2 GT
        hu = new ScriptedPlayer("0000000");
        cp1 = new ScriptedPlayer("0000000");
        cp2 = new ScriptedPlayer("0000000");
        cp3 = new ScriptedPlayer("0000000");

        reinit();

        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck12.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 12 not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kComputer3).size());
        assertEquals(Players.kComputer3, gc.getWinner());

        System.out.println("-- PASSED --");
    }

    /**
     * Test of setDrawDeck method, of class GameController.
     */
    @Test
    public void testSetDrawDeck() 
    {
        Deck gameDeck = null;
        try 
        {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck8.txt");
        }
        catch (java.io.FileNotFoundException fexc)
        {
            System.out.println("Preset deck 8 file not found");
        }

        gc.setDrawDeck(gameDeck);
        setPlayerHand();
        table.startGame();
        assertEquals(table.getHand(Players.kHuman), hp);
        assertEquals(table.getHand(Players.kComputer1), hp);
        assertEquals(table.getHand(Players.kComputer2), hp);
        assertEquals(table.getHand(Players.kComputer3), hp);
        assertEquals(table.getDiscardCard(),
            new Card(Rank.four, Color.green));
        assertEquals(table.getColor(), Color.green);
        assertEquals(table.getDiscardDeckSize(), 1);
        assertEquals(table.getDrawDeckSize(), 7);
    }

    /**
     * Test of setDiscardDeck method, of class GameController.
     */
    @Test
    public void testSetDiscardDeck()
    {
        Deck aDeck = new Deck();
        gc.setDrawDeck(aDeck);
        table.startGame();
        
        ArrayList<Card> discardDeck = new ArrayList<Card>(3);
        discardDeck.add(new Card(Rank.four, Color.blue));
        discardDeck.add(new Card(Rank.one, Color.red));
        discardDeck.add(new Card(Rank.zero, Color.yellow));

        Deck gameDeck = new Deck(discardDeck);
        gc.setDiscardDeck(gameDeck);
        assertEquals(table.getDiscardDeckSize(), 3);
    }

    /**
     * Test of setOptions method, of class GameController.
     */
    @Test
    public void testGetSetOptions()
    {
        gc.setOptions(opt);
        assertEquals(opt, gc.getOptions());
    }

    /**
     * Test of getPlayOrder method, of class GameController.
     */
    @Test
    public void testGetPlayOrder()
    {
        //Every player has 7 G4's to play till end up game.
        hu = new ScriptedPlayer("0000000");
        cp1 = new ScriptedPlayer("0000000");
        cp2 = new ScriptedPlayer("0000000");
        cp3 = new ScriptedPlayer("0000000");
        
        reinit();
        
        Deck gameDeck = null;
        try
        {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck8.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 8 file not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();

        //Human player wins, so still at the top of the list of play order
        assertEquals(Players.kComputer1, gc.getPlayOrder().get(1));
        assertEquals(Players.kComputer2, gc.getPlayOrder().get(2));
        assertEquals(Players.kComputer3, gc.getPlayOrder().get(3));
        assertEquals(Players.kHuman, gc.getPlayOrder().get(0));
    }

    @Test
    public void testRestGame()
    {
        System.out.println("\n-- GameController Integration test " +
            " Game 1 (Deck 9) --");
        //Human player and computer 1 has 6 G4's and 1 GS
        //Discard is G4.
        //Computer 1 will be skipped a turn by the human player.
        hu = new ScriptedPlayer("0000000");
        cp1 = new ScriptedPlayer("0000000");
        cp2 = new ScriptedPlayer("PPPP000");
        cp3 = new ScriptedPlayer("PPPP000");

        reinit();
        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck9.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 9 not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();
        assertEquals(State.gameOver, gc.getState());
        assertEquals(0, table.getHand(Players.kHuman).size());
        assertEquals(Players.kHuman, gc.getWinner());

        gc.resetGame();

        //Human player wins, so still at the top of the list of play order
        assertEquals(Players.kComputer1, gc.getPlayOrder().get(1));
        assertEquals(Players.kComputer2, gc.getPlayOrder().get(2));
        assertEquals(Players.kComputer3, gc.getPlayOrder().get(3));
        assertEquals(Players.kHuman, gc.getPlayOrder().get(0));

        System.out.println("-- PASSED --");
    }
}