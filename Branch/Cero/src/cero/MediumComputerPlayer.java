package cero;

import java.awt.event.ActionEvent;
import java.util.*;
/**
 * MediumComputerPlayer represents an artificially intelligent computer
 * opponent in a Cero game.  MediumComputerPlayer uses a more advanced gaming
 * strategy and provides a bigger challenge to the human player than
 * EasyComputerPlayer.  On the other hand, it represents a simpler and
 * easier computer player than HardComputerPlayer. 
 * NOTE: This class was granted a checkstyle exception due to inner assignment use being beneficial.
 * @author Raymond Wong - Javadoc
 * @author Eric Gustafson - Pesudocode
 * @version 1.0 - March 4, 2010
 */
public class MediumComputerPlayer extends AbstractComputerPlayer
{

    /** {@inheritDoc} */
    public void doTurn()
    {
        ActionEvent event;
        I_Table table = getTable();
        I_Controller controller = getControl();

        // Call getComputerMove to return a card to play
        Card card = getComputerMove();
        // IF card is not null THEN
        if (card != null)
        {
            // select the card from the computer's hand by returning its index
            int index = table.getHand(controller.getCurrentPlayer()).indexOf(card);

            // create an action event with the card's index as its string
            event = new ActionEvent(this, 0,
                    String.valueOf(index), 0);
        }
        //ELSE
        else
        {
            //Create an action event for the pass action
            event = new ActionEvent(this, 0,
                    PlayerAction.pass.toString(), 0);
        }
        // END IF
        // tell the controller to perform this action
        controller.actionPerformed(event);
    }

    /** {@inheritDoc} */
    public Card getComputerMove()
    {
        I_Table table = getTable();
        I_Controller controller = getControl();

        ArrayList<Card> nextHand = table.getHand(controller.getPlayOrder().get(1));
        //NOTE: Specific card finding uses findCard() method below.
        //IF the next player has only one card left
        if (nextHand.size() == 1)
        {
            //IF this player has a playable Draw 2
            if (this.findCard(Rank.drawtwo, table.getColor()) != null)
            {
                //Return that card
                return this.findCard(Rank.drawtwo, table.getColor());

            }
            //ELSE IF the player has a playable skip
            else if (this.findCard(Rank.skip, table.getColor()) != null)
            {
                //Return that card
                return this.findCard(Rank.skip, table.getColor());

            }
            //ELSE IF the player has a playable reverse
            else if (this.findCard(Rank.reverse, table.getColor()) != null)

            {
                //RETURN that card
                return this.findCard(Rank.reverse, table.getColor());

            }
            //ELSE IF the player has a playable Wild
            else if (this.findCard(null, Color.wild) != null)
            {
                //RETURN that card
                return this.findCard(null, Color.wild);

            }
            //END IF
        }

        //CALL findPlayableCard to get the first playable card
        Card card = this.findPlayableCard();
        //IF the card is not null THEN
        if (card != null)
        {
            //RETURN that card
            return card;
        }
        //ELSE if we can draw more than once THEN
        else if (table.getDrawDeckSize() > 1)
        {
            //WHILE we do not have a playable card AND there are cards
            //in the draw deck
            while (findPlayableCard() == null && table.getDrawDeckSize() > 0)
            {
                // Fixes Defect 257
                //Draw a card from the deck by sending a draw action to the
                //Controller.
                ActionEvent event = new ActionEvent(this, 0,
                        PlayerAction.draw.toString(), 0);
                controller.actionPerformed(event);

                if(!getControl().getOptions().multipleDrawsAllowed())
                {
                    break;
                }

            }
            //END LOOP
            //CALL findPlayableCard and...
            //RETURN its output
            return findPlayableCard();

        }
        //ELSE
        else
        {
            // Fixes Defect 257
            //Draw a card from the deck by sending a draw action to the
            //Controller.
            ActionEvent event = new ActionEvent(this, 0,
                    PlayerAction.draw.toString(), 0);
            controller.actionPerformed(event);
            //CALL findPlayableCard and...
            //RETURN its output
            return findPlayableCard();

        }
        //END IF
    }

    /** Determine the color this player has the most of, and choose that. */
    public void showColorChooser()
    {
        //Create a java.util.Random in case we need a random color.
        Random random = new java.util.Random();
        //CALL findMostFrequentColor() to get the most frequent color
        Color color = findMostFrequentColor();
        //IF the color is null THEN
        if (color == null)
        {
            //Get a random color
            color = Color.pos(random.nextInt(4));

        }
        //Create an actionEvent with that color
        ActionEvent event = new ActionEvent(this, 0,
                "" + PlayerAction.color.toString() + color.toString(), 0);
        //CALL actionPerformed with the actionEvent to choose the color.
        this.getControl().actionPerformed(event);
    }

    /** Find a card in this player's hand based on the given rank and/or color
     *  @pre Both rank and color are not null at the same time
     * @param rank The rank being searched for, or null to not search by rank
     * @param color The color being searched for, or null to not search by color
     * @return the first card in the hand with the given rank and/or color, or
     * @return null if no matching card is found.
     */
    private Card findCard(Rank rank, Color color)
    {
        //Get the current player's hand
        ArrayList<Card> hand = getTable().getHand(getControl().getCurrentPlayer());
        //IF rank is null
        if (rank == null)
        {
            //FOR EACH card in this player's hand
            for (Card card : hand)
            {
                //IF the color is equal to the color being searched
                if (card.color() == color)
                {
                    //RETURN that card
                    return card;

                }
                //END IF
            }
            //END LOOP
        }
        //ELSE IF color is null
        else if (color == null)
        {
            //FOR EACH card in this player's hand
            for (Card card : hand)
            {
                //IF the rank is the one being searched for
                if (card.rank() == rank)
                {
                    //RETURN that card
                    return card;

                }
                //END IF
            }
            //END LOOP
        }
        //ELSE
        else
        {
            //FOR EACH card in this player's hand
            for (Card card : hand)
            {
                //IF the rank and color are the ones being searched for
                if ( card.color().equals(color) && card.rank().equals(rank))
                {
                    //RETURN that card
                    return card;
                }
                //END IF
            }
            //END LOOP
        }
        //END IF
        return null;
    }

    /** Analyze the player's hand to find the color that is most frequent.
      * @return The most frequent color or
      * @return null if there is no frequent color (the hand is all wilds or
      * empty.
      */
    private Color findMostFrequentColor()
    {


        //Get the current player's hand
        ArrayList<Card> hand = getTable().getHand(getControl().getCurrentPlayer());

        //Create a list of four numbers, representing the four "colors"
        //in enum order
        int[] frequency = {0,0,0,0};

        //FOR EACH card in the player's hand
        for (Card card : hand)
        {
            //IF the color is not wild
            if (card.color() != Color.wild)
            {
                //Get the card's color, and increment the appropriate
                //value in the list.
                frequency[card.color().ordinal()] += 1;
            }
        }
        //END LOOP

        int mostFreqIndex = 0;
        int mostFreqCount = 0;
        //FOR EACH element in the list
        for (int index = 0; index < frequency.length; index++)
        {
            //IF the current list element's value is greater than the 
            //current frequent color's
            if (frequency[index] > mostFreqCount)
            {
                //Make that list element's color the most frequent color
                mostFreqIndex = index;
                //Make the most frequent color's count to be this color's count.
                mostFreqCount = frequency[index];
            }
            //END IF
        }
        //END LOOP
        //RETURN the enum value associated with the most frequent color's number
        return Color.pos(mostFreqIndex);
    }
}
