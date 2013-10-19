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
public class Terrain {
   public String name;
   public BufferedImage artMap, artPortrait;
   public int moveCost;
   public int defModifier;

   @Override
   public String toString() {
      return name + " m:" + moveCost + " d:" + defModifier;
   }
}
