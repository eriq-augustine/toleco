package cero;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.SingleFrameApplication;

/**
 * Used for unit testing SwingPlayer.
 * @author iseletsk
 */
public class FakeSingleFrameApplication extends CeroApp
{
    public static SwingPlayer appUI;
    public static I_Controller controller;
    public static I_Options options;
    public static I_Table table;

    /**
     * Called to start the application when launch is called
     * This shouldn't be called manually.  Run the main method to invoke this.
     */
    @Override
    protected void startup() {

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.metal.MetalLookAndFeel");            
        }
        catch (Exception e) {
           // handle exception
        }




        controller = new FakeController();
        options = new FakeOptions();
        table = new FakeTable();

        appUI = new SwingPlayer(this);
        appUI.setControl(controller);
        controller.setOptions(options);
        controller.setTable(table);
        appUI.setTable(table);

        appUI.setVisible(true);

        if (appUI instanceof SwingPlayer)
        {
            show((SwingPlayer) appUI);
        }
    }

/*    /**
     * Convenience method to set the controller
     * @param ctrl
     * @pre startup was called
     */
    public static void setController(I_Controller ctrl)
    {
        controller = ctrl;
        appUI.setControl(controller);
        controller.setOptions(options);
        controller.setTable(table);
    }

    /**
     * Convenience method to set the options
     * @param opts
     * @pre startup was called
     */
    public static void setOptions(I_Options opts)
    {
        options = opts;
        controller.setOptions(options);
    }

    /**
     * Convenience method to set the table
     * @param tbl
     * @pre startup was called
     */
    public static void setTable(I_Table tbl)
    {
        table = tbl;
        controller.setTable(table);
        appUI.setTable(table);
    }

    /**
     * Returns an instance of this object, used by SwingPlayer for
     * getting the application instance.
     * @return Returns an instace of the FakeSingleFrameApplication class
     */
    public static FakeSingleFrameApplication getApplication() {
        // GET and return an instance of the FakeSingleFrameApplication class

        return SingleFrameApplication.
                getInstance(FakeSingleFrameApplication.class);
    }

    /**
     * Call this from a unit test!!!  Not startup.
     * @param args Normally main takes args.  Just pass in null.
     */
    public static void main(String[] args)
    {
        launch(FakeSingleFrameApplication.class, args);
    }
}
