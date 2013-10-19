/*
 * JUnit test for the SwingOptionsMenu class.
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

/**
 *
 * @author Annabel
 */
public class SwingOptionsMenuTest {

    public SwingOptionsMenuTest() {
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
     * Test of closeOptionsMenu method, of class SwingOptionsMenu.
     */
    @Test
    public void testCloseOptionsMenu() {
        System.out.println("closeOptionsMenu");
        SwingOptionsMenu instance = new SwingOptionsMenu(null,
            new GameController());
        instance.closeOptionsMenu();
        // The options menu should appear then disappear.
    }

}