/*
 * JUnit test for the ComputerLevel class.
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

public class ComputerLevelTest {
    
    public ComputerLevelTest() {
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
     * Test of values method, of class ComputerLevel.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        ComputerLevel[] expResult = {ComputerLevel.easy, ComputerLevel.medium, ComputerLevel.hard};
        ComputerLevel[] result = ComputerLevel.values();
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        assertEquals(expResult[2], result[2]);
    }

    /**
     * Test of valueOf method, of class ComputerLevel.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "medium";
        ComputerLevel expResult = ComputerLevel.medium;
        ComputerLevel result = ComputerLevel.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test valueOf for possible IllegalArgumentException.
     */
    @Test
    public void testValueOfIllegalArgument() {
        try {
            System.out.println("valueOf - bad name");
            String name = "badlevel";
            ComputerLevel result = ComputerLevel.valueOf(name);
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
            ComputerLevel result = ComputerLevel.valueOf(name);
            fail("Should raise a NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test of getLevel method, of class ComputerLevel.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        // test easy
        int index = 0;
        ComputerLevel expResult = ComputerLevel.easy;
        ComputerLevel result = ComputerLevel.getLevel(index);
        assertEquals(expResult, result);
        // test medium
        index = 1;
        expResult = ComputerLevel.medium;
        result = ComputerLevel.getLevel(index);
        assertEquals(expResult, result);
        // test hard
        index = 2;
        expResult = ComputerLevel.hard;
        result = ComputerLevel.getLevel(index);
        assertEquals(expResult, result);
    }

    /**
     * Test getLevel for possible ArrayIndexOutOfBoundsException.
     */
    @Test
    public void testGetLevelArrayIndexOutOfBounds() {
        try {
            System.out.println("getLevel - out of bounds");
            int index = 100;
            ComputerLevel result = ComputerLevel.getLevel(index);
            fail("Should raise an ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}
