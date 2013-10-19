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
public class UnitClubman extends Unit {
       private static String kClubPath = "/tolecoFunctionalPrototype/GUI/Images/club_caveman.gif";

   public UnitClubman(int _playerNum) {
      try
      {
         artPortrait = ImageIO.read(getClass().getResource(kClubPath));
      }catch(Exception e)
      {
         System.out.println(e);
      }
       name = "Clubman";
       atkStrength = 20;
       atkType = AttackType.bludgeon;
       atkRange = 1;
       defStrength = 10;
       defType = DefenseType.leather;

       healthTotal = 100;
       //healthTotal = 10;
       
       healthRemaining = healthTotal;

       //movePointsTotal = 2;
       movePointsTotal = 200;

       movePointsRemaining = movePointsTotal;
       canAttack = true;
       playerNum = _playerNum;
   }
}
