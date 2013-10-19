/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tolecoFunctionalPrototype.Core;
import java.awt.image.BufferedImage;
import java.util.HashMap;
/**
 * *************************************************
 * DANGER DANGER DANGER
 *
 * ALWAYS INITALIZE ALL DEFENSE TYPES BEFORE
 * INITIALIZING ATTACK TYPES
 *
 * DANGER DANGER DANGER
 * *************************************************
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * Or else explosions.
 * @author abbarton
 */

public enum AttackType

{


   maul('m'),
   bludgeon('b'),
   pierce('p');

   public char name;
   public BufferedImage image;
   public static HashMap modMap = new HashMap<String,Double>();

   public static void init()
   {
      modMap.put("b,l", 1.25);
      modMap.put("b,p", 0.75);
      modMap.put("b,b", 1.0);
      modMap.put("p,l", 0.75);
      modMap.put("p,p", 1.0);
      modMap.put("p,b", 1.25);
      modMap.put("m,l", 1.0);
      modMap.put("m,p", 1.25);
      modMap.put("m,b", 0.75);
   }

   AttackType(char name)
   {
      this.name = name;
   }
}
