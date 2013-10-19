package cero;

import javax.swing.UIManager;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * CeroApp represents the Cero game. It contains all of the elements required to
 * play the game, such as the players, the deck of cards, the table, and the
 * set of rules.
 *
 * @author Raymond Wong - Javadocs
 * @author Matt Carson - Pseudocode, Unit Code
 * @version 1.0 - February 28, 2010
 */

public class CeroApp extends SingleFrameApplication
{
    /** Reference to the game controller */
    private GameController control;
    /** Reference to the human player */
    private static I_Player appUI;
    /** Reference to computer player 1 */
    private I_Player machine1;
    /** Reference to computer player 2 */
    private I_Player machine2;
    /** Reference to computer player 3 */
    private I_Player machine3;
    /** A preset deck for the game */
    private Deck presetDeck = new Deck();
    /** The table to be used in the game */
    private Table myTable = new Table();
    /** Flag indicates if playing on a console */
    private boolean consoleMode = false;

    private static String preset = null;
     /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup()
    {
        // IF
        if(preset != null)
        {
            try
            {
                //presetDeck = null;
                presetDeck = Deck.makeDeckFromFile(preset);
            }
            catch(Exception ex)
            {
                System.out.println(ex.toString());
            }
        }

        //FIXED DEFECT 285
        try
        {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception expt)
        {
            expt.printStackTrace();
        }

        // show the main frame of the application
        appUI = new SwingPlayer(this);
        control = new GameController();
        control.setTable(myTable);
        appUI.setControl(control);
        appUI.setTable(myTable);
        machine1 = (I_Player) new MediumComputerPlayer();
        machine1.setControl(control);
        machine1.setTable(myTable);

        machine2 = (I_Player) new MediumComputerPlayer();
        machine2.setControl(control);
        machine2.setTable(myTable);

        machine3 = (I_Player) new MediumComputerPlayer();
        machine3.setControl(control);
        machine3.setTable(myTable);

        myTable.addObserver((I_Player) appUI);

        control.addPlayer(Players.kHuman, appUI);
        control.addPlayer(Players.kComputer1, machine1);
        control.addPlayer(Players.kComputer2, machine2);
        control.addPlayer(Players.kComputer3, machine3);
        control.setDrawDeck(presetDeck);
        control.startGame();
        appUI.update(null, null);

        // IF appUI needs show()ing THEN
        if (appUI instanceof SwingPlayer)
        {
            show((SwingPlayer) appUI);
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     * @param root the window to be configured
     * @pre root is non-null
     */
    @Override protected void configureWindow(java.awt.Window root)
    {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of CeroApp
     */
    public static CeroApp getApplication()
    {
        // GET and return an instance of the CeroApp class
        return Application.getInstance(CeroApp.class);
    }

    /**
     * Main method launching the application.
     * @param args See initialize().
     */
    public static void main(String[] args)
    {
        // Launch CeroApp class with args
        if(args != null && args.length > 0 && args[0].equals("-c"))
        {
            ConsoleApp ca = new ConsoleApp();
            // Fixes Defect # 251
            // IF there is anothr arguement THEN
            if(args.length > 1)
            {
                // Give the ConsoleApp the filename for a preset deck
                ca.startup(args[1]);
            }
            // ELSE
            else
            {
                // Give the ConsoleApp an empty string
                ca.startup("");
            }
        }
        else
        {
            // Launch
            if(args != null && args.length == 1)
            {
                preset = args[0];
            }

            launch(CeroApp.class, args);
        }
    }

    /**
     * Get a reference to the current appUI
     * @return a reference to the current appUI
     */
    public static I_Player getAppUI()
    {
        return appUI;
    }
}
