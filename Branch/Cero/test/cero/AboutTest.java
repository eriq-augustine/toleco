package cero;

/**
 * The test class AboutTest.
 *
 * @author  J. Dalbey
 */
public class AboutTest extends junit.framework.TestCase
{
    /** 
     *  Test the GetAuthors method
     */
	public void testGetAuthors()
	{
		assertEquals("Ilya Seletsky\nMatt Carson\nDirk Cummings\n" +
                    "Eric Gustafson\n" +
        "Annabel Hung\nRobert Bernal\nRaymond Wong\n", About.getAuthors());
	}

        public void testGetNameDefect203()
        {
            assertEquals("Annabel Hung", About.getName(5));

        }

        public void testGetNameDefect205()
        {
            System.out.println("Testing getName defect 205");
            assertEquals("Raymond Wong", About.getName(7));
        }

        public void testGetNameDefect206()
        {
            assertEquals("Matt Carson", About.getName(2));
        }

        public void testGetNameDefect207()
        {
            System.out.println("-- TESTING getNam defect 207 --");
            assertEquals("Dirk Cummings", About.getName(3));
        }
        
   //Test for Defect #208
   public void testGetNameDefect208() {
      System.out.println("-- TESTING getName defect 208 --");
      assertEquals("Eric Gustafson", About.getName(4));
   }

   public void testGetNameDefect210()
   {
       System.out.println("- TESTING getName defect 210 --");
       assertEquals("Robert Bernal", About.getName(6));
   }

   public void testGetNameDefect211() {
      System.out.println("-- TESTING getName defect 211 --");
      assertEquals("Ilya Seletsky", About.getName(1));
   }
}

