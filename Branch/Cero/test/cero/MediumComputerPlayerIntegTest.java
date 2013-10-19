
package cero;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Integration Test for MediumComputerPlayer
 * @author Raymond Wong
 */
public class MediumComputerPlayerIntegTest {

    private GameController gc;
    private MediumComputerPlayer cp1;
    private ScriptedPlayer hu, cp2, cp3;
    private Table table;

    public MediumComputerPlayerIntegTest() {
    }

    @Before
    public void setUp() {
        cp1 = new MediumComputerPlayer();
        gc = new GameController();
        table = new Table();
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

    /**
     * Test of doTurn method, of class MediumComputerPlayer.
     */
    @Test
    public void testDoTurnPlay() {
        System.out.println("MediumComputerPlayer Integration - doTurn Play");

        //All other players will pass.
        hu = new ScriptedPlayer("PPPPPPP");
        cp2 = new ScriptedPlayer("PPPPPPP");
        cp3 = new ScriptedPlayer("PPPPPPP");

        reinit();
        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck13.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 13 not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();
        assertEquals(table.getColor(), Color.green);
        assertEquals(table.getDiscardCard(), new Card(Rank.seven, Color.green));
    }

    /**
     * Test of doTurn method, of class MediumComputerPlayer.
     */
    @Test
    public void testDoTurnDraw() {
        System.out.println("MediumComputerPlayer Integration - doTurn Draw");

        //All other players will pass.
        hu = new ScriptedPlayer("PPPPPPPP");
        cp2 = new ScriptedPlayer("PPPPPPPP");
        cp3 = new ScriptedPlayer("PPPPPPPP");

        reinit();
        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck14.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 14 not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();
        assertEquals(table.getColor(), Color.green);
        assertEquals(table.getDiscardCard(), new Card(Rank.seven, Color.green));
    }

    /**
     * Test of showColorChooser method, of class MediumComputerPlayer.
     */
    @Test
    public void testShowColorChooser() {
        System.out.println("MediumComputerPlayerIntegration -" +
            " showColorChooser");

        hu = new ScriptedPlayer("PPPPPPP");
        cp2 = new ScriptedPlayer("PPPPPPP");
        cp3 = new ScriptedPlayer("PPPPPPP");

        reinit();

        Deck gameDeck = null;

        try {
            gameDeck = Deck.makeDeckFromFile("test/deckfiles/rpresetDeck15.txt");
        }
        catch (java.io.FileNotFoundException exc)
        {
            System.out.println("Preset deck 15 not found");
        }

        gc.setDrawDeck(gameDeck);
        gc.startGame();

        assertEquals(table.getColor(), Color.red);
    }

}