package cero;

/**
 * The AboutBox represents information about the Cero application. Such
 * information includes the names of the developers, and the current version
 * and build of Cero.
 * 
 * @author Raymond Wong - Javadocs
 * @author Robert Bernal - Pseudocode and source code
 * @version 1.0 - February 28, 2010
 */
public class AboutBox
{
    /** Names of the developers. */
    private String developers = null;

    /** Current version of Cero application. */
    private String version = null;

    /** Current build of Cero application. */
    private String build = null;

    /**
     * Displays the names of the developers.
     * @return Returns a string representation of the names of the developers.
     */
    public String getDevelopers()
    {
        //Fix defect #260, #271
        //SET developers to the names of the developers
        developers = "Developers:\n\nRobert Bernal\n" +
                "Annabel Hung\nRaymond Wong\nMatt Carson\nDirk Cummings\n" +
                "Eric Gustafson\nIlya Seletsky\n";
        //RETURN developers
        return developers;
    }

    /**
     * Displays the current version of Cero.
     * @return A string representation of the version of Cero.
     */
    public String getVersion()
    {
        //SET version to the current version of Cero
        //RETURN version
        version = "Version 1.0\n";
        return version;
    }

    /**
     * Displays the current build of Cero.
     * @return A string representation of the current build.
     */
    public String getBuild()
    {
        //SET build to the current build of Cero
        //RETURN build
        build = "Build 1.0\n";
        return build;
    }
}
