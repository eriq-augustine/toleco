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
public class HowToPlayTest {

    public HowToPlayTest() {
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
     * Test of getGameRules method, of class HowToPlay.
     */
    @Test
    public void testGetGameRules() {
        System.out.println("getGameRules");
        HowToPlay instance = new HowToPlay();
        String testString = "Setting up the Game:\n\n  At the start of the game, " +
                "the system shuffles the deck and places seven cards, from " +
                "the deck, in each player's hand.\n  The remaining cards in " +
                "the deck are placed face down on the table to be used as " +
                "the draw pile.\n  The top card from the draw pile is placed " +
                "face up on the table to begin a discard pile.\n\n" +
                "Playing the Game:\n\n  The human player takes the first " +
                "turn.\n  The system will indicate which cards in the " +
                "player's hand can be legally discarded during each turn.\n" +
                "  During a turn, the player may only discard at most one" +
                " card from his or her hand.\n  " +
                "During a turn, if the player has a playable card, he or " +
                "she may choose whether or not to discard the card.\n  If " +
                "the player has a playable card and chooses not to play it, " +
                "he or she must draw cards from the draw pile until a " +
                "playable card is drawn.\n  If the player does not have a" +
                " playable card, he or she must draw cards from the draw" +
                " pile until a playable card is drawn.\n  When the player " +
                "draws a playable card from the draw pile, he or she may" +
                " discard that card or continue to draw, one card at a time" +
                ", from the draw pile.\n  If the draw pile is exhausted, the " +
                "top card of the discard pile begins a new discard pile, and " +
                "the remainder of the old discard pile is shuffled and used " +
                "as the new draw pile.\n  If the player discards a Wild Card" +
                " or Wild Draw Four Card, the player must declare a color for" +
                " subsequent players to match.\n  If the player discards a" +
                " Wild Draw Four Card, the next player cannot discard any" +
                " card and must draw four cards from the draw pile.\n  If the " +
                "player discards a Draw Two Card, the next player cannot " +
                "discard any card and must draw two cards from the draw pile." +
                "\n  If the player discards a Reverse Card, the system will" +
                " reverse the direction of play.\n  If the player discards a " +
                "Skip Card, the system will skip the next player's turn.\n  " +
                "If the player draws cards from the draw pile as a result of" +
                " a Draw Two Card of Wild Draw Four Card, his or her turn" +
                " ends after drawing.\n  If the player discards a playable" +
                " card, his or her turn ends after discarding.\n\n  " +
                "Ending the Game:\n\n  The first player to discard all of" +
                " the cards in his or her hand is the winner of the game.\n";

        assertEquals(instance.getGameRules(), testString);
        System.out.println(testString);

    }

}