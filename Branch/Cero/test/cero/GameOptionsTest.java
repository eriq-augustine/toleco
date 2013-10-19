/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Robert Bernal
 */
public class GameOptionsTest {

    public GameOptionsTest() {
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
     * Test of getName method, of class GameOptions.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        GameOptions instance = new GameOptions();
        
        assertEquals(instance.getName(Players.kHuman), "Player");
        assertEquals(instance.getName(Players.kComputer1), "Annabel Raymond");
        assertEquals(instance.getName(Players.kComputer2), "Eric Robert Ilya");
        assertEquals(instance.getName(Players.kComputer3), "Matt Dirk");
    }

    /**
     * Test of getComputerLevel method, of class GameOptions.
     */
    @Test
    public void testGetComputerLevel() {
        System.out.println("getComputerLevel");
        GameOptions instance = new GameOptions();

        assertEquals(instance.getComputerLevel(Players.kComputer1) , ComputerLevel.medium);
        assertEquals(instance.getComputerLevel(Players.kComputer2) , ComputerLevel.medium);
        assertEquals(instance.getComputerLevel(Players.kComputer3) , ComputerLevel.medium);
    }
    
    /**
     * Test of setName method, of class GameOptions.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        GameOptions instance = new GameOptions();
        instance.setName(Players.kHuman, "Robert");
        instance.setName(Players.kComputer1, "Ilya");
        instance.setName(Players.kComputer2, "Eric");
        instance.setName(Players.kComputer3, "Annabel");

        assertEquals(instance.getName(Players.kHuman), "Robert");
        assertEquals(instance.getName(Players.kComputer1), "Ilya");
        assertEquals(instance.getName(Players.kComputer2), "Eric");
        assertEquals(instance.getName(Players.kComputer3), "Annabel");
    }

    /**
     * Test of setComputerLevel method, of class GameOptions.
     */
    @Test
    public void testSetComputerLevel() {
        System.out.println("setComputerLevel");

        GameOptions instance = new GameOptions();
        instance.setComputerLevel(Players.kComputer1, ComputerLevel.easy);
        instance.setComputerLevel(Players.kComputer2, ComputerLevel.hard);
        instance.setComputerLevel(Players.kComputer3, ComputerLevel.hard);

        assertEquals(instance.getComputerLevel(Players.kComputer1), ComputerLevel.easy);
        assertEquals(instance.getComputerLevel(Players.kComputer2), ComputerLevel.hard);
        assertEquals(instance.getComputerLevel(Players.kComputer3), ComputerLevel.hard);

    }

    /**
     * Test of multipleDrawsAllowed method, of class GameOptions.
     */
    @Test
    public void testMultipleDrawsAllowed() {
        System.out.println("multipleDrawsAllowed");
        GameOptions instance = new GameOptions();

        assertEquals(instance.multipleDrawsAllowed(), true);
    }

    /**
     * Test of setMultipleDraws method, of class GameOptions.
     */
    @Test
    public void testSetMultipleDraws() {
        System.out.println("setMultipleDraws");

        GameOptions instance = new GameOptions();

        instance.setMultipleDraws(true);
        assertEquals(instance.multipleDrawsAllowed(), true);
        instance.setMultipleDraws(false);
        assertEquals(instance.multipleDrawsAllowed(), false);
    }
}