/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cero;

import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.Application;

/**
 * Tests the SwingPlayer
 * @author Robert Bernal
 */
public class SwingPlayerIntTest {

    public static I_Controller controller;
    public static I_Table table;
    public static I_Options options;

    public SwingPlayerIntTest() {
        
        CeroApp.main(null);

        //Let it sleep to ensure the launch event has time to call startup
        sleep(1000);
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
        sleep(1000);
    }

    /**
     * Conveneince method for forcing the thread to sleep
     * @param millis How many milliseconds to sleep
     */
    private void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch(Exception expt)
        {
            expt.printStackTrace();
        }
    }

    /**
     * Test of showAboutBox method, of class SwingPlayer.
     */
    @Test
    public void testShowAboutBox() {
        ((SwingPlayer) (CeroApp.getAppUI())).showAboutBox();
        sleep(2000);
        //Expect to see an about box pop up
    }

    /**
     * Test of showHowToPlayBox method, of class SwingPlayer.
     */
    @Test
    public void testShowHowToPlayBox() {
        //SwingPlayer appUI;

        //appUI = new SwingPlayer(CeroApp.getApplication());
        ((SwingPlayer) (CeroApp.getAppUI())).showHowToPlayBox();
        sleep(2000);
        //FakeSingleFrameApplication.appUI.showHowToPlayBox();
        //Expect to see a how to play box pop up
    }

    /**
     * Fixes defect 258
     */

    @Test
    public void debug258Test() {
        //SwingPlayer appUI;

        //appUI = new SwingPlayer(CeroApp.getApplication());
        ((SwingPlayer) (CeroApp.getAppUI())).showHowToPlayBox();
        sleep(2000);
        //FakeSingleFrameApplication.appUI.showHowToPlayBox();
        //Expect to see a how to play box pop up with info
        //on how to play
    }

    /**
     * Test of showOptionsMenu method, of class SwingPlayer.
     */
    @Test
    public void testShowOptionsMenu() {

        ((SwingPlayer) (CeroApp.getAppUI())).showOptionsMenu();
        sleep(2000);
        //Expect to see an options menu pop up
    }

    /**
     * Test of setup method, of class SwingPlayer.
     * //reveals defect# 227
     */
    @Test
    public void testSetup() {
        
        //FakeSingleFrameApplication.appUI.setup();
        table = new Table();
        controller = new GameController();
        controller.setTable(table);

        ((SwingPlayer) (CeroApp.getAppUI())).setTable(table);
        ((SwingPlayer) (CeroApp.getAppUI())).setControl(controller);

        controller.addPlayer(Players.kHuman, ((SwingPlayer) (CeroApp.getAppUI())));
        controller.startGame();

        ((SwingPlayer) (CeroApp.getAppUI())).setup();
        sleep(2000);
        
        //expect the same result as after calling update, described below
    }

    /**
     * Test of doTurn method, of class SwingPlayer.
     */
    @Test
    public void testDoTurn() {
        
        //FakeSingleFrameApplication.appUI.doTurn();
        //I_Player appUI;
        table = new Table();
        controller = new GameController();
        controller.setTable(table);

        ((SwingPlayer) (CeroApp.getAppUI())).setTable(table);
        ((SwingPlayer) (CeroApp.getAppUI())).setControl(controller);

        controller.addPlayer(Players.kHuman, ((SwingPlayer) (CeroApp.getAppUI())));
        controller.startGame();

        ((SwingPlayer) (CeroApp.getAppUI())).doTurn();
        sleep(2000);


        //draw button should be enabled now
        //human card chooser should be enabled
    }

    /**
     * Test of showGameOver method, of class SwingPlayer.
     */
    @Test
    public void testShowGameOver() {

        //FakeSingleFrameApplication.appUI.showGameOver(Players.kHuman);
        table = new Table();
        controller = new GameController();
        controller.setTable(table);

        ((SwingPlayer) (CeroApp.getAppUI())).setTable(table);
        ((SwingPlayer) (CeroApp.getAppUI())).setControl(controller);

        controller.addPlayer(Players.kHuman, ((SwingPlayer) (CeroApp.getAppUI())));
        controller.startGame();

        ((SwingPlayer) (CeroApp.getAppUI())).showGameOver(Players.kHuman);
        sleep(2000);
        //Everything in the middle should be hidden except for the message area
        //Message should say you won
        //Player's color chooser background should be yellow
            //while everyone else's is default colored

        
    }

    /**
     * Test of showColorChooser method, of class SwingPlayer.
     */
    @Test
    public void testShowColorChooser() {

        ((SwingPlayer) (CeroApp.getAppUI())).showColorChooser();
        sleep(2000);
    }

    /**
     * Test of update method, of class SwingPlayer.
     */
    @Test
    public void testUpdate() {

        //FakeSingleFrameApplication.appUI.update(null, null);
        table = new Table();
        controller = new GameController();
        controller.setTable(table);

        ((SwingPlayer) (CeroApp.getAppUI())).setTable(table);
        ((SwingPlayer) (CeroApp.getAppUI())).setControl(controller);

        controller.addPlayer(Players.kHuman, ((SwingPlayer) (CeroApp.getAppUI())));
        controller.startGame();

        ((SwingPlayer) (CeroApp.getAppUI())).update(null,  null);
        sleep(2000);
        //The human card selector should be enabled
        //The human card area should be highlighted to indicate player's turn
        //The computer cards should be displayed and not highlighted to indicate
            //it's not their turn
        //The deck panel should be visible and have the draw button
            //enabled without a discard card placed down but showing the card
            //back
        //The arrows in the corners should all be clockwise
    }

    /**
     * Test of showStatusMessage method, of class SwingPlayer.
     */
    @Test
    public void testShowStatusMessage() {
        
        //FakeSingleFrameApplication.appUI.showStatusMessage("I like pie!!!");

        ((SwingPlayer) (CeroApp.getAppUI())).showStatusMessage("I like pie!!!");
        sleep(2000);

        //The status message should say I like pie!!!
    }
}

