/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toleco.logic;

import toleco.unit.Unit;
import toleco.controller.Player;
import toleco.terrain.DefaultTerrain;
import toleco.terrain.Terrain;
import toleco.unit.DefaultUnit;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import toleco.unit.ArmorType;
import toleco.unit.AttackType;
import static org.junit.Assert.*;

/**
 *
 * @author eirq
 */
public class GameBoardTest_1 {

    private GameBoard testBoard;

    public GameBoardTest_1() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        testBoard = new GameBoard();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test Constructor and getters.
     */
    @Test
    public void testConstructor()
    {
        System.out.println("Constructor");

        assertEquals("", testBoard.getBackStory());
        assertEquals(10, testBoard.getMap().length);
        assertEquals(10, (testBoard.getMap())[0].length);
        assertNull(testBoard.getSelection());
    }

    /**
     * Test of loadMap method, of class GameBoard.
     */
    @Test
    public void testLoadMap() {
        System.out.println("loadMap");
        try
        {
            testBoard.loadMap("test/toleco/logic/testMap.ocem");
        }
        catch (Exception e)
        {
        }
        assertEquals("Once upon a time there was a backstory!", testBoard.getBackStory());
        assertEquals("Default", testBoard.getMap()[0][0].getType());
        assertEquals("Default", testBoard.getMap()[0][0].getUnit().getType());
        assertNull(testBoard.getMap()[1][1].getUnit());
    }


    /**
     * Test of setBackStory method, of class GameBoard.
     */
    @Test
    public void testSetBackStory() {
        System.out.println("setBackStory");
        testBoard.setBackStory("Lets do this!");
        assertEquals("Lets do this!", testBoard.getBackStory());
    }

    /**
     * Test of selectTerrain method, of class GameBoard.
     */
    @Test
    public void testSelectTerrain() {
        System.out.println("selectTerrain");

        try
        {
            testBoard.loadMap("test/toleco/logic/testMap.ocem");
        }
        catch (Exception e)
        {
        }
        testBoard.selectTerrain(0, 0);

        assertEquals("Default", testBoard.getSelection().getType());

        testBoard.selectTerrain(-1, -1);

        assertNull(testBoard.getSelection());
    }

    /**
     * Test of setTerrain method, of class GameBoard.
     */
    @Test
    public void testSetTerrain() {
        System.out.println("setTerrain");

        try
        {
            testBoard.loadMap("test/toleco/logic/testMap.ocem");
        }
        catch (Exception e)
        {
        }
        testBoard.selectTerrain(0, 0);
        testBoard.setTerrain("Default", null, Player.kPlayer1);

        assertEquals("Default", testBoard.getSelection().getType());
        assertEquals("Default", testBoard.getMap()[0][0].getType());
        assertNull(testBoard.getMap()[0][0].getUnit());
        
        testBoard.setTerrain("Default", "Default", Player.kPlayer1);
        assertEquals("Default", testBoard.getSelection().getType());
        assertEquals("Default", testBoard.getSelection().getUnit().getType());
    }

    /**
     * Tests for defect #170 not calling setChanged before any call to notifyObservers.
     */
    @Test
    public void testDefect170()
    {
        System.out.println("Defect #170");

        Watcher watch = new Watcher();
        testBoard.addObserver(watch);

        try
        {
            testBoard.loadMap("test/toleco/logic/testAttackMap.ocem");
            fail();
        }
        catch (FunException ex)
        {
            assertEquals("player", ex.getMessage());
        }
        catch (Exception e)
        {
            fail();
        }

        try
        {
            testBoard.selectTerrain(0, 0);
        }
        catch (FunException ex)
        {
            assertEquals("other", ex.getMessage());
        }

        try
        {
            testBoard.getEnemiesInRange();
            fail();
        }
        catch (FunException ex)
        {
            assertEquals("getEnemiesInRange", ex.getMessage());
        }

        try
        {
            testBoard.resetUnits(Player.kPlayer1);
            fail();
        }
        catch (FunException ex)
        {
            assertEquals("player", ex.getMessage());
        }
    }

    private class Watcher implements Observer
    {
        public void update(Observable obs, Object obj)
        {
            if (obj instanceof ArrayList)
            {
                throw new FunException("getEnemiesInRange");
            }
            else if (obj instanceof Player)
            {
                throw new FunException("player");
            }
            else
            {
                throw new FunException("other");
            }
        }
    }

    private class FunException extends RuntimeException
    {
        public FunException(String message)
        {
            super(message);
        }
    }

    /**
     * Helps testAttack by calculating the damage that should be done.
     */
    private int calculateDamage(Unit attacker, Unit defender, int terrMod)
    {
        //Get the numeric modifier for the attackers attack type against the
        // defenders armor type.
        double mod = attacker.getAttackType().modAgainstType(defender.getArmorType());

        //Damage Equation (Code-named: Jon's Equation)
        //AttackVDefenseModifier*(1-((TotalHealth-CurrentHealth)/TotalHealth)/2)*
        // (AttackValue - DefenseValue)

        //Obtain the amount of damage by performing the damage equation.
        int damage = (int)(mod * (1.0 - (((double)attacker.getMaxHealth() -
            attacker.getCurrentHealth()) / (double)attacker.getMaxHealth()) / 2.0) *
            ((double)attacker.getAttackValue() -
            ((double)defender.getArmorValue() +
            (double)(terrMod))));

        //IF damage to be dealt is less than 1.
        if (damage < 1)
        {
            //Set the damage to be done to 1.
            damage = 1;
        }
        //ELSE IF damage to be dealt is greater than the defneding Unit's health.
        else if (damage > defender.getCurrentHealth())
        {
            //Reduce the damage to the defensing units health.
            damage = defender.getCurrentHealth();
        }

        return damage;
    }

    /**
     * Helps testAttack by calculating the counter-attack damage that should be done.
     */
    private int calculateCounterDamage(Unit attacker, Unit defender, int terrMod, int counterTerrMod)
    {
        //FIRST! Calculate the damage that is to be done to the defender.
        int initialDamage = calculateDamage(attacker, defender, terrMod);
        int defendersHealth = defender.getCurrentHealth() - initialDamage;

        if (defendersHealth == 0)
        {
            return 0;
        }
        
        //Get the numeric modifier for the defenders attack type against the
        // attackers armor type.
        double mod = defender.getAttackType().modAgainstType(attacker.getArmorType());

        //Obtain the amount of damage by performing the damage equation.
        int damage = (int)(mod * (1.0 - (((double)defender.getMaxHealth() -
            (double)defendersHealth) / defender.getMaxHealth()) / 2.0)
            * ((double)defender.getAttackValue() - ((double)attacker.getArmorValue() +
            (double)counterTerrMod)));

        //IF damage to be dealt is less than 1.
        if (damage < 1)
        {
            //Set the damage to be done to 1.
            damage = 1;
        }
        //ELSE IF damage to be dealt is greater than the defneding Unit's health.
        else if (damage > attacker.getCurrentHealth())
        {
            //Reduce the damage to the defensing units health.
            damage = attacker.getCurrentHealth();
        }
        //ENDIF

        return damage;
    }

}