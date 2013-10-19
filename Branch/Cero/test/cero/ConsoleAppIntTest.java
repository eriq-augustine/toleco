package cero;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
/**
 * Unit test to run application in console mode.
 *
 * @author Eric Gustafson
 * @date 4/15/2010
 */
public class ConsoleAppIntTest {

    public ConsoleAppIntTest() {
    }

    ConsoleApp app;
    
    @Before
    /** Create a ConsoleApp before testing it */
    public void setUp()
    {
        System.setIn(new ByteArrayInputStream(
            "y\n0\n0\n0\n\n\n\n\nq\n".getBytes()));
        app = new ConsoleApp();
    }

    @Test
    /**
     * Test of startup method, of class ConsoleApp.
     */
    public void testStartup() {
        System.out.println("Starting test of the ConsoleApp class...");
	System.out.println("This is a manual test.  Please verify that the" + 
	    "ConsolePlayer is initialized");
        app.startup("");
    }

}
