/*
 * JUnit test for the Color class.
 *
 * @author Annabel Hung
 * @version 1.0 - April 12, 2010
 */

package cero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColorTest {
    
    public ColorTest() {
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
     * Test of values method, of class Color.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Color[] expResult = {Color.red, Color.blue, Color.green, Color.yellow, Color.wild};
        Color[] result = Color.values();
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        assertEquals(expResult[2], result[2]);
        assertEquals(expResult[3], result[3]);
        assertEquals(expResult[4], result[4]);
    }

    /**
     * Test of valueOf method, of class Color.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        // test red
        String name = "red";
        Color expResult = Color.red;
        Color result = Color.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test valueOf for possible IllegalArgumentException.
     */
    @Test
    public void testValueOfIllegalArgument() {
        try {
            System.out.println("valueOf - bad name");
            String name = "badcolor";
            Color result = Color.valueOf(name);
            fail("Should raise an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test valueOf for possible NullPointerException.
     */
    @Test
    public void testValueOfNullPointer() {
        try {
            System.out.println("valueOf - null name");
            String name = null;
            Color result = Color.valueOf(name);
            fail("Should raise a NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test of pos method, of class Color.
     */
    @Test
    public void testPos() {
        System.out.println("pos");
        // test red
        int index = 0;
        Color expResult = Color.red;
        Color result = Color.pos(index);
        assertEquals(expResult, result);
        // test blue
        index = 1;
        expResult = Color.blue;
        result = Color.pos(index);
        assertEquals(expResult, result);
        // test green
        index = 2;
        expResult = Color.green;
        result = Color.pos(index);
        assertEquals(expResult, result);
        // test yellow
        index = 3;
        expResult = Color.yellow;
        result = Color.pos(index);
        assertEquals(expResult, result);
        // test wild
        index = 4;
        expResult = Color.wild;
        result = Color.pos(index);
        assertEquals(expResult, result);
    }

    /**
     * Test pos for possible ArrayIndexOutOfBoundsException.
     */
    @Test
    public void testPosArrayIndexOutOfBounds() {
        try {
            System.out.println("pos - out of bounds");
            int index = 100;
            Color result = Color.pos(index);
            fail("Should raise an ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

}
