/**
 * Suite of all unit tests.
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
        cero.AboutBoxTest.class,
        cero.AbstractComputerPlayerTest.class,
        cero.CardTest.class,
        cero.ColorTest.class,
        cero.ComputerLevelTest.class,
        cero.ConsoleAppTest.class,
        cero.ConsolePlayerTest.class,
        cero.DeckTest.class,
        cero.EasyComputerPlayerTest.class,
        cero.GameControllerTest.class,
        cero.GameOptionsTest.class,
        cero.HardComputerPlayerTest.class,
        cero.HowToPlayTest.class,
        cero.MediumComputerPlayerTest.class,
        cero.PlayerActionTest.class,
        cero.PlayersTest.class,
        cero.RankTest.class,
        cero.SwingPlayerTest.class,
                // tests (Swing) aboutbox, howtoplay, optionsmenu
        cero.TableTest.class
})

    public class SuiteUnitTest {

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