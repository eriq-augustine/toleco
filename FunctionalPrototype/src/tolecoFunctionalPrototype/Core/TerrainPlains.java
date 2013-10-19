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

public class TerrainPlains extends Terrain {
       private static String kPlainsPath = "/tolecoFunctionalPrototype/GUI/Images/plains.gif";


   public TerrainPlains() {
      name = "Plains";
      moveCost = 1;
      defModifier = 1;
      try
      {
         artPortrait = ImageIO.read(getClass().getResource(kPlainsPath));
         artMap = artPortrait;
      }catch(Exception e)
      {
         System.out.println(e);
      }
   }
}
