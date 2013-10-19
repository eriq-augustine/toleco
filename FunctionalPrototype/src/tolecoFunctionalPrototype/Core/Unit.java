/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.Core;

import java.awt.image.BufferedImage;

/**
 *
 * @author abbarton
 */
public class Unit {
   public String name;
   public int atkStrength;
   public AttackType atkType;
   public int atkRange;
   public int defStrength;
   public DefenseType defType;
   public int healthTotal;
   public int healthRemaining;
   public BufferedImage artPortrait;
   public int movePointsTotal;
   public int movePointsRemaining;
   public boolean canAttack;
   public int playerNum;

   @Override
   public String toString() {
      String returnMe = "";
      returnMe += name + "\n";
      returnMe += "Health:  " + healthRemaining + "/" + healthTotal+ "\n";
      returnMe += "Attack:  " + atkStrength + " " + atkType + " " + "(" + atkRange + ") " + (canAttack ? "y" : "n")+ "\n";
      returnMe += "Defense: " + defStrength + " " + defType + " \n";
      returnMe += "Moves:   " +movePointsRemaining + "/" + movePointsTotal + "\n";
      returnMe += "Player:  " + playerNum + '\n';
      return returnMe;
   }
}
