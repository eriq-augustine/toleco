package cero;

import junit.framework.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

/**
 * ConsolePlayerTest is a unit test to check the functionality of
 * ConsolePlayer actions.
 *
 * @author Raymond Wong
 * @date 4/15/2010
 */
public class ConsolePlayerTest extends TestCase
{
    /**
     * FakeController was implemented by Dirk Cummings and submitted to the
     * repository for general use by the team.
     */
    private FakeController fc2;
    private FakeTable2 ft2;
    private ConsolePlayer consPlayer;
    private ArrayList<Card> drawDeck;

    public ConsolePlayerTest(String testName)
    {
        super(testName);
    }

    public void setup()
    {
        consPlayer = new ConsolePlayer();
        ft2 = new FakeTable2();
        fc2 = new FakeController();
        drawDeck = new ArrayList<Card>(1);
        drawDeck.add(new FakeCard(Rank.eight, Color.green));
    }

    /**
     * Test of resetDeck method, of class ConsolePlayer.
     */
    public void testResetDeck()
    {
        System.out.println("Test resetDeck");
        setup();
        consPlayer.setTable(ft2);
        consPlayer.resetDeck();
        assertTrue(ft2.fakeResetDeckSize() == 1);
    }

    /**
     * Test of showGameOver method, of class ConsolePlayer.
     */
    public void testShowGameOver()
    {
        System.out.println("Test showGameOver");
        setup();
        consPlayer.showGameOver(Players.kHuman);
        //Expect to see a message about human player winning
        consPlayer.showGameOver(Players.kComputer1);
        //Expect to see a message about human player losing
    }

    /**
     * Test of showColorChooser method, of class ConsolePlayer.
     */
    public void testShowColorChooser()
    {
        System.out.println("Test showColorChooser");

        byte[] buf = "blue\n".getBytes();
        ByteArrayInputStream newIn = new ByteArrayInputStream(buf);
        System.setIn(newIn);

        setup();
        consPlayer.setControl(fc2);
        consPlayer.showColorChooser();
        //Expect to see a prompt asking to choose a color
        assertEquals(fc2.getLastAction(), "colorblue");
    }

    /**
     * Test of update method, of class ConsolePlayer.
     */
    public void testUpdate()
    {
        System.out.println("Test update");
        setup();

        ft2.setDrawDeck(new FakeDeck(drawDeck));
        consPlayer.setTable(ft2);
        consPlayer.setControl(fc2);
        consPlayer.update(null, null);
        //Expect to see each player with a G0 in their hand
        // draw deck size of 1, and a discarded card G4
    }

    /**
     * Test of showStatusMessage method, of class ConsolePlayer.
     */
    public void testShowStatusMessage()
    {
        System.out.println("Test showStatusMessage");
        setup();
        String message = "Played a blue two";
        consPlayer.showStatusMessage(message);
        //Expect to see a message "Played a blue two"
    }

    /**
     * Test of doTurn method, of class ConsolePlayer.
     */
    public void testPlayCard()
    {
        System.out.println("Test play card");

        byte[] buf = "G0\nq\n".getBytes();
        ByteArrayInputStream newIn = new ByteArrayInputStream(buf);
        System.setIn(newIn);

        setup();
        ft2.setDrawDeck(new FakeDeck(drawDeck));
        consPlayer.setControl(fc2);
        consPlayer.setTable(ft2);

        consPlayer.doTurn();
        //Also expect to see a draw deck size of 1,
        //my current hand has a G0
        //current discard is G4

        assertEquals(fc2.getLastAction(), "0");
    }

    /**
     * Test of doTurn method, of class ConsolePlayer.
     */
    public void testDrawCard()
    {
        System.out.println("Test draw card");

        byte[] buf = "\nq\n".getBytes();
        ByteArrayInputStream newIn = new ByteArrayInputStream(buf);
        System.setIn(newIn);

        setup();
        FakeTable3 ft3 = new FakeTable3();
        ft3.setDrawDeck(new FakeDeck(drawDeck));
        consPlayer.setControl(fc2);
        consPlayer.setTable(ft3);

        consPlayer.doTurn();
        //expect to see a draw deck size of 1,
        //my current hand has a G0
        //current discard is Y8

        assertEquals(fc2.getLastAction(), "draw");
    }

    /**
     * Test of doTurn method, of class ConsolePlayer.
     */
    public void testPassTurn()
    {
        System.out.println("Test pass turn");

        byte[] buf = "p\nq\n".getBytes();
        ByteArrayInputStream newIn = new ByteArrayInputStream(buf);
        System.setIn(newIn);

        setup();
        FakeTable3 ft3 = new FakeTable3();
        ft3.setDrawDeck(new FakeDeck(new ArrayList<Card>()));
        fc2.setTable(ft3);
        consPlayer.setControl(fc2);
        consPlayer.setTable(ft3);

        consPlayer.doTurn();
        //expect to see a discard deck size of 0,
        //my current hand has a G0
        //current discard is Y8

        assertEquals(fc2.getLastAction(), "pass");
    }

    /**
     * Test of showGameRules method, of class ConsolePlayer.
     */
    public void testShowGameRules()
    {
        System.out.println("Test showGameRules");
        setup();
        consPlayer.showGameRules();
        //expect to see a list of game rules for Cero.
    }

    /**
     * Test of showDevelopers method, of class ConsolePlayer.
     */
    public void testShowDevelopers()
    {
        System.out.println("Test showDevelopers");
        setup();
        consPlayer.showDevelopers();
        //expect to see the name of the developers of Cero.
    }

    /**
     * Test of showVersion method, of class ConsolePlayer.
     */
    public void testShowVersion()
    {
        System.out.println("Test showVersion");
        setup();
        consPlayer.showVersion();
        //expect to see the version of Cero.
    }

    /**
     * Test of showBuild method, of class ConsolePlayer.
     */
    public void testShowBuild()
    {
        System.out.println("Test showBuild");
        setup();
        consPlayer.showBuild();
        //expect to see the build version of Cero.
    }

    /**
     * Test of setup method, of class ConsolePlayer.
     */
    public void testSetup()
    {
        /*
        System.out.println("Test setup");

        byte[] buf = "q".getBytes();
        ByteArrayInputStream newIn = new ByteArrayInputStream(buf);
        System.setIn(newIn);

        setup();
        ft2.setDrawDeck(new FakeDeck(drawDeck));
        consPlayer.setControl(fc2);
        consPlayer.setTable(ft2);
        consPlayer.setup();
        //expect to see a table setup and a prompting message
        //each player will have a G0
        //current discard is G4
        //current draw deck size is 1
         */
    }

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

        public boolean equals(Object aThat)
        {
            if (!(aThat instanceof Card))
            {
                return false;
            }
            else if (!cardRank.equals(((Card) aThat).rank()))
            {
                return false;
            }
            else if (!cardColor.equals(((Card) aThat).color()))
            {
                return false;
            }
            return true;
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

    /**
     * FakeTable was implemented by Dirk Cummings and submitted to the
     * repository for general use by the team.  FakeTable2 inherits FakeTable
     * and overrides some of the methods for the purposes of testing
     * ConsolePlayer.
     */
    class FakeTable2 extends FakeTable
    {
        private Deck drawDeck;
        private ArrayList<Card> hand;
        private FakeDeck resetDeckVersion;

        public FakeTable2()
        {
            hand = new ArrayList<Card>();
            hand.add(new FakeCard(Rank.zero, Color.green));
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
            return new FakeCard(Rank.four, Color.green);
        }

        public Color getColor()
        {
            return Color.green;
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

    class FakeTable3 extends FakeTable2
    {
        public boolean isPlayableCard(Card card)
        {
            return false;
        }

        public Card getDiscardCard() {
            return new FakeCard(Rank.eight, Color.yellow);
        }
    }
}
