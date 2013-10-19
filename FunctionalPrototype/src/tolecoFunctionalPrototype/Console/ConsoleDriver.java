/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.Console;
import tolecoFunctionalPrototype.Core.Logic;
import java.util.Scanner;
/**
 *
 * @author abbarton
 */
public class ConsoleDriver
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      Logic myLogic = new Logic();
      ConsoleIO io = new ConsoleIO(myLogic);
      myLogic.start();

      String input = "";
      while(!input.equals("q"))
      {
         System.out.println(io.printMap());
         input = in.nextLine();
         if(input.length() > 0 && input.charAt(0) != 'q')
         {
            if(!io.parseInput(input)) {
               System.out.println("unrecognized command");
            }
         }
         
      }
   }

   
}
