package cero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * PlayerAction represents the different actions or moves a player can
 * make in a Cero game.
 *
 * @author Matt Carson
 * @version 1.0 - April 12, 2010
 */

public class PlayerActionTest
{

    public PlayerActionTest()
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
     * Test of get method, of class PlayerAction.
     */
    @Test
    public void testGet()
    {
        System.out.println("-- TESTING get(String name) --");

        // Testing color
        PlayerAction color = PlayerAction.color;
        assertEquals(PlayerAction.color, color.get("color"));

        // Testing play
        PlayerAction play = PlayerAction.play;
        assertEquals(PlayerAction.play, play.get("play"));

        // Testing pass
        PlayerAction pass = PlayerAction.pass;
        assertEquals(PlayerAction.pass, pass.get("pass"));

        // Testing draw
        PlayerAction draw = PlayerAction.draw;
        assertEquals(PlayerAction.draw, draw.get("draw"));

        System.out.println("\n-- PASSED --");
    }

}