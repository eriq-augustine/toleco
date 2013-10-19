package cero;
import java.util.*;
import java.util.ArrayList;

/**
 * The Table class represents the game area in which players may participate in
 * a Cero card game.  The Table has a deck of cards, four players, the
 * currently dealt card and the most recently discarded card.  It also
 * keeps track of each player's hand, the currently chosen color,
 * and the direction of gameplay.  Table is also Observable, which means
 * an Observer will be notified of any changes to Table.
 * 
 * @author Raymond Wong - javadoc
 * @author Dirk Cummings - pseudocode
 * @author Jon Moorman - maintainer
 * @version 1.0 - February 28, 2010
 */
public class Table extends Observable implements I_Table
{
    /** The Draw Deck used during play. */
    private Deck drawDeck;

    /** The Discard Deck used during play. */
    private Deck discardDeck;

    /** The currently dealt card. */
    private Card dealtCard;

    /** The current discarded card. */
    private Card discard;

    /** The current chosen color. */
    private Color chosenColor;

    /**
     * Flag to indicate direction of game play.
     * True indicates clockwise direction; false indicates 
     * counter-clockwise direction.
     */
    private boolean isClockwise;

    /** A map of players and their hands.  */
    private HashMap<Players, ArrayList<Card>> hands;

    /**
     * Construct a table by resetting the deck and making an empty map.
     * @post isClockwise is true.
     * @post hands is not null.
     */
    public Table()
    {
        // CALL reset deck
        resetDeck();

        // Initialize the hands map
        hands = new HashMap<Players, ArrayList<Card>>();

    }

    /** {@inheritDoc} */
    public Color getColor()
    {
        // Return the current playable color
        return chosenColor;
    }

    /** {@inheritDoc} */
    public Card getDealtCard()
    {
        // Return the current dealt card
        return dealtCard;
    }

    /** {@inheritDoc} */
    public boolean getDirection()
    {
        // Return the current direction of play
        return isClockwise;
    }

    /** {@inheritDoc} */
    public Card getDiscardCard()
    {
        // Return the current discarded card
        return discard;
    }

    /** {@inheritDoc} */
    public int getDiscardDeckSize()
    {
        // Return the current size of the discard deck
        return discardDeck.size();
    }

    /** {@inheritDoc} */
    public int getDrawDeckSize()
    {
        // Return the current size of the draw deck
        return drawDeck.size();
    }

    /** {@inheritDoc} */
    public ArrayList<Card> getHand(Players player)
    {
        // Return the hand of player
        return hands.get(player);
    }

    /** {@inheritDoc} */
    public boolean hasWinner()
    {
        // Return true if any player's hand is empty
        for (Players current: Players.values())
        {
            ArrayList<Card> hand = getHand(current);
            /* If hand is empty - Jon */
            if(hand.isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean isPlayableCard(Card card)
    {
        // Return true if the card is a wild, the card matches the chosenColor,
        // or if the card's rank matches the current discard rank

        /*if the color is wild -Jon */
        if(card.color().equals(Color.wild))
        {
            return true;
        }
        /*else if the card is the chosen color -Jon */
        else if(card.color().equals(chosenColor))
        {
            return true;
        }
        /*else if the cards rank is the discard card's rank -Jon */
        else if(card.rank().equals(discard.rank()))
        {
            return true;
        }
        return false;

    }

    /** {@inheritDoc} */
    public void performColorChosenAction(String colorChoice)
    {
        // Make this color the current chosen color
        chosenColor = Color.valueOf(colorChoice);

        // Indicate this observable has changed
        this.setChanged();

        // Notify observers
        notifyObservers();

    }

    /** {@inheritDoc} */
    public void performDiscardAction(Players player, Card card)
    {
        // Make this card the current discard card
        discard = card;
        // Add this card to the discard deck
        discardDeck.addCard(card);
        // Set current chosen color to card reference's color
        chosenColor = card.color();
        // Remove this card from player's hand
        hands.get(player).remove(card);
        // IF the draw deck is empty and the discard deck is greater than 1 THEN
        if(drawDeck.size() == 0 && discardDeck.size() > 1)
        {
            reshuffleDrawDeck();
        }
        
        // Indicate this observable has changed
        this.setChanged();
        // Notify observers
        notifyObservers();
    }

    /** {@inheritDoc} */
    public void performReverseAction()
    {
        // Negate the current direction
        isClockwise = !isClockwise;

        // Indicate this observable has changed
        this.setChanged();
        // Notify observers
        notifyObservers();
    }

    /** {@inheritDoc} */
    public void performDrawAction(Players player)
    {
        // Deal a card from the draw deck
        dealtCard = drawDeck.dealCard();
        // IF the dealtCard is not null THEN
        if(dealtCard != null)
        {
            // Add the dealt card to player's hand
            hands.get(player).add(dealtCard);
        }

        // IF the draw deck is empty THEN
        if(drawDeck.size() == 0)
        {
            // CALL reshuffle draw deck
            reshuffleDrawDeck();
        }
        // END IF
        
        // Indicate this observable has changed
        this.setChanged();
        // Notify observers
        notifyObservers();
    }

    /** {@inheritDoc} */
    public void resetDeck()
    {
        // Create new decks for the discard and draw decks
        drawDeck = new Deck();
        discardDeck = new Deck();
        // Empty the discard deck by dealing all its cards into a List of card
        discardDeck.dealHand(discardDeck.size());
    }

    /** {@inheritDoc} */
    public void reshuffleDrawDeck()
    {
        // Create a new draw deck List of cards
        ArrayList<Card> deck = new ArrayList<Card>();
        // Empty the discard deck into the new list
        deck = (ArrayList<Card>)discardDeck.dealHand(discardDeck.size());
        // Create a new discard card Array List of cards
        ArrayList<Card> discardCard = new ArrayList<Card>();
        // Remove the first card from the new list
        Card temp = deck.remove(0);
        // Add removed card to new discard card Array List
        discardCard.add(temp);
        // Set the discard deck to a new Deck with the topDiscard list
        discardDeck = new Deck(discardCard);
        // Shuffle the new draw deck

        // Set the table's draw deck to a new Deck with the new draw deck list

        /* I don't understand the pseudocode so im writing my own - Jon */
        drawDeck = new Deck(deck);
        drawDeck.shuffle();
    }

    /** {@inheritDoc} */
    public void setDiscardDeck(Deck presetDeck)
    {
        // Set the discard deck to the preset deck
        discardDeck = presetDeck;

    }

    /** {@inheritDoc} */
    public void setDrawDeck(Deck presetDeck)
    {
        // Set the draw deck to the preset deck
        drawDeck = presetDeck;

    }

    /** {@inheritDoc} */
    public void startGame()
    {
        // Declare a constant for cards per hand and set it to 7
        final int kCardsPerHand = 7;
        // Deal cards per hand to hands for human and computer players
        
        /*
         * This could be done with a simple for each loop if you rearranged
         * your enum so that human came first or if you just didnt insist
         * on the cards being dealt to the human first.
         * -Jon
         */
        Players player = Players.kHuman;
        /* do while the player isnt human -Jon */
        do
        {
            hands.put(player,
                (ArrayList<Card>)drawDeck.dealHand(kCardsPerHand));
            player = Players.next(player);
        }
        while(!player.equals(Players.kHuman));

        // WHILE the discard is a wild Do
        do
        {
            // Turn over the next card in the draw deck
            discard = drawDeck.dealCard();
            // Add the card to the top of the discardDeck
            discardDeck.addCard(discard);

        } while(discard.color().equals(Color.wild));
        // END Loop
        
        // Set table's current color to color of discard card
        chosenColor = discard.color();
        // Set direction of play to clockwise (true)
        isClockwise = true;
        
    }
}
