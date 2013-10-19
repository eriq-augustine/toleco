/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cero;

import java.util.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * The HardComputerPlayer is the hardest computer player algorithm available
 * for Cero.  It is knowledgable about the number of cards in other players'
 * hands, and can make decisions based on this about playing special cards
 * (eg. skip, draw 2, etc).  It should be, by a qualitative assessment, more 
 * challenging than the NormalComputerPlayer.
 * NOTE: This class was granted a checkstyle exception due to length, complexity
 * and inner assignment use being beneficial.
 * @author Eric Gustafson
 * 
 */
public class HardComputerPlayer extends AbstractComputerPlayer
{
/** {@inheritDoc} */
    public void doTurn()
    {
        Card card;
        int cardNum;
        ActionEvent ae;
        // Call getComputerMove to return a card to play
        card = getComputerMove();
        ActionEvent event = null;
        // IF card is not null THEN
        if(card != null)
        {
            // select the card from the computer's hand, returning its index
            // number
            cardNum = getTable().getHand(getControl().getCurrentPlayer()).indexOf(card);
            // create an action event with the card's index as its string
            event = new ActionEvent(this, 0, cardNum + "");
        }
        //ELSE
        else
        {
            //Create an action event for the pass action
            event = new ActionEvent(this, 0, PlayerAction.pass.toString());
        }
        // END IF
        // tell the controller to perform this action
        getControl().actionPerformed(event);
    }
    
    /** {@inheritDoc} */
    public Card getComputerMove()
    {
        //NOTE: All searches use the findCard() method below.
        // ## One card left scenario ##
        //IF the next player has only one card left
        if(getTable().getHand(Players.next(getControl().getCurrentPlayer())).size() == 1)
        {
            //IF this player has a playable Draw 2
            if(findCard(Rank.drawtwo, null) != null)
            {
                //Return that card
                return findCard(Rank.drawtwo, null);
            }
            //ELSE IF the player has a playable skip
            else if(findCard(Rank.skip, null) != null)
            {
                //Return that card
                return findCard(Rank.skip, null);
            }
            //ELSE IF the player has a playable reverse
            else if(findCard(Rank.reverse, null) != null)
            {
                //RETURN that card
                return findCard(Rank.reverse, null);
            }
            //ELSE IF the player has a playable Wild
            else if(findCard(Rank.wild, null) != null)
            {
                //RETURN that card
                return findCard(Rank.wild, null);
            }
            //END IF
        }
        //## Try to keep the current color first ##
        //Search for a card of the current color
        //ELSE IF the card is not null
        else if(findCard(null, getTable().getDiscardCard().color()) != null)
        {
            //RETURN that card
            return findCard(null, getTable().getDiscardCard().color());
        }
        //## Then try to switch to the most frequent color without a wild
        //Search for a card of the current rank and most frequent color
        //ELSE IF that card is not null
        else if(findCard(getTable().getDiscardCard().rank(), findMostFrequentColor()) != null)
        {
            //RETURN that card
            return findCard(getTable().getDiscardCard().rank(), findMostFrequentColor());
        }
        //Search for a card of the current rank
        //ELSE IF that card is not null
        else if(findCard(getTable().getDiscardCard().rank(), null) != null)
        {
            //RETURN that card
            return findCard(getTable().getDiscardCard().rank(), null);
        }
        //Search for a Wild Draw Four
        //ELSE IF that card is not null
        else if(findCard(Rank.wilddrawfour, null) != null)
        {
            //RETURN that card
            return findCard(Rank.wilddrawfour, null);
        }
        //Search for a regular Wild
        //ELSE IF that card is not null
        else if(findCard(Rank.wild, null) != null)
        {
            //RETURN that card
            return findCard(Rank.wild, null);
        }
        //ELSE IF there is another playable card in our hand
        else if(findPlayableCard() != null)
        {
            //RETURN that card
            return findPlayableCard();
        }

        //## No playable cards present.  Draw.  ##
        //ELSE IF we can draw multiple times
        else if(getTable().getDrawDeckSize() > 1)
        {
            //WHILE we still don't have a playable card
            //AND the draw deck still has cards in it
            while(findPlayableCard() == null && getTable().getDrawDeckSize() >= 1)
            {
                // Fixes defect 257
                //Draw a card by sending a "draw" action to the controller.
                getControl().actionPerformed(new ActionEvent(this, 0, PlayerAction.draw.toString()));

                if(!getControl().getOptions().multipleDrawsAllowed())
                {
                    break;
                }
            }
            //END LOOP
            //RETURN the new card (findPlayableCard)
            return findPlayableCard();
        }
        //ELSE
        else
        {
            // Fixes defect 257
            //Draw one card by passing the controller a "draw" action.
            getControl().actionPerformed(new ActionEvent(this, 0, PlayerAction.draw.toString()));
            //RETURN the output of findPlayableCard
            return findPlayableCard();
        }
        //END IF
        return null;
    }

    /** Determine the color this player has the most of, and choose that. */
    public void showColorChooser()
    {
        //Create a new java.util.Random to use with random numbers.
        java.util.Random rand = new Random();
        //CALL findMostFrequentColor() to get the most frequent color
        Color freq = findMostFrequentColor();
        //IF the most frequent color is null
        if(freq == null)
        {
            //Pick a random one, we don't care.
            freq = Color.pos(Math.abs(rand.nextInt()) % 4);
        }
        //Create an actionEvent with that color
        ActionEvent event = new ActionEvent(this, 0, "color"+freq.toString());
        //CALL actionPerformed with the actionEvent to choose the color.
        getControl().actionPerformed(event);
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
        if(rank == null)
        {
            //FOR EACH card in this player's hand
            for(Card card : hand)
            {
                //IF the color is equal to the color being searched
                if(card.color().equals(color))
                {
                    //RETURN that card
                    return card;
                }
                //END IF
            }
            //END LOOP
        }
        //ELSE IF color is null
        else if(color == null)
        {
            //FOR EACH card in this player's hand
            for(Card card : hand)
            {
                //IF the rank is the one being searched for
                if(card.rank().equals(rank))
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
            for(Card card : hand)
            {
                //IF the rank and color are the ones being searched for
                if(card.rank().equals(rank) && card.color().equals(color))
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
      * @return null if there is no frequent color (all cards are wilds or hand
      * is empty.
      */
    private Color findMostFrequentColor()
    {


        //Get the current player's hand
        ArrayList<Card> hand = getTable().getHand(getControl().getCurrentPlayer());

        //Create a list of four numbers, representing the four coloors
        //in enum order
        int[] numberOfElements = new int[Color.values().length];

        //FOR EACH card in the player's hand
        for(Card card : hand)
        {
            //IF the card's color is not wild
            if(!card.color().equals(Color.wild))
            {
                //Get the color's enum position, and increment the appropriate
                //value in the list.
                numberOfElements[card.color().ordinal()]++;
            }
        }
        //END LOOP
        Color freq = null;
        int frequency = 0;
        //FOR EACH element in the list
        for(int index = 0; index < numberOfElements.length; index++)
        {
            //IF the current list element's value is greater than the
            //current frequent color's
            if(numberOfElements[index] > frequency)
            {
                //Make that list element's color the most frequent color
                freq = Color.pos(index);
                //Make the most frequent color's count this color's count.
                frequency = numberOfElements[index];
            }
            //END IF
        }
        //END LOOP
        //RETURN the enum value associated with the most frequent color's number
        return freq;
    }
}

