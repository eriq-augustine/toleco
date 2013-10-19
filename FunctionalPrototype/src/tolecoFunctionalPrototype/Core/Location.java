/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.Core;

/**
 *
 * @author abbarton
 */
public class Location
{
   public Unit myUnit;
   public Terrain myTerrain;
   public int xLoc;
   public int yLoc;

   @Override
   public String toString()
   {
      String returnMe = "";
      if(myUnit == null)
      {
         returnMe += ' ';
      }
      else
      {
         returnMe += myUnit.name.charAt(0);
      }

      if(myTerrain == null)
      {
         returnMe += ' ';
      }
      else
      {
         returnMe += myTerrain.name.charAt(0);
      }
      return returnMe;
   }
}
