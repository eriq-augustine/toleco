package toleco.unit;

import toleco.controller.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

/**
* This singleton class is responsible for instantiating and setting the values inside
* of a new Unit object. It also keeps a list of all of the possible Units.
* It does not keep a list of all of the Units it has built, only the ones it can
* build in the future.
*
* @author Andrew Barton (both javadocs and implementation)
* @version 1.0
*/
public class UnitFactory
{
    /**
    * A lookup table mapping a unit name to an instance of the unit.
    */
    private HashMap<String, Unit> unitMap;
    
    
    //this fancy statement just makes a new ArrayList
    //filled with the names of the properties to check
    //that they're all there when we read in the file.
    private static final ArrayList<String> kUnitPropertyNames =
        new ArrayList<String>(Arrays.asList(new String[]{
            "type", "maxHealth", "maxMoves",
            "attackType", "attackValue", "attackRange", "armorType", "armorValue"}));

    /**
     * The path for the directory that units' properties files are located.
     */
    private static final String kUnitDirectory = "units";
    
    /**
    * Although it takes no parameters, the constructor must read in all of the files
    * from the Unit folder and attempt to create an instance of each unit.
    * The UnitFactory can only get new Units from the Unit Folder, with the exception of
    * DefaultUnit.
    */
    public UnitFactory()
    {
        //Create a File.
        File unitDir = null;

        //TRY
        {
            //Create a new File which is the kUnitDirectory
        }
        //CATCH
        {
            //Print an error about not being able to find the unit directory.
        }

        //Initialie a HashMap<String, Unit> for units and their names
        //TRY
        {
            //if the unit directory exists and is a dir
            {
                //initialize an array of files from the directory's list of files
                //FOR every file in the directory
                {
                    //Check if it is a proper properties file
                    {
                        //Call UnitFromFile with the filename
                    }
                    //ENDIF
                }//END FOR
            }
            //ELSE
            {
                //unitDir isn't a directory?
                //FAIL! (do nothing)
            }
            //ENDIF
        }

        //CATCH ioexception
        {
            //Fixes defect #146
            //Print a generic IO exception error
        }
        //END catch
    }
    
    /**
    * Get a list of Strings of all of the different Unit types that this
    * UnitFactory can build.
    * @return an ArrayList of Strings of all of the different Unit types that this
    * UnitFactory can build
    */
    public ArrayList<String> getUnitNames()
    {
        //return ArrayList of all unit names
        return null;
    }
    
    /**
    * Given a name (unique to the type of Unit, not the individual Unit),
    * the Player that this Unit will belong to, the currentHealth of the Unit,
    * whether the Unit can attack this turn or not, and the current number of moves
    * this unit will have, constructs a Unit of type <name> from the list of
    * Units that UnitFactory has. UnitFactory will construct a Unit with all of
    * its attributes, and will return DefaultUnit if it
    * cannot find a Unit by the given name.
    * @pre currentHealth can not be greater than the unit's maxHealth
    * @param unitName the name of the Unit type to be created
    * @param player the Player that the Unit will belong to
    * @param currentHealth the currentHealth that the Unit will have
    * @param canAttack whether the Unit will be able to attack this turn or not
    * @param currentMoves the current number of Moves that this Unit will have
    * @return the constructed Unit
    */
    public Unit build(String unitName, Player player,
        int currentHealth, boolean canAttack,
        int currentMoves)
    {
        //Create a Unit unit
        //IF unitName is in unitMap
        {
            //Set unit to a new instance of the unit Mapped to by unitName with
            //parameters from the archtype except for the owning player,
            //current health, whether it can still attack and its current
            //movement points
        }
        //ELSE
        {
            //Set unit to a new instance of the DefaultUnit
        } //END IF
        //Return unit
        return null;
    }
    
    /**
    * Given a name (unique to the type of Unit, not the individual Unit) and the
    * the Player that this Unit will belong to,
    * constructs a Unit of type <name> from the list of
    * Units that UnitFactory has. UnitFactory will put all of the values
    * into the Unit except for the Unit, and will return DefaultUnit if it
    * cannot find a Unit by the given name.
    * @param unitName the name of the Unit type to be created
    * @param player the Player that the Unit will belong to
    * @return the constructed Unit
    */
    public Unit build(String unitName, Player player)
    {
        //Create a Unit unit
        //IF unitName is in unitMap

        {
            //Set unit to a new instance of the unit Mapped to by unitName with
            //parameters from the archtype except for the owning player
        }
        //ELSE
        {
            //Set unit to a new instance of the DefaultUnit
        }
        //END IF
        //Return Unit
        return null;
    }
    
    /**
    * Given a string generated by a Units toStringForFile method regenerate
    * the Unit that generated the string.
    * @param fromFile the string generated by a toStringForFile call
    * @return the Unit re-constructed by the string
    */
    public Unit build(String fromFile)
    {
        //Extract the Unit properties from the string
        //SET the scanner's delimiter
        //GET the unit's type
        //GET the unit's owner
        //GET the unit's current health
        //GET the unit's current moves
        //GET if the unit can attack or not;
        
        //return the result of Call build with the parameters from the string
        return null;
    }
    
    /**
    * Given a name of a file to look in, gets the data about a particular Unit
    * and adds it to the UnitMap.
    * @pre file with name fileName exists, and is in the proper directory
    * @param unitFile the file from which to read the Unit data
    */
    private void unitFromFile(File unitFile)
    {
        //Create a new Properties variable unitProps
        //TRY
        {
            //IF all properties are there
            {
                //read all of the properties
                //player doesn't matter, it will be set again later.

            }
            
        }
        //catch exception caused by bad file(file itself is bad, not the data contained)
        {
            //Fixes defect #146
            //PRINT statement about failure
        }
        //END catch
        //catch exception caused by bad numerical values
        {
            //Fixes defect #146
            //PRINT statement about failure

        }
        //END catch
        //catch  exception caused by a bad attackType or bad armorType
        {
            //Fixes defect #146
            //PRINT statement about failure

        }
    }
}
