package cero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * FakeController represents a fake GameController between the table and
 * the user interface and is ment to provide minimal support for game
 * function to be used for unit testing of other modules.
 * The only function it performs is to keep track of the last action performed.
 *
 * @author Dirk Cummings
 * @version 1.0 - April 13, 2010
 */
public class FakeController implements ActionListener, I_Controller
{
    private String lastAction;

    public FakeController()
    {

    }

    public void actionPerformed(ActionEvent e) {
        lastAction = e.getActionCommand();
    }

    public String getLastAction()
    {
        return lastAction;
    }

    public void setTable(I_Table tbl) {
        
    }

    public void addPlayer(Players who, I_Player newInterface) {
        
    }

    public void startGame() {
        
    }

    public void changeTurn() {
        
    }

    public Players getCurrentPlayer() {
        return Players.kHuman;
    }

    public State getState() {
        return State.playing;
    }

    public void setOptions(I_Options opt)
    {

    }

    public I_Options getOptions()
    {
        return new FakeOptions();
    }

    public ArrayList<Players> getPlayOrder()
    {
        ArrayList<Players> ret = new ArrayList<Players>();
        ret.add(Players.kHuman);
        ret.add(Players.kHuman);
        return ret;
    }

    public void resetGame() {
    }
}
