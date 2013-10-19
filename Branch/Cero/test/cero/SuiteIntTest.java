/**
 * Suite of all integration tests.
 * @author Annabel Hung
 */

package cero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        cero.AbstractComputerPlayerIntTest.class,
        cero.CeroAppTest.class,
        cero.ConsoleAppIntTest.class,
        cero.ConsolePlayerIntegrationTest.class,
        cero.DeckIntTest.class,
        cero.EasyComputerPlayerIntTest.class,
        cero.GameControllerIntegTest.class,
        cero.GameOptionsTest.class,
        cero.HardComputerPlayerIntTest.class,
        cero.HowToPlayTest.class,
        cero.MediumComputerPlayerIntegTest.class,
        cero.SwingPlayerIntTest.class,
        cero.TableIntTest.class
})

    public class SuiteIntTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}