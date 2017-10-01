/**
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-19
 *
 */
public class PointTest extends student.TestCase {

    /**
     * Test method for {@link Rectangle1#main(java.lang.String[])}.
     */
    
    public void testMain() {
        Point test = new Point();
        String[] ourArguments = { "SyntaxTest.txt" };
        Point.main(ourArguments);
        assertEquals(ourArguments[0].compareTo("SyntaxTest.txt"), 0);
        assertNotNull(test);
    }

}
