package cero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The ranks of a standard playing card.
 *
 * @author Matt Carson
 * @version 1.0 - April 12, 2010
 */

public class RankTest
{

    public RankTest()
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
    public void setUp() {
    }

    @After
    public void tearDown()
    {
    }


    /**
     * Test of symbol method, of class Rank.
     */
    @Test
    public void testSymbol()
    {
        System.out.println("-- TESTING next(Players aPlayer) --");

        Rank rank = Rank.zero;
        assertEquals('0', rank.symbol());

        rank = Rank.one;
        assertEquals('1', rank.symbol());

        rank = Rank.two;
        assertEquals('2', rank.symbol());

        rank = Rank.three;
        assertEquals('3', rank.symbol());

        rank = Rank.four;
        assertEquals('4', rank.symbol());

        rank = Rank.five;
        assertEquals('5', rank.symbol());

        rank = Rank.six;
        assertEquals('6', rank.symbol());
      
        rank = Rank.seven;
        assertEquals('7', rank.symbol());

        rank = Rank.eight;
        assertEquals('8', rank.symbol());

        rank = Rank.nine;
        assertEquals('9', rank.symbol());

        rank = Rank.drawtwo;
        assertEquals('T', rank.symbol());

        rank = Rank.reverse;
        assertEquals('R', rank.symbol());

        rank = Rank.skip;
        assertEquals('S', rank.symbol());

        rank = Rank.wild;
        assertEquals('W', rank.symbol());

        rank = Rank.wilddrawfour;
        assertEquals('F', rank.symbol());
        
        System.out.println("\n-- PASSED --");
    }

}