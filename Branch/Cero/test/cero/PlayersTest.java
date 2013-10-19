package cero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Players enumerates the participants in the Cero game.
 * @author Matt Carson
 * @version 1.0 - April 12, 2010
 */

public class PlayersTest
{

    public PlayersTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of next method, of class Players.
     */
    @Test
    public void testNext()
    {
        System.out.println("-- TESTING next(Players aPlayer) --");

        Players player = Players.kComputer1;
        assertEquals(Players.kComputer2, player.next(player));

        player = Players.kComputer2;
        assertEquals(Players.kComputer3, player.next(player));

        player = Players.kComputer3;
        assertEquals(Players.kHuman, player.next(player));

        player = Players.kHuman;
        assertEquals(Players.kComputer1, player.next(player));
        
        System.out.println("\n-- PASSED --");
    }

}