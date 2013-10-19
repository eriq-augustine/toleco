package cero;

import java.awt.event.ActionEvent;
import java.util.Observable;

/**
 * Scripted Player represents a player in the game but that has predetermined
 * 'scripted' plays to reach a desired game outcome. Use for testing of
 * GameController.
 *
 * @author Dirk Cummings
 */
public class ScriptedPlayer implements I_Player {

    private I_Controller gc;
    private I_Table tbl;
    private String script;
    private int turnCount = 0;
    
    public ScriptedPlayer(String script)
    {
        this.script = script;
    }

    public void setControl(I_Controller gc) {
        this.gc = gc;
    }

    public void setTable(I_Table tbl) {
        this.tbl = tbl;
    }

    public void setup() {
        // Not supported
    }

    public void resetDeck() {
        // Not supported
    }

    public void doTurn() {
        ActionEvent evnt;
        char command = script.charAt(turnCount++);
        switch(command)
        {
            case 'D':
                evnt = new ActionEvent(this, 0, "draw");
                gc.actionPerformed(evnt);
                // to make up for the last of action after a draw move
                evnt = new ActionEvent(this, 0, "pass");
                gc.actionPerformed(evnt);
                break;
            //Added by Raymond 
            case 'P':
                evnt = new ActionEvent(this, 0, "pass");
                gc.actionPerformed(evnt);
                break;
            default:
                int index = (int) command - '0';
                evnt = new ActionEvent(this, 0, "" + index);
                gc.actionPerformed(evnt);
                break;
        }
    }

    public void showGameOver(Players winner) {
        System.out.println("Player " + winner.toString() + " wins!");
    }

    public void showColorChooser() {
        String colors = "rbgyw";
        char command = script.charAt(turnCount++);
        int index = colors.indexOf(command);
        Color color = Color.pos(index);
        gc.actionPerformed(new ActionEvent(this, 0, "color"+color.toString()));
    }

    public void update(Observable obs, Object obj) {
        // Not supported
    }

    public void showStatusMessage(String mes) {
        // Not supported
    }
}
