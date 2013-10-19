package toleco.terrain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Properties;

/**
* This singleton class is responsible for instantiating and setting the values inside
* of a new Terrain object. It also keeps a list of all of the possible Terrains.
* It does not keep a list of all of the Terrains it has built, only the ones it can
* build in the future.
*
* @author Andrew Barton(javadoc)
* @author Jon Moorman(implementation)
* @version 1.0
*/
public class TerrainFactory
{
    /**
    * A lookup table mapping a terrain name to an instance of the terrain.
    */
    private HashMap<String, Terrain> terrainMap;

    /**
     * The path for the directory that terrains' properties files are located.
     */
    private static final String kTerrainDirectory = "terrains";

    /**
    * Although it takes no parameters, the constructor must read in all of the files
    * from the Terrain folder and attempt to create an instance of each terrain. 
    * The TerrainFactory can only get new Terrains from the Terrain Folder, with
    * the exception of DefaultTerrain.
    */
    public TerrainFactory()
    {
        //Create a File

        //TRY
        {
            //INIT the File with the terrain directory
        }
        //CATCH
        {
            //Print an error about not being able to find the terrain directory
        }

        //INIT a new hash map of Strings and Terrains

        //TRY
        {
            //if the File is a directory
            {
                //INIT an array of Files with the contents of the directory

                //FOR every File in the directory
                {
                    //IF the File is a file with .properties in it
                    {
                        //Call TerrainFromFile with the filename
                    }
                    //END IF
                }
                //END FOR
            }
        }
        //CATCH an IOException
        {
            //Print the stack trace
        }
    }
    
    /**
    * Get a list of Strings of all of the different Terrain types that this
    * TerrainFactory can build.
    * @return an ArrayList of Strings of all of the different Terrain types that this
    * TerrainFactory can build.
    */
    public ArrayList<String> getTerrainNames()
    {
        //Return an ArrayList<String> names with every key
        return null;
    }
    
    /**
    * Given a name (unique to the type of Terrain, not the individual Terrain),
    * and a x and y that this Terrain will think it is located at, construct a
    * terrain of type <name> from the list of Terrains that TerrainFactory has.
    * TerrainFactory will put all of the values into the Terrain except for the
    * Unit, and will return DefaultTerrain if it cannot find a Terrain by the
    * given name.
    *
    * @param type the type of Terrain to be built
    * @param x the x-coordinate this Terrain will be located at
    * @param y the y-coordinate this Terrain will be located at
    * @return the constructed Terrain or defaultTerrain if
    *    the given type cannot be found
    */
    public Terrain build(String type, int x, int y)
    {
        //Create a new Terrain ter

        //IF type is in terrainMap
        {
            //Get the value for the given type

            //Set ter to a new terrain using the method parameters and the
            //defMod and moveCost from the terrainMap entry
        }
        //ELSE
        {
            //Set ter to a default terrain located at x, y
        }
        //END IF

        //RETURN ter
        return null;
    }
    
    /**
    * Given a name of a file to look in, gets the data about a particular Terrain
    * and adds it to the terrainMap.
    * @pre file with name fileName exists, and is in the proper directory
    * @param fileName the name of the file from which to read the Terrain data
    */
    private void terrainFromFile(String fileName)
    {
        //Create a new Properties variable props

        //Try
        {
            //load the terrain properties from the given file into props
            //Create a terrain using the attributes specified in props
            //Add the Terrain to the hash map using its type as the key
        }
        //Catch IOException
        {
            //Print the stack trace
        }
    }
}
