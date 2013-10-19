/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.GUI;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tolecoFunctionalPrototype.Core.Location;
import tolecoFunctionalPrototype.Core.Logic;
import tolecoFunctionalPrototype.Core.Unit;

/**
 *
 * @author Abbarton
 */
public class GUI_Controller{

   private Location selectedLocation;
   private Logic myLogic;

   private State currState;

   public StatusPanel _status;
   public ButtonPanel _buttons;
   public GameBoard _board;
/**
 *
 * @param logic
 * @param status
 * @param buttons
 * @param board
 */
   public void gameStart(Logic logic, StatusPanel status, ButtonPanel buttons,
           GameBoard board) {
      myLogic = logic;
      myLogic.start();
      _status = status;
      _buttons = buttons;
      buttons.changePlayerColor(0);
      _board = board;
      currState = State.noSelected;
      status.gotoStateBackStory("This is a functional prototype of Toleco!");
      buttons.gotoStateGrayed();
      _board.drawMap(myLogic.map);
      _board.selectCell(-1,-1);
      _buttons.gotoStateAttack();
      _buttons.gotoStateGrayed();
   }
   /**
    *
    */
   public void okClicked() {
      if(currState != State.locationSelected) {
         System.out.println("Flow reached okClicked while" +
                 "GUI_Controller wasn't in the right state");
      }
      _buttons.gotoStateAttack();
      _buttons.gotoStateGrayed();
      if(selectedLocation.myUnit != null)
      {
        _status.gotoStateUnit(selectedLocation);
      }
      else
      {
        _status.gotoStateBackStory("This is a functional prototype of Toleco!");
      }
   }
   /**
    *
    */
   public void cancelClicked() {
      if(currState != State.attackMode) {
         System.out.println("Flow reached cancelClicked while" +
                 "GUI_Controller wasn't in the right state");
      }
      ArrayList<Point> blah = new ArrayList<Point>();
      for(int i = 0; i < myLogic.map.length; i++)
      {
         for(Location loc: myLogic.map[i])
         {
            if(loc != selectedLocation)
            {
               blah.add(new Point(loc.xLoc,loc.yLoc));
            }
         }
      }
      _board.lowlightLocations(blah);
      _buttons.gotoStateAttack();
      currState = State.locationSelected;
   }
   /**
    *
    */
   public void attackClicked() {
      if(currState != State.locationSelected) {
         System.out.println("Flow reached attackClicked while" +
                 "GUI_Controller wasn't in the right state");
      }else {
         currState = State.attackMode;
         _buttons.gotoStateCancel();
         ArrayList<Location> highLight = new ArrayList<Location>();
         for(int index = 0; index < myLogic.map.length; index++) {
            for(Location loc: myLogic.map[index]) {
               if(myLogic.inRange(selectedLocation,  loc)
                       && loc.myUnit.playerNum != selectedLocation.myUnit.playerNum) {
                  highLight.add(loc);
               }
            }
         }
         _board.highlightLocations(highLight);
         _board.drawMap(myLogic.map);
      }

   }
   /**
    *
    * @param x
    * @param y
    */
   public void cellSelected(int x, int y) {
      switch(currState) {
         case locationSelected:
         case noSelected:
            _buttons.gotoStateAttack();
            _buttons.gotoStateGrayed();
            _board.selectCell(x,y);
            selectedLocation = myLogic.map[x][y];
            if(myLogic.map[x][y].myUnit != null)
            {
               _status.gotoStateUnit(myLogic.map[x][y]);
               if(myLogic.map[x][y].myUnit.playerNum == myLogic.whoseTurn
                       && myLogic.map[x][y].myUnit.canAttack) {
                  _buttons.gotoStateAttack();
               }else {
                  _buttons.gotoStateGrayed();
               }

            }else {
               _status.gotoStateTerrain(myLogic.map[x][y].myTerrain);
               _buttons.gotoStateGrayed();
            }
            currState = State.locationSelected;
            _board.drawMap(myLogic.map);
            break;
         case attackMode:
            if(myLogic.inRange(selectedLocation, myLogic.map[x][y]) &&
             myLogic.map[x][y].myUnit.playerNum != selectedLocation.myUnit.playerNum)
            {
               Unit atk = selectedLocation.myUnit;
               Unit def = myLogic.map[x][y].myUnit;
               String summary = attackAndSumarize(selectedLocation, myLogic.map[x][y]);
               _buttons.gotoStateOk();
               _status.gotoStateBattleSummary(atk,def,summary);
               if(myLogic.isGameOver() == 1) {
                   _board.drawMap(myLogic.map);
                   _board.lowlightLocations();
                   int chosen = JOptionPane.showConfirmDialog(_board, "Leaftron has won the game. Would you like to quit?","Exit",JOptionPane.YES_NO_OPTION);
                   if(chosen == JOptionPane.YES_OPTION)
                   {
                       System.exit(0);
                   }
               }
               if(myLogic.isGameOver() == 2) {
                   _board.drawMap(myLogic.map);
                   _board.lowlightLocations();
                   int chosen2 = JOptionPane.showConfirmDialog(_board, "FireMahn has won the game. Would you like to quit?","Exit",JOptionPane.YES_NO_OPTION);
                   if(chosen2 == JOptionPane.YES_OPTION)
                   {
                       System.exit(0);
                   }
               }


               _board.lowlightLocations();
               _board.drawMap(myLogic.map);
               _board.selectCell(selectedLocation.xLoc, selectedLocation.yLoc);
               currState = State.locationSelected;
            }else {
               //do wat?
            }
            break;
      }
   }
   /**
    * just does the attack and generate the battle summary string.
    * Put here to make that switch statement smaller. It was once a pretty
    * function, and then it became warped, and is now an abomination.
    *
    * @param atk
    * @param def
    * @return
    */
   private String attackAndSumarize(Location atk, Location def) {
      int aHealthBefore = atk.myUnit.healthRemaining;
      int bHealthBefore = def.myUnit.healthRemaining;
      String atkTeam = (atk.myUnit.playerNum == 1 ? "Green Player's " : "Pink Player's ") + atk.myUnit.name;
      String defTeam = (atk.myUnit.playerNum != 1 ? "Green Player's " : "Pink Player's ") + def.myUnit.name;
      String summary = "";
      myLogic.attack(atk, def);
      summary += atkTeam + " attacks...\n\n";
      summary += defTeam + " receives ";
      if(def.myUnit != null)
      {
         summary += (bHealthBefore - def.myUnit.healthRemaining) + " damage!\n\n";
      }else {
         summary += bHealthBefore + " damage!\n";
         summary +=  defTeam + " dies!\n";
         if(myLogic.isGameOver() != 0) {
             summary += "\n" + (myLogic.isGameOver() == 1 ? "Green " : "Pink ") + "Player Has won the game!";
         }
         return summary;
      }
      if(atk.myUnit != null && aHealthBefore == atk.myUnit.healthRemaining) {
          summary +=  defTeam + " tries to Counterattack, but cannot!";
          return summary;
      }
      summary +=  defTeam + " Counterattacks...\n\n";
      if(atk.myUnit != null) {
         summary += atkTeam + " receives " + (aHealthBefore - atk.myUnit.healthRemaining) + " damage!\n\n";
      }else {
         summary += atkTeam + " receives " + aHealthBefore + " damage!\n\n";
         summary += atkTeam + " dies!\n";
      }
      if(myLogic.isGameOver() != 0) {
         summary += "\n" + (myLogic.isGameOver() == 1 ? "Green " : "Pink ") + "Player Has won the game!";
      }
      return summary;
   }
   /**
    *
    */
   private enum State {
      noSelected,
      attackMode,
      locationSelected;
   }

   /**
    * NOTE: Locations are (row, col), not (x, y).
    * So, (1,0) is 1 down and 0 to the right of the top left corner
    * xLoc = row, yLoc = col
    */
   public void move(KeyEvent evt)
   {
      if(currState == State.locationSelected && selectedLocation != null && selectedLocation.myUnit != null)
      {
         switch(evt.getKeyCode())
         {
            case KeyEvent.VK_DOWN:
               if(selectedLocation.xLoc + 1 != Logic.nMapSize)
               {
                  if(myLogic.move(selectedLocation, myLogic.map[selectedLocation.xLoc + 1][selectedLocation.yLoc]))
                  {
                    cellSelected(selectedLocation.xLoc + 1, selectedLocation.yLoc);
                  }
               }
               break;
            case KeyEvent.VK_UP:
               if(selectedLocation.xLoc - 1 >= 0)
               {
                  if(myLogic.move(selectedLocation, myLogic.map[selectedLocation.xLoc - 1][selectedLocation.yLoc]))
                  {
                    cellSelected(selectedLocation.xLoc - 1, selectedLocation.yLoc);
                  }
               }
               break;
            case KeyEvent.VK_LEFT:
               if(selectedLocation.yLoc - 1 >= 0)
               {
                  if(myLogic.move(selectedLocation, myLogic.map[selectedLocation.xLoc][selectedLocation.yLoc - 1]))
                  {
                    cellSelected(selectedLocation.xLoc, selectedLocation.yLoc - 1);
                  }
               }
               break;
            case KeyEvent.VK_RIGHT:
               if(selectedLocation.yLoc + 1 != Logic.nMapSize)
               {
                  if(myLogic.move(selectedLocation, myLogic.map[selectedLocation.xLoc][selectedLocation.yLoc + 1]))
                  {
                    cellSelected(selectedLocation.xLoc, selectedLocation.yLoc + 1);
                  }
               }
               break;
         }
      }
      _board.drawMap(myLogic.map);
   }

   public void endTurnPressed() {
      myLogic.endTurn();
      _buttons.changePlayerColor(myLogic.whoseTurn-1);
      _buttons.gotoStateAttack();
      _buttons.gotoStateGrayed();
      _status.gotoStateBackStory("This is a functional prototype of Toleco!");
      ArrayList<Point> blah = new ArrayList<Point>();
      for(int i = 0; i < myLogic.map.length; i++)
      {
         for(Location loc: myLogic.map[i])
         {
            blah.add(new Point(loc.xLoc,loc.yLoc));
         }
      }
      _board.lowlightLocations(blah);
      _board.drawMap(myLogic.map);
      currState = State.noSelected;
   }


}
