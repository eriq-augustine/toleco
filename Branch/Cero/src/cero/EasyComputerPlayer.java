package cero;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * The EasyComputerPlayer class extends the ComputerPlayer abstract class
 * and models an autonomous human player in the card game Cero.
 * An EasyComputerPlayer performs its turns in the card game in a way designed
 * to make the game play the simplest and easiest of the three types of
 * ComputerPlayers.
 *
 * @author Ilya Seletsky - Javadoc
 * @author Dirk Cummings - Javadoc
 * @author Eric Gustafson - Pseudocode / Unit Code
 * @version 1.0 - March 3, 2010
 */
public class EasyComputerPlayer extends AbstractComputerPlayer
{

    /** {@inheritDoc} */
    public void doTurn()
    {
        // Create a temporary action event ae
        ActionEvent ae;
        // CALL getComputerMove to return a card to play
        Card playable = getComputerMove();

        // IF card is playable (not null) THEN
        if(playable != null)
        {
            // GET the table
            I_Table table = getTable();
            // GET the card's index from the computer's hand
            ArrayList<Card> hand = table.getHand(this.getControl().getCurrentPlayer());

            Integer index = new Integer(hand.indexOf(playable));

            // SET ae to a new action event with the card index
            ae = new ActionEvent(this, 0, index.toString());
        }
        //ELSE
        else
        {
            // SET ae to a new action event "pass"
            ae = new ActionEvent(this, 0, "pass");
        }
        // END IF

        // GET the controller
        I_Controller controller = getControl();
        // CALL the controller to perform action with action event ae
        controller.actionPerformed(ae);
    }

    /**
     * Find the first playable card, and play it.
     * @return the chosen card, or null if there is no move.
     */
    public Card getComputerMove()
    {
        // GET the controller
        I_Controller controller = this.getControl();
        // CALL findPlayableCard to get the first playable card
        Card card = this.findPlayableCard();

        // IF the card is playable (not null) THEN
        if(card != null)
        {
            // RETURN playable card
            return card;
        }
        // ELSE IF multiple draws are allowed in the options THEN
        else if(controller.getOptions().multipleDrawsAllowed())
        {
            // WHILE we do not have a playable card (card is null) AND
            // the draw deck is not empty.  See defect 257.
            while(card == null && getTable().getDrawDeckSize() > 0)
            {
                // Fixes Defect 257
                // CALL controller to perform action "draw"
                controller.actionPerformed(new ActionEvent(this, 0, "draw"));

                // CALL findPlayableCard
                card = findPlayableCard();

                if(!getControl().getOptions().multipleDrawsAllowed())
                {
                    break;
                }
            }
            // END WHILE

            // RETURN the playable card
            return card;
        }
        // ELSE
        else
        {
            // Fixes Defect 257
            // CALL controller to perform action "draw"
            controller.actionPerformed(new ActionEvent(this, 0, "draw"));
            // CALL findPlayableCard, and
            // RETURN findPlayableCard's result
            return findPlayableCard();
        }
        // END IF
    }

    /**
     * Pick a random new suit when the computer player
     * plays a Wild or Wild Draw Four
     */
    public void showColorChooser()
    {
        // CREATE a new java.util.Random object for random numbers
        java.util.Random generator = new java.util.Random();
        // GET a random number between 0 and 3.
        int num = generator.nextInt(4);
        // GET the corresponding color with generated random number
        Color color = Color.pos(num);
        // CREATE a new action event to choose a new color
        // from the string "color" appended with the color name
        String colorStr = "color" + color.toString();
        // GET the controller
        // CALL our controller to perform action with the color action event
        this.getControl().actionPerformed(new ActionEvent(this, 0, colorStr));
    }
}
