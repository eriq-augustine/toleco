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
public class AboutBoxTest {

    public AboutBoxTest() {
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
     * Test of showDevelopers method, of class AboutBox.
     */
    @Test
    public void testShowDevelopers() {
        System.out.println("showDevelopers");
        AboutBox instance = new AboutBox();
        assertEquals(instance.getDevelopers(), "Developers:\n\nRobert Bernal\n" +
                "Annabel Hung\nRaymond Wong\nMatt Carson\nDirk Cummings\n" +
                "Eric Gustafson\nIlya Seletsky\n");
    }

    /**
     * Test of showDevelopers method, of class AboutBox.
     */
    @Test
    public void testDefect271() {
        System.out.println("showDevelopers");
        AboutBox instance = new AboutBox();
        assertEquals(instance.getDevelopers(), "Developers:\n\nRobert Bernal\n" +
                "Annabel Hung\nRaymond Wong\nMatt Carson\nDirk Cummings\n" +
                "Eric Gustafson\nIlya Seletsky\n");
    }

    /**
     * Test of showVersion method, of class AboutBox.
     */
    @Test
    public void testShowVersion() {
        System.out.println("showVersion");
        AboutBox instance = new AboutBox();
        assertEquals(instance.getVersion(), "Version 1.0\n");
  
    }

    /**
     * Test of showBuild method, of class AboutBox.
     */
    @Test
    public void testShowBuild() {
        System.out.println("showBuild");
        AboutBox instance = new AboutBox();
        assertEquals(instance.getBuild(), "Build 1.0\n");
    }

}