/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cero;

import java.awt.Window;
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
public class CeroAppTest {

    public CeroAppTest() {
    }

    @BeforeClass
    public static
    void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static
    void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of startup method, of class CeroApp.
     */
    @Test
    public
    void testStartup()
    {
        System.out.println("startup");
        CeroApp instance = new CeroApp();
        //instance.startup();
        CeroApp.main(null);

        try
        {
            Thread.sleep(2000);
        }
        catch(Exception ex)
        {
            ex.toString();
        }
        /**
         * Verify that the application actually starts.
         */
    }

    /**
     * Test of configureWindow method, of class CeroApp.
     */
    @Test
    public
    void testConfigureWindow()
    {
        System.out.println("configureWindow");
        Window root = null;
        CeroApp instance = new CeroApp();
        instance.configureWindow(root);
        /**
         * No need to test auto-generated code.
         */
    }

    /**
     * Test of getApplication method, of class CeroApp.
     */
    @Test
    public
    void testGetApplication()
    {
        System.out.println("getApplication");
        CeroApp result = CeroApp.getApplication();
        assertFalse(result.equals(null));
        
    }

    /**
     * Test of main method, of class CeroApp.
     */
    @Test
    public
    void testMain()
    {
        System.out.println("main");
        String[] args = null;
        CeroApp.main(args);
        /**
         * Verify that the application actually starts.
         */
    }
}