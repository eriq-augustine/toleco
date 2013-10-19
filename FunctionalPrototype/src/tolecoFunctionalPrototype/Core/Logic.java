/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.Core;

/**
 *
 * @author abbarton
 */
public class Logic
{
   public static final int nMapSize = 10;
   public Location[][] map;
   public int whoseTurn;

   /**
    * starts the game by initializing the map and placing two units on it.
    */
   public void start()
   {
      whoseTurn = 1;
      AttackType.init();
      map = new Location[nMapSize][nMapSize];
      Terrain plains = new TerrainPlains();
      Terrain mountains = new TerrainMountains();
      //populate map with blank locations
      for(int index = 0; index < nMapSize; index++)
      {
         for(int inIndex = 0; inIndex < nMapSize; inIndex++)
         {
            Location loc = new Location();
            if(inIndex % 2 == 0) {
               loc.myTerrain = mountains;
            }else {
               loc.myTerrain = plains;
            }
            
            loc.xLoc = index;
            loc.yLoc = inIndex;
            map[index][inIndex] = loc;

         }
      }
      Unit un = new UnitSpearman(1);
      map[0][0].myUnit = un;

      un = new UnitClubman(2);
      un.healthRemaining = 70;
      map[2][1].myUnit = un;

      un = new UnitClubman(1);
      map[1][0].myUnit = un;

   }
   /**
    * Moves the unit on Location one to Location two.
    * Checks if there is a unit on one, a unit on two,
    * if the unit has enough moves left, if the two location are the same,
    * and if the two locations are adjacent.
    * @param one The location the unit is on.
    * @param two The Location the unit is going to.
    * @returns If the move was sucessfully completed
    */
   public boolean move(Location one, Location two)
   {
      if(one == two || one.myUnit == null || two.myUnit != null
              || one.myUnit.playerNum != whoseTurn) {
         return false;
      }
      if(one.myUnit.movePointsRemaining < two.myTerrain.moveCost) {
         return false;
      }
      if((one.xLoc == two.xLoc && Math.abs(one.yLoc - two.yLoc) == 1)
              || (one.yLoc == two.yLoc && Math.abs(one.xLoc - two.xLoc) == 1))
      two.myUnit = one.myUnit;
      two.myUnit.movePointsRemaining -= two.myTerrain.moveCost;
      one.myUnit = null;
      return true;
   }

   /**
    * Checks if the unit on Location 1 has enough range to attack Location
    * two.
    * @param one The unit the attacker is on.
    * @param two The location the unit is trying to attack
    * @return if the unit is in range to attack
    */
   public boolean inRange(Location one, Location two)
   {
      if(one.myUnit == null || two.myUnit == null
              || !one.myUnit.canAttack) {
         return false;
      }
      //int xDist = (int)Math.round(Math.pow(Math.abs(one.xLoc - two.xLoc), 2)+0.49);
      //int yDist = (int)Math.round(Math.pow(Math.abs(one.yLoc - two.yLoc), 2)+0.49);

      int xDist = Math.abs(one.xLoc - two.xLoc);
      int yDist = Math.abs(one.yLoc - two.yLoc);

      //return Math.sqrt(xDist*xDist + yDist*yDist) <= one.myUnit.atkRange;

      System.out.println("Dist: " + new Integer(xDist + yDist));
      return (xDist + yDist) <= one.myUnit.atkRange;
   }
   
   public boolean attack(Location attacker, Location defender)
   {
      if(attacker.myUnit == null || defender.myUnit == null
              || attacker.myUnit.playerNum != whoseTurn
              || !attacker.myUnit.canAttack)
      {
         return false;
      }
      if(!inRange(attacker,defender))
      {
         return false;
      }
      attacker.myUnit.canAttack = false;
      defender.myUnit.healthRemaining -= calcDamage(defender, attacker);

      if(defender.myUnit.healthRemaining <= 0)
      {
         defender.myUnit = null;
      }
      else
      {
         if(inRange(defender,attacker)) {
            attacker.myUnit.healthRemaining -= calcDamage(attacker,defender);
            if(attacker.myUnit.healthRemaining <= 0)
            {
               attacker.myUnit = null;
            }
         }
         
      }
      return true;
   }
   private int calcDamage(Location def, Location atk) {
      Unit attacker = atk.myUnit;
      Unit defender = def.myUnit;
      double mod = (Double)AttackType.modMap.get(attacker.atkType.name + "," + defender.defType.name);
      double defense = defender.defStrength + def.myTerrain.defModifier;
      double dmgBeforeHealth = (double)attacker.atkStrength * mod - defense;
      int damage = (int)(dmgBeforeHealth * ((double)attacker.healthRemaining / (double)attacker.healthTotal));
      if(damage < 1)
      {
          damage = 1;
      }
      return damage;
   }

   public void endTurn() {

    for(int index = 0; index < map.length; index++) {
        for(Location loc: map[index]) {
            if(loc.myUnit != null) {
                loc.myUnit.movePointsRemaining = loc.myUnit.movePointsTotal;
                loc.myUnit.canAttack = true;
            }
        }
    }
      if(whoseTurn == 2) {
          whoseTurn = 1;
      }else {
         whoseTurn = 2;
      }
      
   }

   /**
    *
    * @return 0 if game isn't over, 1 if player 1 has won,
    *   2 if player 2 has won.
    */
   public int isGameOver() {
      boolean oneHasUnits = false;
      boolean twoHasUnits = false;
      for(int index = 0; index < nMapSize; index++) {
         for(Location loc : map[index]) {
            if(loc.myUnit != null) {

               oneHasUnits |= loc.myUnit.playerNum == 1;
               twoHasUnits |= loc.myUnit.playerNum == 2;
            }
         }
      }
      if(oneHasUnits) {
         return twoHasUnits ? 0 : 1;

      }
      return 2;
   }
}
