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
public class TerrainMountains extends Terrain {

   private static String kMountainPath = "/tolecoFunctionalPrototype/GUI/Images/mountains.gif";

   public TerrainMountains() {
      name = "Mountains";
      moveCost = 2;
      defModifier = 3;
      try
      {
         artPortrait = ImageIO.read(getClass().getResource(kMountainPath));
         artMap = artPortrait;
      }catch(Exception e)
      {
         System.out.println(e);
      }
   }

}
