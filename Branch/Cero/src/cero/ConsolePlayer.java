package cero;
import java.awt.event.ActionEvent;
import java.util.*;

/**
 * A console-based user interface to the game.
 * @see "Requirements Document"
 * 
 * @author Raymond Wong - JavaDoc, Pseudocode
 * @version 1.0 - February 28, 2010
 */
public class ConsolePlayer implements java.util.Observer, I_Player
{

    /** Reference to the table for this game    */
    private I_Table table;

    /** Reference to the controller */
    private I_Controller controller;

    /** Input reader */
    private Scanner console;

    /** True if the hand has no playable cards */
    private boolean mustDraw;

    /** Indicates a draw move or pass move*/
    private PlayerAction playerMove;

    /** 
     * Determines if the player has drawn at least once.
     * See defect ticket 238.  
     */
    private boolean hasDrawn;

    /** Information about application. */
    private AboutBox box;

    /** Game rules and instructions. */
    private HowToPlay instns;

    /** Flag to indicate quit console mode. */
    private boolean quitGame;

    /** Determines if the move entered into the console is valid or not */
    private boolean valid;
    
    /**
     * Constructs a ConsolePlayer when the user indicates -c as a command
     * line argument to CeroApp.
     * @post options is not null.
     * @post box is not null.
     * @post instns is not null. 
     * @post console is not null.
     * @post mustDraw is false.
     * @post quitGame is false.
     */
    ConsolePlayer() 
    {
        //Set console a new Scanner with System.in (keyboard reader)
        console = new Scanner(System.in);
        //Set mustDraw to false.
        mustDraw = false;
        //Set box to a new AboutBox.
        box = new AboutBox();
        //Set instns to a new HowToPlay.
        instns = new HowToPlay();
        //Set quitGame to false
        quitGame = false;
        //Set hasDrawn to false
        hasDrawn = false;
    }

    /** {@inheritDoc} */
    public void setControl(I_Controller aController)
    {
        //SET controller to aController
        controller = aController;
    }

    /** {@inheritDoc} */
    public void setTable(I_Table tbl)
    {
        //SET table to tbl
        table = tbl;
    }

    /** {@inheritDoc} */
    public void resetDeck()
    {
        //CALL table to resetDeck
        table.resetDeck();
    }

    /** {@inheritDoc} */
    public void showGameOver(Players winner)
    { 
        //IF winner equals human player THEN
        if (winner.equals(Players.kHuman))
        {
            //Print game over, human player wins
            System.out.println("Hurray, YOU WIN!");
        }
        //ELSE
        else
        {
            //Print game over, human player loses
            System.out.println("Bummer, you lost.");
        }
        //END IF
        //Set quitGame to true.  See defect 249.
        quitGame = true;
    }

    /** {@inheritDoc} */
    public void showColorChooser()
    {
        final int kColorLength = 5;
        //Create and set a string move to an empty string
        String move = "";
        //Create a boolean validMove set to false
        boolean validMove = false;

        //WHILE move does not contain substring "color" (validMove is false)
        while (!validMove)
        {
            //Print a message to choose a color
            System.out.println("Please choose a color");
            //Set move by CALLING getMove
            move = getMove();
            //IF move does not contain substring "color"
            if (!move.substring(0, kColorLength).equals("color"))
            {
                //Print a message invalid color
                System.out.println("Please enter a valid color " +
                    "(blue, green, yellow, red)");
                validMove = false;
            }
            //ELSE
            else
            {
                //SET validMove to true
                validMove = true;
            }
            //END IF
        }
        //END WHILE

        //Create a new ACTION_PERFORMED event for move
        ActionEvent evt = new ActionEvent(this,
                ActionEvent.ACTION_PERFORMED, move);
        //CALL controller actionPerformed with move
        controller.actionPerformed(evt);
    }

    /** {@inheritDoc} */
    public void update(java.util.Observable obs, Object obj)
    {
        System.out.println("-----------------------------------");
        //CALL display computer hand
        displayComputerHand();
        //CALL refresh deck 
        refreshDeckPanel();
        //CALL display human hand 
        displayHumanHand();
    }

    /** {@inheritDoc} */
    public void showStatusMessage(String message) 
    {
        //Print message to the screen.
        System.out.println(message);
    }
    
    /** {@inheritDoc} */
    public void doTurn()
    {
        //WHILE not quit game.  Moved loop to doTurn.  See defect 240.
        while (!quitGame)
        {
            //Create a string, move, set to null
            String move = null;

            //CALL update with null values to show table display
            update(null, null);

            //Added logic to pass when player has drawn and draw one turned on
            //See defect ticket 238.  Also see defect 257.  
            //IF (table's draw deck is empty and must draw and discard deck
            //    equals 1) or (mustDraw and multiple draws not allowed and
            //    hasDrawn) THEN
            if (((table.getDrawDeckSize() == 0) && mustDraw &&
                table.getDiscardDeckSize() == 1) ||
                (mustDraw && !controller.getOptions().multipleDrawsAllowed() &&
                hasDrawn))
            {
                //Print a message to press enter to pass.
                System.out.println("You must pass on this turn.  " +
                        "Press 'p' to pass.");
                // SET playerMove to pass
                playerMove = PlayerAction.pass;
            }
            // ELSE IF must draw THEN
            else if (mustDraw)
            {
                //Print a message to press enter to draw a card
                System.out.println("You must draw on this turn");
                //SET playerMove to draw
                playerMove = PlayerAction.draw;
            }
            //ELSE
            else
            {
                //Print a message to press enter to draw card or to enter
                // a card to discard a card.
                System.out.println("Press enter to draw a card" +
                    " or enter a string to play card");
                //SET playerMove to draw
                playerMove = PlayerAction.draw;
            }
            //END IF

            //SET move by CALLING getMove
            move = getMove();
            //IF not quit game THEN
            if (!quitGame)
            {
                //Create a new ACTION_PERFORMED event for move
                ActionEvent evt = new ActionEvent(this,
                        ActionEvent.ACTION_PERFORMED, move);
                //CALL controller actionPerformed with move
                controller.actionPerformed(evt);
            }
            //END IF
        }
        //END WHILE
    }

    /** {@inheritDoc} */
    public void setup()
    {
    }

    /** Display computer hand */
    private void displayComputerHand()
    {
        //Create an empty string, computerHand
        String computerHand = "";

        ArrayList<Card> compHand;

        //Get computer 1 hand from the table
        compHand = table.getHand(Players.kComputer1);
        //Print a message to indicate the following cards below to computer 1
        System.out.println(controller.getOptions().getName(Players.kComputer1)
                + " hand (" + controller.getOptions().getComputerLevel(
                Players.kComputer1) + "):");
        //FOR each card in computer 1's hand
        for (Card card : compHand)
        {
            //Append computerHand with the toString value of the card
            computerHand = computerHand + " " + card.toString();
        }
        //END FOR
        //Print computerHand
        System.out.println(computerHand);

        computerHand = "";
        //Get computer 2 hand from the table
        compHand = table.getHand(Players.kComputer2);
        //Print a message to indicate the following cards below to computer 2
        System.out.println(controller.getOptions().getName(Players.kComputer2)
                + " hand (" + controller.getOptions().getComputerLevel(
                Players.kComputer2) + "):");
        //FOR each card in computer 2's hand
        for (Card card : compHand)
        {
            //Append computerHand with the toString value of the card
            computerHand = computerHand + " " + card.toString();
        }
        //END FOR
        //Print computerHand
        System.out.println(computerHand);

        computerHand = "";
        //Get computer 3 hand from the table
        compHand = table.getHand(Players.kComputer3);
        //Print a message to indicate the following cards below to computer 3
        System.out.println(controller.getOptions().getName(Players.kComputer3)
            + " hand (" + controller.getOptions().getComputerLevel(
                Players.kComputer3) + "):");
        //FOR each card in computer 3's hand
        for (Card card : compHand)
        {
            //Append computerHand with the toString value of the card
            computerHand = computerHand + " " + card.toString();
        }
        //END FOR
        //Print computerHand
        System.out.println(computerHand);
    }

    /** Display the deck panel */
    private void refreshDeckPanel()
    {
        //Print current discard and the size of the deck
        Card discarded = table.getDiscardCard();
        int drawSize = table.getDrawDeckSize();
        System.out.println("Current discard card: " + discarded.toString() +
            "          " + "Draw deck size: " + drawSize);
        //IF current discard requires next card to follow a color THEN
        if (table.getColor() != null)
        {
            //Print a message with the current color
            System.out.println("Color of current discard: " + table.getColor());
        }
        //END IF
    }

    /** Display the human hand */
    private void displayHumanHand()
    {
        //Get the human's hand from the table
        ArrayList<Card> humanHand = table.getHand(Players.kHuman);
        //Create an empty string of cards
        String humanCards = "";
        //SET mustDraw to true
        mustDraw = true;
        //Sort the human's hand
        Collections.sort(humanHand);

        String curCard = "";
        //FOR each card in the hand
        for (Card card : humanHand)
        {
            //IF current card is playable THEN
            if (table.isPlayableCard(card))
            {
                //Append the card with a plus sign to indicate it's playable
                curCard = "+" + card.toString();
                //SET mustDraw to false
                mustDraw = false;
            }
            //ELSE
            else
            {
                //Append the card with a blank to indicate it's not playable
                curCard = card.toString();
            }
            //END IF

            //Add the current card's string to the string of cards
            humanCards = humanCards + " " + curCard;
        }
        //END FOR

        //Print the string of cards
        System.out.println("Your hand:");
        System.out.println(humanCards);
    }

    /**
     * Asks the user for a move until a valid move is entered.
     * @return One of the PlayerAction moves
     */
    private String getMove()
    {
        //Create an empty input string
        String input = "";
        //Create an empty move string
        String move = "";

        valid = false;

        //WHILE move is invalid (flag equals false)
        while (!valid)
        {
            //Print to ask for a move
            System.out.println("Enter a move: ");
            //Read a line of input to get move
            input = console.nextLine().toUpperCase();
            
            move = processInput(input, move);

            //IF move is invalid (flag equals false) THEN
            if (!valid)
            {
                //Print a message to indicate invalid move
                System.out.println("Invalid move");
            }
            //END IF
        }
        //END WHILE

        //IF move string equals "q" THEN
        if (move.equals("Q"))
        {
            //Print a newline
            System.out.println("");
            //Set quitGame to true
            quitGame = true;
        }
        //END IF

        //IF move string is empty or equals "p" THEN
        if (move.length() == 0 || move.equals("P"))
        {
            //Set move string to the toString value of playerMove
            move = playerMove.toString();
        }
        //END IF

        //RETURN move string
        return move;
    }

    /**
     * Prints the game rules and instructions of the game.
     * @post instns is not null.
     */
    public void showGameRules() 
    {
        //GET and print the game rules
        //System.out.println("Game rules and instructions:"); //defect# 268
        System.out.println(instns.getGameRules());
    }

    /**
     * Prints the names of the developers of Cero.
     * @pre box is not null.
     */
    public void showDevelopers() 
    {
        //GET and print the developers
        //System.out.println("Developers:"); //defect# 268
        System.out.println(box.getDevelopers());
    }

    /**
     * Prints the current version of the application.
     * @pre box is not null.
     */
    public void showVersion() 
    {
        //GET and print the version
        //System.out.println("Version:"); //defect# 268
        System.out.println(box.getVersion());
    }

    /**
     * Prints the current build of the application.
     * @pre box is not null.
     */
    public void showBuild() 
    {
        //GET and print the build
        //System.out.println("Build:"); //defect# 268
        System.out.println(box.getBuild());
    }

    /** 
     * Set ConsolePlayer's scanner to sc.
     * See change request 16.
     * @param sc A scanner object with preset input stream.
     * @pre sc is non null.
     */
    public void setScanner(Scanner sc)
    {
        console = sc;
    }

    /**
     * Private helper method for getMove to process input information
     * from console
     * @param input reference to the input string
     * @param move refernce to the move string
     * @return Returns a string of the move to make
     */
    private String processInput(String input, String move)
    {
        // Added via change request 22
        //IF input length is zero THEN
        if (input.length() == 0)
        {
            //Extra checking if player presses enter and may only pass.
            //See defect 238.
            //IF playerMove equals draw THEN
            if (playerMove.equals(PlayerAction.draw))
            {
                //Set move string to empty string
                move = "";
                //Set flag to true
                valid = true;
                //set hasDrawn to true.  See defect 238
                hasDrawn = true;
            }
            //END IF
        }
        //ELSE IF input is one-character THEN
        else if (input.length() == 1)
        {
            //IF input equals "q" THEN
            if (input.equals("Q"))
            {
                //Set move string to "q"
                move = "Q";
                //Set flag to true
                valid = true;
            }
            //ELSE IF input equals "p" THEN.  See defect 238
            else if (input.equals("P"))
            {
                //Set move string to "p"
                move = "P";
                //Set flag to true
                valid = true;
                //Reset hasDrawn to false.
                hasDrawn = false;
            }
            //END IF
        }
        // ELSE IF input is two-character (indicates a card choice) THEN
        else if (input.length() == 2)
        {
            ArrayList<Card> humanHand = table.getHand(Players.kHuman);
            // FOR each card in human hand
            for (int cardNdx = 0; cardNdx < humanHand.size(); cardNdx++)
            {
                //IF card equals card value of input and is playable THEN
                if (humanHand.get(cardNdx).equals(Card.valueOf(input))
                    && table.isPlayableCard(Card.valueOf(input)))
                {
                    //Set move string to "play" and the string of the
                    // card's index in hand
                    move = "" + cardNdx;
                    //Set valid to true
                    valid = true;
                    //Set hasDrawn to false.  See defect 238.
                    hasDrawn = false;
                }
            }
        }

        //ELSE IF input length > 2 THEN
        else if (input.length() > 2)
        {
            //IF input is name of a color THEN
            if (input.equals("BLUE") || input.equals("RED") ||
                input.equals("YELLOW") || input.equals("GREEN"))
            {
                //Set move string to "color" with name of color
                // concatenated to the end
                move = "color" + input.toLowerCase();
                //Set flag to true
                valid = true;
                //Reset hasDrawn to false.  See defect 238.
                hasDrawn = false;
            }
            //END IF
        }
        //END IF

        return move;
    }
}
