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
public enum DefenseType
{
   leather('l'),
   bone('b'),
   padding('p');

   public char name;
   public BufferedImage image;

   DefenseType(char name)
   {
      this.name = name;
   }
}
