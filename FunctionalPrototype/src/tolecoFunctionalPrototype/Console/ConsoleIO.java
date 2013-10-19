/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.Console;

import tolecoFunctionalPrototype.Core.Location;
import tolecoFunctionalPrototype.Core.Logic;

/**
 *
 * @author abbarton
 */
public class ConsoleIO {

   private Logic logic;

   public ConsoleIO(Logic _logic) {
      logic = _logic;
   }

   public String printMap()
   {
      String returnMe = "";
      for(int index = 0; index < logic.nMapSize; index++)
      {
         returnMe += "[";
         for(Location loc: logic.map[index])
         {
            returnMe += loc.toString() + '|';
         }
         returnMe +="]\n";
      }
      returnMe += "Player " + logic.whoseTurn + "'s turn";
      return returnMe;
   }


   public String getInfo(Location loc)
   {
      String returnMe = "";
      if(loc.myUnit != null)
      {
         returnMe += loc.myUnit.toString();
      }

      if(loc.myTerrain != null)
      {
         returnMe += loc.myTerrain.toString();
      }
      else
      {
         returnMe += "Erp! Something really bad happened,\nThis square exists, but doesn't have a terrain";
      }
      return returnMe;
   }

   public boolean parseInput(String input)
   {
      String locs = "";
      if(input.length() > 0 && input.indexOf(" ") != -1) {
         locs = input.substring(input.indexOf(" ")).trim();
      }
      //split on all spaces and commas
      String eachLoc[] = locs.split("[ ,]");
      int xLoc1 = -1;
      int yLoc1 = -1;
      if(eachLoc.length > 1) {
         xLoc1 = Integer.valueOf(eachLoc[0]);
         yLoc1 = Integer.valueOf(eachLoc[1]);

      }
      int xLoc2 = -1;
      int yLoc2 = -1;
      if(eachLoc.length > 2) {
         xLoc2 = Integer.valueOf(eachLoc[2]);
         yLoc2 = Integer.valueOf(eachLoc[3]);
      }
      
      switch(input.charAt(0))
      {
         case('a'):
            if(logic.attack(logic.map[xLoc1][yLoc1], logic.map[xLoc2][yLoc2])) {
               System.out.println("Attack succeeded!");
            }else {
               System.out.println("Attack failed.");
            }
            return true;
         case('m'):
            if(logic.move(logic.map[xLoc1][yLoc1], logic.map[xLoc2][yLoc2])) {
               System.out.println("Move succeeded!");
            }else {
               System.out.println("Move failed.");
            }
            return true;
         case('i'):
            System.out.println(getInfo(logic.map[xLoc1][yLoc1]));
            return true;
         case('e'):
               System.out.println("Ending turn");
               logic.endTurn();
            return true;
         default:
            return false;
      }
   }
}
