/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.Core;

import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author abbarton
 */
public class UnitSpearman extends Unit {
       private static String kSpearPath = "/tolecoFunctionalPrototype/GUI/Images/spear_caveman.gif";
   public UnitSpearman(int _playerNum) {
      try 
      {
         artPortrait = ImageIO.read(getClass().getResource(kSpearPath));
      }catch(Exception e)
      {
         System.out.println(e);
      }

       name = "Spearman";
       atkStrength = 20;
       atkType = AttackType.pierce;
       atkRange = 2;
       defStrength = 10;
       defType = DefenseType.padding;
       healthTotal = 100;
       healthRemaining = healthTotal;
       movePointsTotal = 2;
       movePointsRemaining = movePointsTotal;
       canAttack = true;
       playerNum = _playerNum;
   }
}
