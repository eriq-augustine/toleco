package cero;

import java.util.List;
import java.util.Arrays;
/**
 * About knows the credits for this application.
 * 
 * @author J. Dalbey
 */
public class About
{
    // Added "Annabel Hung" defect # 203
    // Added "Raymond Wong" defect # 205
    // Added "Matt Carson" defect # 205
    // Added "Dirk Cummings" defect # 207
    // Added "Eric Gustafson" defect # 208
    // Added "Robert Bernal" defect # 210
    // Added "Ilya Seletsky" defect # 211
    private final static String[] authors = {"Ilya Seletsky",
        "Matt Carson", "Dirk Cummings", "Eric Gustafson", "Annabel Hung",
        "Robert Bernal", "Raymond Wong"};

    /**
     * Return a printable representation of the names of the 
     * authors of this application.
     * 
     * @return String names of authors separated by newlines.
     */
    public static String getAuthors()
    {
        // Get author names as a list
        List<String> names = Arrays.asList(authors);
        StringBuilder result = new StringBuilder();
        // Iterate through names appending to a string
        for (String name : names)
        {
            result.append(name+"\n");
        }
        return result.toString();
    }
    
    /**
     *  Return the name of author n.
     *  @param number the ordinal position of desired author.
     *  @pre 1 <= number <= authors.length
     *  @return the nth author in the list
     */   
    public static String getName(int number)
    {
        return authors[number-1];
    }

}
