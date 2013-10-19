package toleco.terrain;

import toleco.unit.*;

/**
* A Terrain is a generic representation of a terrain that will be used for all of
* the terrains in the game. A terrain has a cost associated with moving onto it
* and a defense modifier that affects any unit occupying the terrain. A terrain
* can only contain a single unit.
*
* @author Jon Moorman (Javadocs)
* @author Andrew Barton (implementation)
* @version 1.0
*/
public class Terrain
{
    /**
    * The type of terrain which corresponds to its immutable properties.
    */
    private String type;
    
    /**
    * The unit that occupies the terrain.
    */
    private Unit unit;
    
    /**
    * The defense modifier applied to the occupying unit
    */
    private int defMod;
    
    /**
    * The movement cost associated with moving onto the terrain.
    */
    private int moveCost;
    
    /**
    * The x-coordinate of the terrain.
    */
    private int xCoord;
    
    /**
    * The y-coordinate of the terrain.
    */
    private int yCoord;
    
    /**
    * The constructor for a Terrain.
    * @param type the type of terrain being represented
    * @param defMod the defense modifier given to the occupying unit
    * @param moveCost the cost to move onto the terrain
    * @param x the x-coordinate of the terrain
    * @param y the y-coordinate of the terrain
    */
    public Terrain(String type, int defMod, int moveCost, int x, int y)
    {
        //Initialize this terrain's type to the param type
        //Initialize this terrain's defense modifier to the param defense modifier
        //Initialize this terrain's move cost to the param move cost
        //Initialize this terrain's x coordinate to the param x coordinate
        //Initialize this terrain's y coordinate to the param y coordinate
    }
    
    /**
    * Accessor method to type.
    * @return the type of terrain that is being represented
    */
    public String getType()
    {
        //return this terrain's type
        return null;
    }
    
    /**
    * Accessor method to unit.
    * @return the unit occupying the terrain
    */
    public Unit getUnit()
    {
        //return this terrain's unit
        return null;
    }
    
    /**
    * Accessor method to defense modifier.
    * @return the defense modifier
    */
    public int getDefMod()
    {
        //return this terrain's defense modifier
        return Integer.MIN_VALUE;
    }
    
    /**
    * Accessor method to movement cost.
    * @return the cost to move onto the terrain
    */
    public int getMoveCost()
    {
        //return this terrain's move cost
        return -1;
    }
    
    /**
    * Accessor method to x-coordinate.
    * @return the x-coordinate of the terrain
    */
    public int getX()
    {
        //return this terrain's x coordinate
        return -1;
    }
    
    /**
    * Accessor method to y-coordinate.
    * @return the y-coordinate of the terrain
    */
    public int getY()
    {
        //return this terrain's y coordinate
        return -1;
    }
    
    /**
    * Sets the unit contained by the terrain. If there is already a unit on the terrain
    * it will be overwritten.
    * @param unit the unit that will be placed on the terrain
    */
    public void setUnit(Unit unit)
    {
        //Set this terrain's unit to the param unit
    }
    
    /**
    * Removes the occupying unit from the terrain.
    * @return the removed unit
    */
    public Unit removeUnit()
    {
        //Create a temporary unit
        //Set the temporary unit with this terrain's unit
        //Set the terrain's unit to null
        //Return the temporary unit
        return null;
    }
}
