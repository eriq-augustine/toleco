package cero;

import java.util.*;

/**
 * ConsoleApp is a driver to run Cero in console mode.  It 
 * will perform the necessary setup to run the game with the same
 * functionality as in Swing mode.  It will also ask the user for
 * the set of game options to enforce before the start of a game.
 *
 * @author Raymond Wong - Javadoc, Pseudocode
 * @author Dirk Cummings - Javadoc
 * @version 1.0 - February 28, 2010
 */
public class ConsoleApp
{
    /** Reference to the game controller */
    private GameController control;
    /** Reference to the human player */
    private I_Player appUI;
    /** Reference to machine player 1, 2, and 3 */
    private I_Player[] machines;
    /** A preset deck for the game */
    private Deck presetDeck;
    /** The table to be used in the game */
    private I_Table table;
    /** Flag indicates if playing on a console */
    private boolean consoleMode = true;

    /** 
     * Initializes the components and starts the game. 
     * @param deckFile The name of the preset deck file.
     * @pre deckFile is non-null
     */
    public void startup(String deckFile)
    {
        final int kNumComputers = 3;
        //Create a new Scanner with System.in
        Scanner sc = new Scanner(System.in);

        //BEGIN
        try
        {
            //IF deckFile is empty string THEN
            if (deckFile.equals(""))
            {
                //SET presetDeck to a new Deck
                presetDeck = new Deck();
            }
            //ELSE
            else
            {
                //CALL Deck to makeDeckFromFile with deckFile RETURNING aDeck
                //SET presetDeck to aDeck
                presetDeck = Deck.makeDeckFromFile(deckFile);
            }
            //END IF
        }
        //EXCEPTION
        catch (java.io.FileNotFoundException ex) //WHEN FileNotFoundException
        {
            //Print error message unable to find deck file
            System.out.println("Unable to find deck file " + deckFile);
            //Exit
            System.exit(-1);
        }
        //END

        //SET control to a new GameController
        control = new GameController();
        //SET table to a new Table
        table = new Table();
        //CALL control to setTable with table
        control.setTable(table);
        // Fixes Defect # 252 
        // Set GameController to console mode
        control.setConsoleMode(true);

        //SET appUI to a new ConsolePlayer
        appUI = new ConsolePlayer();
        //SET machines to 3 computer players
        machines = new I_Player[kNumComputers];
        //Get the game options from the controller
        I_Options gameOpt = control.getOptions();

        //CALL appUI to show the game rules
        ((ConsolePlayer) appUI).showGameRules();
        //CALL appUI to show the developers names
        ((ConsolePlayer) appUI).showDevelopers();
        //CALL appUI to show the version
        ((ConsolePlayer) appUI).showVersion();
        //CALL appUI to show the build
        ((ConsolePlayer) appUI).showBuild();

        String input = "";

        //Prompt if player wants draw one behavior or not
        System.out.println("Do you want to be able to draw more than one"
            + " card on each turn? (\"y\" for yes, \"n\" for no)");
        // Capitalize all alphabet characters.  See ticket 250.  Not a defect.
        input = sc.nextLine().toUpperCase();
        //IF input equals "y" THEN
        if (input.equals("Y"))
        {
            //Tell player draw more than one behavior set to on
            System.out.println("Multiple draws allowed");
            //SET multiple draws to true on game options
            gameOpt.setMultipleDraws(true);
        }
        //ELSE IF input equals "n" THEN
        else if (input.equals("N"))
        {
            //Tell player draw more than one behavior set to on
            System.out.println("Multiple draws not allowed");
            //SET multiple draws to false on game options
            gameOpt.setMultipleDraws(false);
        }
        //ELSE
        else
        {
            //Tell player invalid input and draw more than one behavior is on
            System.out.println("Invalid input; multiple draws allowed");

        }
        //END IF

        input = "";
        //Create a ComputerLevel lvl
        ComputerLevel lvl;
        //SET currentPlayer to computer 1
        Players currentPlayer = Players.kComputer1;
        //FOR each machine
        for (int player = 0; player < kNumComputers; player++)
        {
            //Ask the user to enter 0, 1, 2 (the index of ComputerLevels
            // corresponding to easy, medium, and hard)
            System.out.println("Enter a computer level for computer " + player +
                " (0 = easy, 1 = medium, 2 = hard):");
            input = sc.nextLine();

            //IF input index equals 0, 1, or 2 THEN
            if (input.equals("0") || input.equals("1") || input.equals("2"))
            {
                //CALL getLevel in ComputerLevel with the input index
                //SET lvl with the return value from getLevel
                lvl = ComputerLevel.getLevel(Integer.parseInt(input));
            }
            //ELSE
            else
            {
                //Print a message indicating invalid input, computer set
                // to medium difficulty level
                System.out.println("Invalid level; computer set to medium");
                //Set lvl to medium
                lvl = ComputerLevel.medium;
            }
            //END IF

            //IF lvl equals easy THEN
            if (lvl.equals(ComputerLevel.easy))
            {
                //SET current machine to an EasyComputerPlayer
                machines[player] = new EasyComputerPlayer();

            }
            //Fixes defect 278
            //ELSE IF lvl equals hard THEN
            else if (lvl.equals(ComputerLevel.hard))
            {
                //SET current machine to a HardComputerPlayer
                machines[player] = new HardComputerPlayer();
            }
            //ELSE
            else
            {
                //SET current machine to a MediumComputerPlayer
                machines[player] = new MediumComputerPlayer();
            }
            //END IF

            //SET current machine to lvl in the game options
            // as their respective player (Computer1, Computer2, Computer3)
            gameOpt.setComputerLevel(currentPlayer, lvl);

            //SET current player to the next player
            currentPlayer = Players.next(currentPlayer);
        }
        //END FOR

        input = "";
        //FOR each player in the game
        for (Players curPlayer : Players.values())
        {
            //Ask the user to enter a name for the player
            System.out.println("Enter a name for player " + curPlayer + ":");
            input = sc.nextLine();
            //IF the input is not empty THEN
            if (input.length() != 0)
            {
                //SET current player's name to input string in game options
                // as their respective player
                // (Human, Computer1, Computer2, Computer3)
                gameOpt.setName(curPlayer, input);
            }
            //END IF
        }
        //END FOR

        //CALL appUI to setControl with control
        appUI.setControl(control);
        //CALL appUI to setTable with table
        appUI.setTable(table);

        //FOR each machine
        for (int player = 0; player < kNumComputers; player++)
        {
            //CALL current machine to setControl with control
            machines[player].setControl(control);
            //CALL current machine to setTable with table
            machines[player].setTable(table);
        }
        //END FOR

        //CALL table to addObserver appUI
        ((Table) table).addObserver((ConsolePlayer) appUI);

        //CALL control to addPlayer appUI as a Human player
        control.addPlayer(Players.kHuman, appUI);
        //CALL control to addPlayer machine1 as Computer1 player
        I_Player machine1 = machines[0];
        control.addPlayer(Players.kComputer1, machine1);
        //CALL control to addPlayer machine2 as Computer2 player
        I_Player machine2 = machines[1];
        control.addPlayer(Players.kComputer2, machine2);
        //CALL control to addPlayer machine3 as Computer3 player
        I_Player machine3 = machines[2];
        control.addPlayer(Players.kComputer3, machine3);

        //Set current scanner to ConsolePlayer's scanner
        //See change request 16
        ((ConsolePlayer) appUI).setScanner(sc);

        //CALL control to set the draw deck
        control.setDrawDeck(presetDeck);
        
        //CALL control to startGame
        control.startGame();
    }
}
