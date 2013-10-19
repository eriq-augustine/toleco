/*
 * JUnit test for Card class.
 *
 * @author Annabel Hung
 * @version 1.0 - April 15, 2010
 */

package cero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {

    private Card instance;

    public CardTest() {
        instance = new Card(Rank.two, Color.blue);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "B2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toLongString method, of class Card.
     */
    @Test
    public void testToLongString() {
        System.out.println("toLongString");
        String expResult = "blue two";
        String result = instance.toLongString();
        assertEquals(expResult, result);
    }

    /**
     * Test of rank method, of class Card.
     */
    @Test
    public void testRank() {
        System.out.println("rank");
        Rank expResult = Rank.two;
        Rank result = instance.rank();
        assertEquals(expResult, result);
    }

    /**
     * Test of color method, of class Card.
     */
    @Test
    public void testColor() {
        System.out.println("color");
        Color expResult = Color.blue;
        Color result = instance.color();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Card. -1 expected
     */
    @Test
    public void testCompareToGreater() {
        System.out.println("compareTo");
        Card aThat = new Card(Rank.one, Color.blue);
        int expResult = 1;
        int result = instance.compareTo(aThat);
        assertEquals(expResult, result);

        Card bThat = new Card(Rank.two, Color.red);
        result = instance.compareTo(bThat);
        assertEquals(expResult, result);
    }

	/**
	 * Test compareTo. 0 expected.
	 */
    @Test
    public void testCompareToEqual() {
        System.out.println("compareTo");
        Card aThat = new Card(Rank.two, Color.blue);
        int expResult = 0;
        int result = instance.compareTo(aThat);
        assertEquals(expResult, result);
    }

	/**
	 * Test compareTo. 1 expected.
	 */
    @Test
    public void testCompareToLesser() {
        System.out.println("compareTo");
        Card aThat = new Card(Rank.five, Color.blue);
        int expResult = -1;
        int result = instance.compareTo(aThat);
        assertEquals(expResult, result);

        Card bThat = new Card(Rank.two, Color.wild);
        result = instance.compareTo(bThat);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Card. True expected
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object aThat = new Card(Rank.two, Color.blue);
        boolean expResult = true;
        boolean result = instance.equals(aThat);
        assertEquals(expResult, result);
    }

	/**
	 * Test equals(). False expected.
	 */
    @Test
    public void testEqualsFalse() {
        System.out.println("equals");
        Object aThat = new Card(Rank.five, Color.yellow);
        boolean expResult = false;
        boolean result = instance.equals(aThat);
        assertEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Card.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "B2";
        Card expResult = new Card(Rank.two, Color.blue);
        Card result = Card.valueOf(name);
        assertEquals(expResult, result);
    }

}
